package com.tntcpu.javabase.tij.concurrency.exercise;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-07
 **/
class FibonacciSum2 {
	private static ExecutorService exec;

	private static int fib(int n) {
		if (n < 2) {
			return 1;
		}
		return fib(n - 2) + fib(n - 1);
	}

	public static synchronized Future<Integer> runTask(final int n) {
		assert exec != null;
		return exec.submit(() -> {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				sum += fib(i);
			}
			return sum;
		});
	}

	public static synchronized void init() {
		if (exec == null) {
			exec = Executors.newCachedThreadPool();
		}
	}

	public static synchronized void shutdown() {
		if (exec != null) {
			exec.shutdown();
		}
		exec = null;
	}
}

@Slf4j
public class E10_FibonacciSum2 {
	private static Logger logger = LoggerFactory.getLogger(E10_FibonacciSum2.class);

	public static void main(String[] args) {
		ArrayList<Future<Integer>> results = new ArrayList<>();
		FibonacciSum2.init();
		for (int i = 1; i <= 5; i++) {
			results.add(FibonacciSum2.runTask(i));
		}
		Thread.yield();
		FibonacciSum2.shutdown();
		for (Future<Integer> fi : results) {
			try {
				logger.info("{}", fi.get());
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
			} catch (ExecutionException e) {
				logger.error(e.getMessage());
			}
		}
	}
}
