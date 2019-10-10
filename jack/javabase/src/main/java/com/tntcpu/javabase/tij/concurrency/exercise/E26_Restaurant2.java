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

class E26_Meal {
	private final int counter;
	public E26_Meal(int counter) {
		this.counter = counter;
	}
	public String toString(){
		return "Meal "+counter;
	}
}
class E26_Chef implements Runnable{
	private int orderNum = 0;
	private E26_Restaurant2 restaurant;
	public E26_Chef(E26_Restaurant2 restaurant) {
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
					restaurant.meal = new E26_Meal(orderNum);
					restaurant.waiter.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (Exception e) {
			System.out.println("chef interrupted");
		}
	}
}
class E26_Waiter implements Runnable{
	private E26_Restaurant2 restaurant;
	boolean notified;
	public E26_Waiter(E26_Restaurant2 restaurant) {
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
				synchronized (restaurant.busBoy){
					restaurant.busBoy.notified = true;
					restaurant.busBoy.meal = restaurant.meal;
					restaurant.busBoy.notifyAll();
				}
				synchronized (restaurant.chef){
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
				synchronized (this){
					if (!notified){
						wait();
					}
					notified = false;
				}
			}
		} catch (Exception e) {
			System.out.println("waiter interrupted");
		}
	}
}
class BusBoy implements Runnable{
	boolean notified;
	volatile E26_Meal meal;
	private E26_Restaurant2 restaurant;
	public BusBoy(E26_Restaurant2 restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				synchronized (this){
					if (!notified){
						wait();
					}
					notified = false;
				}
				System.out.println("clean up " + meal);
				synchronized (restaurant.waiter){
					restaurant.waiter.notified = true;
					restaurant.waiter.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("busboy interrupted");
		}
	}
}
public class E26_Restaurant2 {
	E26_Meal meal;
	E26_Chef chef = new E26_Chef(this);
	E26_Waiter waiter = new E26_Waiter(this);
	BusBoy busBoy = new BusBoy(this);
	ExecutorService exec = Executors.newCachedThreadPool();

	public E26_Restaurant2() {
		exec.execute(waiter);
		exec.execute(chef);
		exec.execute(busBoy);
	}

	public static void main(String[] args) {
		new E26_Restaurant2();
	}
}
