package com.tntcpu.javabase.tij.concurrency.exercise;

import com.tntcpu.javabase.tij.concurrency.example.MyUncaughtExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-21
 **/
class SafeTank extends Tank {

	@Override
	public synchronized void validate() {
		super.validate();
	}

	@Override
	public synchronized void fill() {
		super.fill();
	}

	@Override
	public synchronized void drain() {
		super.drain();
	}
}

@Slf4j
public class E11_RaceConditionB {
	private static Logger logger = LoggerFactory.getLogger(E11_RaceConditionB.class);

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		logger.info("press control-c to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		Tank tank = new SafeTank();
		for (int i = 0; i < 10; i++) {
			exec.execute(new ConsistencyChecker(tank));
		}
		Thread.yield();
		exec.shutdown();
	}
}
