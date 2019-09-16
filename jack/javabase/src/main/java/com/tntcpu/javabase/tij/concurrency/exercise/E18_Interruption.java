package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-09-16
 **/
class NonTask {
	static void longMethod() throws InterruptedException {
		TimeUnit.SECONDS.sleep(60);
	}
}

class Task implements Runnable {

	@Override
	public void run() {
		try {
			NonTask.longMethod();
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		} finally {

		}
	}
}

public class E18_Interruption {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Task());
		t.start();
		TimeUnit.SECONDS.sleep(3);
		t.interrupt();
	}
}
