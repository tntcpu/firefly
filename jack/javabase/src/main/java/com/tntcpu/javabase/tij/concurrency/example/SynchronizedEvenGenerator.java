package com.tntcpu.javabase.tij.concurrency.example;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-21
 **/
public class SynchronizedEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
