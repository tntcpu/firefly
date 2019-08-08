package com.tntcpu.javabase.tij.concurrency.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-07
 **/
@Slf4j
class Sleeper extends Thread {
	private Logger logger = LoggerFactory.getLogger(Sleeper.class);
	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}
	@Override
	public void run() {
		try {
			sleep(duration);
//			logger.info( getName() + " sleeping!");
		} catch (InterruptedException e) {
			logger.error(getName() + " was interrupted. " + "isInterrupted(): " + isInterrupted());
			logger.error(getName() + " has awakened");
		}
	}
}

@Slf4j
class Joiner extends Thread {
	private Logger logger = LoggerFactory.getLogger(Joiner.class);
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}

	@Override
	public void  run(){
		try {
			sleeper.join();
			logger.info("test");
		} catch (InterruptedException e) {
			logger.error("interrupted");
		}
		logger.info(getName() + " join completed!");
	}
}

public class Joining {
	public static void main(String[] args) {
		Sleeper
				sleepy = new Sleeper("Sleepy", 3000),
				grumpy = new Sleeper("Grumpy", 4000);
		Joiner
				dopey = new Joiner("Dopey", sleepy),
				doc = new Joiner("Doc", grumpy);
//		grumpy.interrupt();
	}
}
