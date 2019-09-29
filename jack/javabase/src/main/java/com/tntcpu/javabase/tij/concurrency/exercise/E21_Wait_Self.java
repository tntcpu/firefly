package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-09-29
 **/
class WaitRunnable implements Runnable {

	@Override
	public void run() {
		try {
			synchronized (this) {
				wait();
			}
			System.out.println("wake up!");
		} catch (InterruptedException e) {
			System.out.println("interrupt via sleep");
		}
	}
}

class NotifyAllRunnable implements Runnable {
	private WaitRunnable waitRunnable;

	public NotifyAllRunnable(WaitRunnable waitRunnable) {
		this.waitRunnable = waitRunnable;
	}

	@Override
	public void run() {
		try {
			System.out.println("notifying");
			TimeUnit.SECONDS.sleep(2);
			synchronized (waitRunnable) {
				waitRunnable.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class E21_Wait_Self {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		WaitRunnable waitRunnable = new WaitRunnable();
		exec.execute(waitRunnable);
		exec.execute(new NotifyAllRunnable(waitRunnable));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}


























