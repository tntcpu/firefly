package com.tntcpu.javabase.tij.concurrency.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-11
 **/
class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;

	public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
		this.rockets = rockets;
	}

	public void add(LiftOff lo) {
		try {
			rockets.put(lo);
		} catch (InterruptedException e) {
			System.out.println("Interrupted during put");
		}
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch (InterruptedException e) {
			System.out.println("waking from take");
		}
		System.out.println("exiting liftOffRunner");
	}
}

public class TestBlockingQueues {
	static void getkey() {
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	static void getkey(String message) {
		System.out.println(message);
		getkey();
	}

	static void test(String msg, BlockingQueue<LiftOff> queue) {
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for (int i = 0; i < 5; i++) {
			runner.add(new LiftOff(5));
		}
		getkey("press 'Enter' (" + msg + ")");
		t.interrupt();
		System.out.println("finished " + msg + " test");
	}

	public static void main(String[] args) {
		test("linkedBlockingQueue",new LinkedBlockingDeque<>());
		test("arrayBlockingQueue",new ArrayBlockingQueue<>(3));
		test("synchronousQueue",new SynchronousQueue<>());
	}
}
























