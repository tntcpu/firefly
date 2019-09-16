package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-09-16
 **/
class Entrance2 implements Runnable {
	private static Count count = new Count();
	private static List<Entrance2> entrances = new ArrayList<>();
	private int number;
	private final int id;

	public Entrance2(int id) {
		this.id = id;
		entrances.add(this);
	}

	@Override
	public void run() {
		for (; ; ) {
			synchronized (this) {
				++number;
			}
			System.out.println(this + " Total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Stopping " + this);
				return;
			}
		}
	}

	public synchronized int getValue() {
		return number;
	}

	@Override
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumEntrances() {
		int sum = 0;
		for (Entrance2 entrance : entrances) {
			sum += entrance.getValue();
		}
		return sum;
	}
}

public class E19_OrnamentalGarden2 {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Entrance2(i));
		}
		TimeUnit.SECONDS.sleep(3);
		exec.shutdownNow();
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.println("some tasks were not terminated");
		}
		System.out.println("Total: " + Entrance2.getTotalCount());
		System.out.println("Sum of Entrances: " + Entrance2.sumEntrances());
	}
}
