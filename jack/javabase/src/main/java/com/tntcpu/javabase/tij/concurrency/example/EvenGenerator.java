package com.tntcpu.javabase.tij.concurrency.example;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-13
 **/
public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;

	@Override
//	synchronized
	public int next() {
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator(),2);
	}
}
