package com.tntcpu.javabase.tij.concurrency.exercise;

import com.tntcpu.javabase.tij.concurrency.example.MyUncaughtExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-21
 **/

class Tank {
	enum State {EMPTY, LOADED}

	private State state = State.EMPTY;
	private int current_load = 0;

	public void validate() {
		if ((state == State.EMPTY && current_load != 0) || (state == State.LOADED && current_load == 0)) {
			throw new IllegalStateException();
		}
	}

	public void fill() {
		state = State.LOADED;
		Thread.yield();
		current_load = 10;
	}

	public void drain() {
		state = State.EMPTY;
		Thread.yield();
		current_load = 0;
	}
}

class ConsistencyChecker implements Runnable {
	private static Random rnd = new Random();
	private Tank tank;

	ConsistencyChecker(Tank tank) {
		this.tank = tank;
	}

	@Override
	public void run() {
		for (; ; ) {
			if (rnd.nextBoolean()) {
				tank.fill();
			} else {
				tank.drain();
			}
			tank.validate();
		}
	}
}

@Slf4j
public class E11_RaceCondition {
	private static Logger logger = LoggerFactory.getLogger(E11_RaceCondition.class);

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		logger.info("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		Tank tank = new Tank();
		for (int i = 0; i < 10; i++) {
			exec.execute(new ConsistencyChecker(tank));
		}
		Thread.yield();
		exec.shutdown();
	}
}
