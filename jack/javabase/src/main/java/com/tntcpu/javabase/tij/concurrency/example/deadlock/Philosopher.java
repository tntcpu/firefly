package com.tntcpu.javabase.tij.concurrency.example.deadlock;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-16
 **/
public class Philosopher implements Runnable {
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);
	private void pause() throws InterruptedException {
		if (ponderFactor == 0){return;}
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor*500));
	}
	public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
		this.left = left;
		this.right = right;
		this.id = id;
		this.ponderFactor = ponderFactor;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				System.out.println(this + " " + "thinking");
				pause();
				System.out.println(this + " " + "grabbing right");
				right.take();
				System.out.println(this + " " + "grabbing left");
				left.take();
				System.out.println(this + " " + "eating");
				pause();
				right.drop();
				left.drop();
			}
		} catch (Exception e) {
			System.out.println(this + " " + "exiting via interrupt");
		}
	}
	public String toString(){return "Philosopher " + id;}
}

























