package com.tntcpu.javabase.tij.concurrency.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-07-26
 **/
//普通内部类继承Thread方式
class InnerThread1 {
	private int countDown = 5;
	private Inner inner;
	private static Logger logger = LoggerFactory.getLogger(InnerThread1.class);

	private class Inner extends Thread {
		Inner(String name) {
			super(name);
			start();
		}

		@Override
		public void run() {
			try {
				while (true) {
					logger.info("this {} ", this);
					if (--countDown == 0) {
						return;
					}
					sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return getName() + ": " + countDown;
		}
	}

	public InnerThread1(String name) {
		inner = new Inner(name);
	}
}

//匿名内部类Thread方式
class InnerThread2 {
	private Logger logger = LoggerFactory.getLogger(InnerThread2.class);
	private int countDown = 5;
	private Thread t;

	public InnerThread2(String name) {
		t = new Thread(name) {
			@Override
			public void run() {
				try {
					while (true) {
						logger.info("this {}", this);
						if (--countDown == 0) {
							return;
						}
						sleep(10);
					}
				} catch (InterruptedException e) {
					logger.error("sleep() interrupted");
				}
			}

			@Override
			public String toString() {
				return getName() + ": " + countDown;
			}
		};
		t.start();
	}
}

//普通内部类实现Runnable方式
class InnerRunnable1 {
	private Logger logger = LoggerFactory.getLogger(InnerRunnable1.class);
	private int countDown = 5;
	private Inner inner;

	private class Inner implements Runnable {
		Thread t;

		Inner(String name) {
			t = new Thread(this, name);
		}

		@Override
		public void run() {
			try {
				while (true) {
					logger.info("this {}", this);
					if (--countDown == 0) {
						return;
					}
					TimeUnit.MILLISECONDS.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public String toString() {
			return t.getName() + ": " + countDown;
		}
	}

	public InnerRunnable1(String name) {
		inner = new Inner(name);
	}
}

//匿名内部类Runnable方式
class InnerRunnable2 {
	private Logger logger = LoggerFactory.getLogger(InnerRunnable2.class);
	private int countDown = 5;
	private Thread t;

	public InnerRunnable2(String name) {
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						logger.info("this {}", this);
						if (--countDown == 0) {
							return;
						}
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					logger.info("sleep() interrupted");
				}
			}

			@Override
			public String toString() {
				return Thread.currentThread().getName() + ": " + countDown;
			}
		}, name);
		t.start();
	}
}

class ThreadMethod {
	private Logger logger = LoggerFactory.getLogger(ThreadMethod.class);
	private int countDown = 5;
	private Thread t;
	private String name;

	public ThreadMethod(String name) {
		this.name = name;
	}

	public void runTask() {
		if (t == null) {
			t = new Thread(name) {
				@Override
				public void run() {
					try {
						while (true) {
							logger.info("this {} ", this);
							if (--countDown == 0) {
								return;
							}
							sleep(10);
						}
					} catch (InterruptedException e) {
						logger.info("sleep() interrupted");
					}
				}

				@Override
				public String toString() {
					return getName() + ": " + countDown;
				}
			};
			t.start();
		}
	}

}

@Slf4j
public class ThreadVarations {
	private static Logger logger = LoggerFactory.getLogger(ThreadVarations.class);

	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		new InnerThread2("InnerThread2");
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");
	}
}
