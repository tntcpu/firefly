package com.tntcpu.javabase.tij.concurrency.exercise;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-28
 **/
class SingleSynch {
	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	public synchronized void g() {
		for (int i = 0; i < 5; i++) {
			System.out.println("g()");
			Thread.yield();
		}
	}

	public synchronized void h() {
		for (int i = 0; i < 5; i++) {
			System.out.println("h()");
			Thread.yield();
		}
	}
}

class TripleSynch {
	private Object synchObjectG = new Object();
	private Object synchObjectH = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (synchObjectG) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}

	public void h() {
		synchronized (synchObjectH) {
			for (int i = 0; i < 5; i++) {
				System.out.println("h()");
				Thread.yield();
			}
		}
	}

}

public class E15_SyncObject {
	public static void main(String[] args) throws InterruptedException {
		final SingleSynch singleSynch = new SingleSynch();
		final TripleSynch tripleSynch = new TripleSynch();
		System.out.println("Test SingleSynch...");
		Thread t1 = new Thread() {
			@Override
			public void run() {
				singleSynch.f();
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			@Override
			public void run() {
				singleSynch.g();
			}
		};
		t2.start();
		singleSynch.h();
		t1.join();
		t2.join();
		System.out.println("Test TripleSynch...");
		new Thread() {
			@Override
			public void run() {
				tripleSynch.f();
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				tripleSynch.g();
			}
		}.start();
		tripleSynch.h();
	}
}
