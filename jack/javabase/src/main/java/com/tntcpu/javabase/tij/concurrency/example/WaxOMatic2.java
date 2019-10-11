package com.tntcpu.javabase.tij.concurrency.example;

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
class Car2 {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean waxOn = false;

	public void waxed() {
		lock.lock();
		try {
			waxOn = true;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void buffed() {
		lock.lock();
		try {
			waxOn = false;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn == false) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}

	public void waitForBuffing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn == true) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
}

class WaxOn2 implements Runnable {
	private Car2 car2;

	public WaxOn2(Car2 car2) {
		this.car2 = car2;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("wax on!");
				TimeUnit.MILLISECONDS.sleep(200);
				car2.waxed();
				car2.waitForBuffing();
			}
		} catch (Exception e) {
			System.out.println("exiting via interrupt");
		}
		System.out.println("ending wax on task");
	}
}

class WaxOff2 implements Runnable {
	private Car2 car2;

	public WaxOff2(Car2 car2) {
		this.car2 = car2;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car2.waitForWaxing();
				System.out.println("wax off");
				TimeUnit.MILLISECONDS.sleep(200);
				car2.buffed();
			}
		} catch (InterruptedException e) {
			System.out.println("exiting via interrupt");
		}
		System.out.println("ending wax off task");
	}
}

public class WaxOMatic2 {
	public static void main(String[] args) throws InterruptedException {
		Car2 car2 = new Car2();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff2(car2));
		exec.execute(new WaxOn2(car2));
		TimeUnit.SECONDS.sleep(3);
		exec.shutdownNow();
	}
}














