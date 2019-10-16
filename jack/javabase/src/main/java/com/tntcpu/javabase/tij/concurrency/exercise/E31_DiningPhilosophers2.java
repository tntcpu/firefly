package com.tntcpu.javabase.tij.concurrency.exercise;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-16
 **/
class Chopstick {
	private final int id;
	private boolean taken;
	public Chopstick(int id) { this.id = id;}
	public synchronized void take() throws InterruptedException {
		while (taken) {
			wait();
		}
		taken = true;
	}
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
	public String toString(){return "Chopstick " + id;}
}
class ChopstickQueue extends LinkedBlockingQueue<Chopstick>{}
class ChopstickBin{
	private ChopstickQueue bin = new ChopstickQueue();
	public Chopstick get() throws InterruptedException { return bin.take();}
	public void put(Chopstick chopstick) throws InterruptedException {bin.put(chopstick);}
}
class Philosopher implements Runnable {
	private static Random rand = new Random(47);
	private final int id;
	private final int ponderFactor;
	private ChopstickBin bin;
	private void pause() throws InterruptedException {
		if (ponderFactor == 0){return;}
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor*250));
	}
	public Philosopher(int id, int ponderFactor, ChopstickBin bin) {
		this.id = id;
		this.ponderFactor = ponderFactor;
		this.bin = bin;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				System.out.println(this + " " + "thinking");
				pause();
				Chopstick c1 = bin.get();
				System.out.println(this + " has " + c1 + " waiting for another one");
				Chopstick c2 = bin.get();
				System.out.println(this + " has " + c2);
				System.out.println(this + " eanting");
				pause();
				bin.put(c1);
				bin.put(c2);
			}
		} catch (Exception e) {
			System.out.println(this + " " + "exiting via interrupt");
		}
	}
	public String toString(){return "Philosopher "+ id;}
}
public class E31_DiningPhilosophers2 {
	public static void main(String[] args) throws InterruptedException, IOException {
		int ponder = 0, size = 5;
		ChopstickBin bin = new ChopstickBin();
		for (int i = 0; i < size; i++) {
			bin.put(new Chopstick(i));
		}
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < size; i++) {
			exec.execute(new Philosopher(i,ponder,bin));
		}
		System.out.println("press 'enter' to quit");
		System.in.read();
		exec.shutdownNow();
	}
}








































