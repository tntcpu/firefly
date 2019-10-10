package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-10
 **/

class E261_Meal {
	private final int counter;
	public E261_Meal(int counter) {
		this.counter = counter;
	}
	public String toString(){
		return "Meal "+counter;
	}
}

class E261_Chef implements Runnable{
	private int orderNum = 0;
	private E26_Restaurant2_Self restaurant;
	public E261_Chef(E26_Restaurant2_Self restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				synchronized (this){
					while (restaurant.meal != null){wait();}
				}
				if (++orderNum == 10){
					System.out.println("out of needs");
					restaurant.exec.shutdownNow();
					return;
				}
				System.out.print("order up!");
				synchronized (restaurant.waiter){
					restaurant.meal = new E261_Meal(orderNum);
					restaurant.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (Exception e) {
			System.out.println("chef interrupted");
		}
	}
}

class E261_Waiter implements Runnable{
	private E26_Restaurant2_Self restaurant;
	public E261_Waiter(E26_Restaurant2_Self restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				synchronized (this){
					while (restaurant.meal == null){wait();}
				}
				System.out.println("got "+restaurant.meal);
				synchronized (restaurant.busBoy){
					restaurant.busBoy.notifyAll();
				}
				synchronized (restaurant.chef){
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch (Exception e) {
			System.out.println("waiter interrupted");
		}
	}
}

class E26_BusBoy implements Runnable{
	private E26_Restaurant2_Self restaurant;
	public E26_BusBoy(E26_Restaurant2_Self restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				synchronized (this){
					wait();
				}
				System.out.println("cleaned up!");
			}
		} catch (Exception e) {
			System.out.println("busBoy interrupted");
		}
	}
}

public class E26_Restaurant2_Self {
	E261_Meal meal;
	E261_Chef chef = new E261_Chef(this);
	E261_Waiter waiter = new E261_Waiter(this);
	E26_BusBoy busBoy = new E26_BusBoy(this);
	ExecutorService exec = Executors.newCachedThreadPool();

	public E26_Restaurant2_Self() {
		exec.execute(waiter);
		exec.execute(chef);
		exec.execute(busBoy);
	}

	public static void main(String[] args) {
		new E26_Restaurant2_Self();
	}
}
