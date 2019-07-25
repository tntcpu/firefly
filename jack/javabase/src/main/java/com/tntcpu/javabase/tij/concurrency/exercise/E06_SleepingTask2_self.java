package com.tntcpu.javabase.tij.concurrency.exercise;

import org.jetbrains.annotations.Contract;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-07-25
 **/
public class E06_SleepingTask2_self {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SleepRandom(i));
		}
		exec.shutdown();
	}
}

class SleepRandom implements Runnable{
	private final int n;

	SleepRandom(int n) {
		this.n = n;
	}

	@Override
	public void run() {
		double random = Math.random();
		int sleepSec = (int) (random*10+1);
		try {
			TimeUnit.SECONDS.sleep(sleepSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread()+" sleep "+ sleepSec + "S;n is "+ n);
	}
}
