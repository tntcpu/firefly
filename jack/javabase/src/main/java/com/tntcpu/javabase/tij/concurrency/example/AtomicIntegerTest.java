package com.tntcpu.javabase.tij.concurrency.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-22
 **/

@Slf4j
public class AtomicIntegerTest implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(AtomicIntegerTest.class);
	private AtomicInteger i = new AtomicInteger(0);

	public int getValue() {
		return i.get();
	}

	public void evenIncrement() {
		i.addAndGet(2);
	}

	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				logger.error("Aborting");
				System.exit(0);
			}
		}, 10000);
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest ait = new AtomicIntegerTest();
		exec.execute(ait);
		while (true) {
			int val = ait.getValue();
			if (val % 2 != 0) {
				logger.error("val: " + val);
				System.exit(0);
			}
		}
	}


}
