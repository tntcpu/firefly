package com.tntcpu.javabase.tij.concurrency.exercise;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-09-29
 **/
public class E22_BusyWait {
	private static volatile boolean flag;
	private static int spins;

	public static void main(String[] args) throws InterruptedException {
		Runnable r1 = () -> {
			for (;;){
				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					return;
				}
				flag = true;
			}
		};
		Runnable r2 = () -> {
			for (;;){
				while (!flag && !Thread.interrupted()) {
					spins++;
				}
				System.out.println("Spun "+spins+" times");
				spins = 0;
				flag = false;
				if (Thread.interrupted()){
					return;
				}
			}
		};
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(r1);
		exec.execute(r2);
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
	}
}
