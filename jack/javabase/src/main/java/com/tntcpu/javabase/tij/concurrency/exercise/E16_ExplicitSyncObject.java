package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-28
 **/
class ExplicitSingleSynch {
	private Lock lock = new ReentrantLock();

	public void f() {
		lock.lock();
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("f()");
				Thread.yield();
			}
		} finally {
			lock.unlock();
		}
	}

	public void g() {
		lock.lock();
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		} finally {
			lock.unlock();
		}
	}

	public void h() {
		lock.lock();
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("h()");
				Thread.yield();
			}
		} finally {
			lock.unlock();
		}
	}
}

class ExplicitTripleSynch {
	private Lock lockF = new ReentrantLock();
	private Lock lockG = new ReentrantLock();
	private Lock lockH = new ReentrantLock();

	public void f() {
		lockF.lock();
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("f()");
				Thread.yield();
			}
		} finally {
			lockF.unlock();
		}
	}

	public void g() {
		lockG.lock();
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		} finally {
			lockG.unlock();
		}
	}

	public void h() {
		lockH.lock();
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("h()");
				Thread.yield();
			}
		} finally {
			lockH.unlock();
		}
	}
}

public class E16_ExplicitSyncObject {
	public static void main(String[] args) throws InterruptedException {
		final ExplicitSingleSynch singleSynch = new ExplicitSingleSynch();
		final ExplicitTripleSynch tripleSynch = new ExplicitTripleSynch();
		System.out.println("single...");
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
		System.out.println("triple...");
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
