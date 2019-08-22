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
class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;

	public CircularSet(int size) {
		array = new int[size];
		len = size;
		for (int i = 0; i < size; i++) {
			array[i] = -1;
		}
	}

	public synchronized void add(int i) {
		array[index] = i;
		index = ++index % len;
	}

	public synchronized boolean contains(int val) {
		for (int i = 0; i < len; i++) {
			if (array[i] == val) {
				return true;
			}
		}
		return false;
	}
}

class SerialNumberGenerator2 {
	private static int serialNumber;

	synchronized static int nextSerialNumber() {
		return serialNumber++;
	}
}

@Slf4j
public class E13_SerialNumberChecker2 {
	private static Logger logger = LoggerFactory.getLogger(E13_SerialNumberChecker2.class);

	private static final int SIZE = 10;
	private static CircularSet serials = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();

	static class SerialChecker implements Runnable {
		@Override
		public void run() {
			while (true) {
				int serial = SerialNumberGenerator2.nextSerialNumber();
				if (serials.contains(serial)) {
					logger.info("Duplicate: " + serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
		}
		TimeUnit.SECONDS.sleep(15);
		logger.info("No duplicates detected");
		System.exit(0);
	}
}
