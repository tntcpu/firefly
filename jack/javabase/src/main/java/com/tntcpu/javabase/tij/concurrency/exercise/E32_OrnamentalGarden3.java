package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-17
 **/
class Entrance3 implements Runnable {
	private final CountDownLatch latch;
	private static Count cout = new Count();
	private static List<Entrance3> entrances = new ArrayList<>();
	private int number;
	private final int id;
	private static volatile boolean canceled;

	public static void cancel() {
		canceled = true;
	}

	public Entrance3(CountDownLatch latch, int id) {
		this.latch = latch;
		this.id = id;
		entrances.add(this);
	}

	@Override
	public void run() {
		while (!canceled) {
			synchronized (this) {
				++number;
			}
			System.out.println(this + " total: " + cout.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("sleep interrupted");
			}
		}
		latch.countDown();
		System.out.println("stopping " + this);
	}

	public synchronized int getValue() {
		return number;
	}

	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return cout.value();
	}

	public static int sumEntrances() {
		int sum = 0;
		for (Entrance3 entrance : entrances) {
			sum += entrance.getValue();
		}
		return sum;
	}
}

public class E32_OrnamentalGarden3 {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(5);
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Entrance3(latch,i));
		}
		TimeUnit.SECONDS.sleep(3);
		Entrance3.cancel();
		exec.shutdown();
		latch.await();
		System.out.println("Total: "+ Entrance3.getTotalCount());
		System.out.println("sum of entrances: " + Entrance3.sumEntrances());
	}
}































