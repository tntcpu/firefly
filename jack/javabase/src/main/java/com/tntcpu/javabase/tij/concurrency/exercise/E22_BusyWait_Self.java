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
class BusyWaitOne implements Runnable {
	private boolean flag;

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(3);
			setFlag(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isFlag() {
		return flag;
	}
}

class BusyWaitTwo implements Runnable {
	private BusyWaitOne busyWaitOne;

	public BusyWaitTwo(BusyWaitOne busyWaitOne) {
		this.busyWaitOne = busyWaitOne;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		while (true) {
			if (busyWaitOne.isFlag()) {
				busyWaitOne.setFlag(false);
				System.out.println("flag converse");
				break;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("time fly: " + (end - start) + "ms");
	}
}

public class E22_BusyWait_Self {
	public static void main(String[] args) throws InterruptedException {
		BusyWaitOne busyWaitOne = new BusyWaitOne();
		BusyWaitTwo busyWaitTwo = new BusyWaitTwo(busyWaitOne);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(busyWaitOne);
		exec.execute(busyWaitTwo);
		TimeUnit.SECONDS.sleep(2);
		exec.shutdown();
	}
}
