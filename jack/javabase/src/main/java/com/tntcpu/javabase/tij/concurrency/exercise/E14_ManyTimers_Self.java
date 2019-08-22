package com.tntcpu.javabase.tij.concurrency.exercise;

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
class CreateTimer implements Runnable {
	private Logger logger = LoggerFactory.getLogger(CreateTimer.class);
	private AtomicInteger i = new AtomicInteger(0);

	@Override
	public void run() {
		i.addAndGet(1);
		for (int j = 0; j < i.get(); j++) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					logger.info(Thread.currentThread().getName() + " exiting");
				}
			}, 2000);
		}
	}
}

public class E14_ManyTimers_Self {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			exec.execute(new CreateTimer());
			Thread.sleep(1000);
		}
		Thread.yield();
		exec.shutdown();
	}
}
