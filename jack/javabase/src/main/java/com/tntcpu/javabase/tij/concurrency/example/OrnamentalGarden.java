package com.tntcpu.javabase.tij.concurrency.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
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
class Count {
	private int count = 0;
	private Random random = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (random.nextBoolean()) {
			Thread.yield();
		}
		return (count = ++temp);
	}

	public synchronized int value() {
		return count;
	}
}

@Slf4j
class Entrance implements Runnable {
	private Logger logger = LoggerFactory.getLogger(Entrance.class);
	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<>();
	private int number = 0;
	private final int id;
	private static volatile boolean canceled = false;

	public static void cancel() {
		canceled = true;
	}

	public Entrance(int id) {
		this.id = id;
		entrances.add(this);
	}

	@Override
	public void run() {
		while (!canceled) {
			synchronized (this) {
				++number;
			}
			logger.info(this + " Total: " + count.increment());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				logger.info("sleep interrupted");
			}
		}
		logger.info("Stopping " + this);
	}

	public synchronized int getValue() {
		return number;
	}

	@Override
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumEntrances() {
		int sum = 0;
		for (Entrance entrance : entrances) {
			sum += entrance.getValue();
		}
		return sum;
	}
}
@Slf4j
public class OrnamentalGarden {
	private static Logger logger = LoggerFactory.getLogger(OrnamentalGarden.class);

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			exec.execute(new Entrance(i));
		}
		TimeUnit.MILLISECONDS.sleep(5000);
		Entrance.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(5000, TimeUnit.MICROSECONDS)){logger.info("Some tasks were not terminated!");}
		logger.info("Total: "+Entrance.getTotalCount());
		logger.info("Sum of Entrances: "+Entrance.sumEntrances());
	}
}
