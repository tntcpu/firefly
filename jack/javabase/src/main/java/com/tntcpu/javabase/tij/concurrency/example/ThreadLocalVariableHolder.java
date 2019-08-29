package com.tntcpu.javabase.tij.concurrency.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-29
 **/
@Slf4j
class Accessor implements Runnable {
	private Logger logger = LoggerFactory.getLogger(Accessor.class);
	private final int id;
	public Accessor(int idn) {
		id = idn;
	}
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			logger.info("" + this);
			Thread.yield();
		}
	}
	@Override
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}
}
public class ThreadLocalVariableHolder {
	private static ThreadLocal<Integer> value = new ThreadLocal<>() {
		private Random rand = new Random(47);
		@Override
		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}
	};
	public static void increment() {
		value.set(value.get() + 1);
	}
	public static int get() {
		return value.get();
	}
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Accessor(i));
		}
		TimeUnit.MILLISECONDS.sleep(500);
		exec.shutdownNow();
	}
}
