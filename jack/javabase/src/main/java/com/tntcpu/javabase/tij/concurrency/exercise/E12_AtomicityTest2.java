package com.tntcpu.javabase.tij.concurrency.exercise;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-22
 **/
class AtomicityTest2 implements Runnable {
	private int i;

	public synchronized int getValue() {
		return i;
	}

	public synchronized void evenIncrement() {
		i++;
		i++;
	}

	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}
}

@Slf4j
public class E12_AtomicityTest2 {
	private static Logger logger = LoggerFactory.getLogger(E12_AtomicityTest2.class);

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest2 at = new AtomicityTest2();
		for (int i = 0; i < 10; i++) {
			exec.execute(at);
		}

		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				logger.info("val: "+val);
				System.exit(0);
			}
		}
	}
}
