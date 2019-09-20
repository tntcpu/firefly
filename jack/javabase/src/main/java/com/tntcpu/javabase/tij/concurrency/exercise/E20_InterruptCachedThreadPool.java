package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-09-16
 **/
class LiftOff2 implements Runnable {
	protected int countDown = 5000;
	private static int taskCount;
	private final int id = taskCount++;

	public LiftOff2() {
	}

	public LiftOff2(int countDown) {
		this.countDown = countDown;
	}

	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
	}

	@Override
	public void run() {
		while (countDown-- > 0) {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Interrupted: #" + id);
				return;
			}
			System.out.println(status());
			Thread.yield();
		}
	}
}

public class E20_InterruptCachedThreadPool {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff2());
		}
		Thread.yield();
		exec.shutdownNow();
	}
}
