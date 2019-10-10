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
class E25_Meal {
	private final int counter;
	public E25_Meal(int counter) {
		this.counter = counter;
	}
	public String toString(){
		return "Meal "+counter;
	}
}
class E25_Chef implements Runnable{
	private int orderNum = 0;
	private E25_Restaurant restaurant;
	public E25_Chef(E25_Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				synchronized (this){
					if (restaurant.meal != null){wait();}
				}
				if (++orderNum == 10){
					System.out.println("out of needs");
					restaurant.exec.shutdownNow();
					return;
				}
				System.out.print("order up!");
				synchronized (restaurant.waiter){
					restaurant.meal = new E25_Meal(orderNum);
					restaurant.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (Exception e) {
			System.out.println("chef interrupted");
		}
	}
}
class E25_Waiter implements Runnable{
	private E25_Restaurant restaurant;
	public E25_Waiter(E25_Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				synchronized (this){
					if (restaurant.meal == null){wait();}
				}
				System.out.println("got "+restaurant.meal);
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
public class E25_Restaurant {
	E25_Meal meal;
	E25_Chef chef = new E25_Chef(this);
	E25_Waiter waiter = new E25_Waiter(this);
	ExecutorService exec = Executors.newCachedThreadPool();

	public E25_Restaurant() {
		exec.execute(waiter);
		exec.execute(chef);
	}

	public static void main(String[] args) {
		new E25_Restaurant();
	}
}
