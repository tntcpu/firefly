package com.tntcpu.javabase.tij.concurrency.exercise;

import com.tntcpu.javabase.tij.util.Generator;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-07-24
 **/
public class E05_FibonacciSum {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<Integer>>  results = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			results.add(exec.submit(new FibonacciSum(i)));
		}
		Thread.yield();
		exec.shutdown();
		for (Future<Integer> fs : results){
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}

class FibonacciSum implements Generator<Integer>, Callable<Integer> {
	private int count;
	private final int n;

	FibonacciSum(int n) {
		this.n = n;
	}

	@Override
	public Integer next() {
		return fib(count++);
	}

	private Integer fib(int n) {
		if (n < 2) {
			return 1;
		}
		return fib(n - 2) + fib(n - 1);
	}

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += next();
		}
		return sum;
	}
}
