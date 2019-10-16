package com.tntcpu.javabase.tij.concurrency.example.deadlock;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-16
 **/
public class DeadlockingDiningPhilosophers {
	public static void main(String[] args) throws InterruptedException, IOException {
		int ponder = 0,
			size = 5;
		ExecutorService exec = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		for (int i = 0; i < size; i++) { sticks[i] = new Chopstick();}
		for (int i = 0; i < size; i++) {
			exec.execute(new Philosopher(sticks[i],sticks[(i+1)%size],i,ponder));
		}
		if (args.length == 3 && args[2].equals("timeout")){
			TimeUnit.SECONDS.sleep(5);
		}else {
			System.out.println("press 'enter' to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}
}
