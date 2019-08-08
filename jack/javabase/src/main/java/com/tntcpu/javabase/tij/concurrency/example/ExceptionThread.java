package com.tntcpu.javabase.tij.concurrency.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-08
 **/
public class ExceptionThread implements Runnable {
	@Override
	public void run() {
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
}
