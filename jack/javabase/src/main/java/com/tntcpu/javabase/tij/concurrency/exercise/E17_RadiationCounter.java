package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-29
 **/
class Count {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (rand.nextBoolean()) {
			Thread.yield();
		}
		return (count = ++temp);
	}

	public synchronized int value() {
		return count;
	}
}

class Sensor implements Runnable {
	private static Random rand = new Random(47);
	private static Count count = new Count();
	private static List<Sensor> sensors = new ArrayList<>();
	private int number;
	private final int id;
	private static volatile boolean canceled = false;

	public static void cancel() {
		canceled = true;
	}

	public Sensor(int id) {
		this.id = id;
		sensors.add(this);
	}

	@Override
	public void run() {
		while (!canceled){
			if (rand.nextInt(5)==0){
				synchronized (this){++number;}
				count.increment();
			}
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized int getValue(){return number;}
	@Override
	public String toString(){return "Sensor "+id+":" + getValue();}
	public static int getTotalCount(){return count.value();}
	public static int sumSensors(){
		int sum = 0;
		for (Sensor sensor:sensors){
			sum += sensor.getValue();
		}
		return sum;
	}
}

public class E17_RadiationCounter {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Sensor(i));
		}
		TimeUnit.SECONDS.sleep(5);
		Sensor.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(250,TimeUnit.MILLISECONDS)){
			System.out.println("some tasks were not terminated!");
		}
		System.out.println("Total: "+Sensor.getTotalCount());
		System.out.println("sum of sensors:" + Sensor.sumSensors());
	}
}
