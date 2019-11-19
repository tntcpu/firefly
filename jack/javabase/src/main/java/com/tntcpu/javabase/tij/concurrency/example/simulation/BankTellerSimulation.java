package com.tntcpu.javabase.tij.concurrency.example.simulation;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-23
 **/

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 顾客
 */
class Customer {
	//服务时间
	private final int serviceTime;

	public Customer(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public String toString() {
		return "[" + serviceTime + "]";
	}
}

/**
 * 顾客队列
 */
class CustomerLine extends ArrayBlockingQueue<Customer> {
	public CustomerLine(int capacity) {
		super(capacity);
	}

	public String toString() {
		if (this.size() == 0) {
			return "[Empty]";
		}
		StringBuilder result = new StringBuilder();
		for (Customer customer : this) {
			result.append(customer);
		}
		return result.toString();
	}
}

/**
 * 产生顾客源
 */
class CustomerGenerator implements Runnable {
	private CustomerLine customers;
	private static Random rand = new Random(47);

	public CustomerGenerator(CustomerLine customers) {
		this.customers = customers;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
				customers.put(new Customer(rand.nextInt(1000)));
			}
		} catch (Exception e) {
			System.out.println("customerGenerator interrupted");
		}
		System.out.println("customerGenerator terminating");
	}
}

/**
 * 柜台出纳
 */
class Teller implements Runnable, Comparable<Teller> {
	private static int counter = 0;
	private final int id = counter++;
	private int customersServed = 0;
	private CustomerLine customers;
	private boolean servingCustomerLine = true;

	public Teller(CustomerLine customers) {
		this.customers = customers;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Customer customer = customers.take();
				TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
				synchronized (this) {
					customersServed++;
					while (!servingCustomerLine) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println(this + "interrupted");
		}
		System.out.println(this + "terminating");
	}

	public synchronized void doSomethingElse() {
		customersServed = 0;
		servingCustomerLine = false;
	}

	public synchronized void serveCustomerLine() {
		assert !servingCustomerLine : "already serving: " + this;
		servingCustomerLine = true;
		notifyAll();
	}

	public String toString() {
		return "teller " + id + " ";
	}

	public String shortString() {
		return "T" + id;
	}

	@Override
	public synchronized int compareTo(Teller other) {
		return customersServed < other.customersServed ? -1 : (customersServed == other.customersServed ? 0 : 1);
	}
}

class TellManager implements Runnable {
	private ExecutorService exec;
	private CustomerLine customers;
	//优先队列
	private PriorityQueue<Teller> workingTeller = new PriorityQueue<>();
	//普通队列
	private Queue<Teller> tellersDoingOtherThings = new LinkedList<>();
	//调整期
	private int adjustmentPeriod;
	private static Random rand = new Random(47);

	public TellManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
		this.exec = exec;
		this.customers = customers;
		this.adjustmentPeriod = adjustmentPeriod;
		Teller teller = new Teller(customers);
		exec.execute(teller);
		workingTeller.add(teller);
	}
	//调整柜台数量
	public void adjustTellerNumber() {
		//触发条件(顾客数量是办公人员数量的两倍时)
		if (customers.size() / workingTeller.size() > 2) {
			if (tellersDoingOtherThings.size() > 0) {
				Teller teller = tellersDoingOtherThings.remove();
				teller.serveCustomerLine();
				workingTeller.offer(teller);
				return;
			}
			Teller teller = new Teller(customers);
			exec.execute(teller);
			workingTeller.add(teller);
			return;
		}
		if (workingTeller.size() > 1 && customers.size() / workingTeller.size() < 2) {
			reassignOneTeller();
		}
		if (customers.size() == 0) {
			while (workingTeller.size() > 1) {
				reassignOneTeller();
			}
		}
	}
	//减少柜台服务的数量
	private void reassignOneTeller() {
		Teller teller = workingTeller.poll();
		teller.doSomethingElse();
		tellersDoingOtherThings.offer(teller);
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				adjustTellerNumber();
				System.out.println(customers + "{ ");
				for (Teller teller : workingTeller) {
					System.out.println(teller.shortString() + " ");
				}
				System.out.println("}");
			}
		} catch (Exception e) {
			System.out.println(this + "interrupted");
		}
		System.out.println(this + "terminating");
	}

	public String toString() {
		return "TellerManger ";
	}
}

public class BankTellerSimulation {
	static final int MAX_LINE_SIZE = 50;
	static final int ADJUSTMENT_PERIOD = 1000;

	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
		exec.execute(new CustomerGenerator(customers));
		exec.execute(new TellManager(exec, customers, ADJUSTMENT_PERIOD));
		System.out.println("press 'Enter' to quit");
		System.in.read();
		exec.shutdownNow();
	}

}




















