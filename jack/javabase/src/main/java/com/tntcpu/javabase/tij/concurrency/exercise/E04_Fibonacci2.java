package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-07-24
 **/
public class E04_Fibonacci2 {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 1; i < 6; i++) {
			exec.execute(new Fibonacci(i));
		}
		Thread.yield();
		exec.shutdown();
		exec = Executors.newFixedThreadPool(5);
		for (int i = 1; i < 6; i++) {
			exec.execute(new Fibonacci(i));
		}
		Thread.yield();
		exec.shutdown();
		exec = Executors.newSingleThreadExecutor();
		for (int i = 1; i < 6; i++) {
			exec.execute(new Fibonacci(i));
		}
		Thread.yield();
		exec.shutdown();
	}
}
