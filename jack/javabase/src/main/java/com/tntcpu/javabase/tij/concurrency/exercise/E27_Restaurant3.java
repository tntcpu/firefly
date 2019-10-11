package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-11
 **/
class WaitPerson3 implements Runnable {
	private Restaurant3 restaurant;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public WaitPerson3(Restaurant3 restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				lock.lock();
				try {
					while (restaurant.meal == null) {
						condition.await();
					}
				} finally {
					lock.unlock();
				}
				System.out.println("got " + restaurant.meal);
				restaurant.chef.lock.lock();
				try {
					restaurant.meal = null;
					restaurant.chef.condition.signalAll();
				} finally {
					restaurant.chef.lock.unlock();
				}
			}
		} catch (Exception e) {
			System.out.println("waiter interrupted");
		}
	}
}

class Chef3 implements Runnable {
	private Restaurant3 restaurant;
	private int orderNum = 0;
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public Chef3(Restaurant3 restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				lock.lock();
				try {
					if (restaurant.meal != null) {
						condition.await();
					}
				} finally {
					lock.unlock();
				}
				if (++orderNum == 10) {
					System.out.println("out of goal");
					restaurant.exec.shutdownNow();
				}
				System.out.println("order up");
				restaurant.waitPerson.lock.lock();
				try {
					restaurant.meal = new Meal(orderNum);
					restaurant.waitPerson.condition.signalAll();
				} finally {
					restaurant.waitPerson.lock.unlock();
				}
				TimeUnit.MILLISECONDS.sleep(200);
			}
		} catch (Exception e) {
			System.out.println("chef interrupted");
		}
	}
}

class Restaurant3 {
	Meal meal;
	WaitPerson3 waitPerson = new WaitPerson3(this);
	Chef3 chef = new Chef3(this);
	ExecutorService exec = Executors.newCachedThreadPool();

	public Restaurant3() {
		exec.execute(waitPerson);
		exec.execute(chef);
	}
}

public class E27_Restaurant3 {
	public static void main(String[] args) {
		new Restaurant3();
	}
}
