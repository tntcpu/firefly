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
class Coop1 implements Runnable {
	public Coop1() {
		System.out.println("Constructed coop1");
	}

	@Override
	public void run() {
		System.out.println("coop1 going into wait");
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("coop1 exited wait");
	}
}

class Coop2 implements Runnable {
	Runnable otherTask;

	public Coop2(Runnable otherTask) {
		this.otherTask = otherTask;
		System.out.println("constructed coop2");
	}

	@Override
	public void run() {
		System.out.println("coop2 pausing 2 seconds");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("coop2 calling notifyAll");
		synchronized (otherTask) {
			otherTask.notifyAll();
		}
	}
}

public class E21_ThreadCooperation {
	public static void main(String[] args) {
		Runnable coop1 = new Coop1(),
				coop2 = new Coop2(coop1);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(coop1);
		exec.execute(coop2);
		Thread.yield();
		exec.shutdown();
	}
}
