package com.tntcpu.javabase.tij.concurrency.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-28
 **/
@Slf4j
class DualSynch {
	private Logger logger = LoggerFactory.getLogger(DualSynch.class);
	private Object syncObject = new Object();

	public synchronized void f() {
		for (int i = 0; i < 10; i++) {
			logger.info("f()");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 10; i++) {
				logger.info("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			@Override
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
	}
}

//class DualSynch {
//	private Object syncObject = new Object();
//	public synchronized void f() {
//		for(int i = 0; i < 5; i++) {
//			System.out.println("f()");
//			Thread.yield();
//		}
//	}
//	public void g() {
//		synchronized(syncObject) {
//			for(int i = 0; i < 5; i++) {
//				System.out.println("g()");
//				Thread.yield();
//			}
//		}
//	}
//}
//public class SyncObject {
//	public static void main(String[] args) {
//		final DualSynch ds = new DualSynch();
//		new Thread() {
//			public void run() {
//				ds.f();
//			}
//		}.start();
//		ds.g();
//	}
//}