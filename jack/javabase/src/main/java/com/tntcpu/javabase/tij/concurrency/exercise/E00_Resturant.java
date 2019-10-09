package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-09
 **/
class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}

class Chef implements Runnable {
	private E00_Resturant resturant;
	private int count = 0;

	public Chef(E00_Resturant e00_resturant) {
		resturant = e00_resturant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (resturant.meal != null) {
						wait();
					}
				}
				if (++count == 10) {
					System.out.println("out of meal");
					resturant.exec.shutdownNow();
				}
				System.out.println("order up!");
				synchronized (resturant.waiter) {
					resturant.meal = new Meal(count);
					resturant.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (Exception e) {
			System.out.println("chef interrupted!");
		}
	}
}

class Waiter implements Runnable {
	private E00_Resturant resturant;

	public Waiter(E00_Resturant resturant) {
		this.resturant = resturant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (resturant.meal == null) {
						wait();
					}
				}
				System.out.println("got meal " + resturant.meal);
				synchronized (resturant.chef) {
					resturant.meal = null;
					resturant.chef.notifyAll();
				}
			}
		} catch (Exception e) {
			System.out.println("waiter interrupted");
		}
	}
}

public class E00_Resturant {
	Meal meal;
	Chef chef = new Chef(this);
	Waiter waiter = new Waiter(this);
	ExecutorService exec = Executors.newCachedThreadPool();

	public E00_Resturant() {
		exec.execute(chef);
		exec.execute(waiter);
	}

	public static void main(String[] args) {
		new E00_Resturant();
	}
}
