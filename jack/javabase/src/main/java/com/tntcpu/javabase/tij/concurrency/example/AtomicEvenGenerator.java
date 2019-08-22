package com.tntcpu.javabase.tij.concurrency.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-22
 **/
public class AtomicEvenGenerator extends IntGenerator {
	private AtomicInteger currentEvenValue = new AtomicInteger(0);

	@Override
	public int next() {
		return currentEvenValue.addAndGet(2);
	}

	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}
}
