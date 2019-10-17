package com.tntcpu.javabase.tij.concurrency.example;

import java.util.Random;
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
class TaskPortion implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private Random rand = new Random(47);
	private final CountDownLatch latch;

	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (Exception e) {

		}
	}

	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		System.out.println(this + "completed");
	}

	public String toString() {
		return String.format("%1$-3d ", id);
	}

}

class WaitingTask implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final CountDownLatch latch;

	public WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			latch.await();
			System.out.println("latch barrier passed for " + this);
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted");
		}
	}
	public String toString(){return String.format("waitingtask %1$-3d ",id);}
}

public class CountDownLatchDemo {
	static final int SIZE = 100;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(SIZE);
		for (int i = 0; i < 10; i++) {
			exec.execute(new WaitingTask(countDownLatch));
		}
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new TaskPortion(countDownLatch));
		}
		TimeUnit.SECONDS.sleep(10);
		System.out.println("launched all tasks");
		exec.shutdownNow();
	}
}
























