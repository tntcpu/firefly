package com.tntcpu.javabase.tij.concurrency.example;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-12
 **/
class Toast {
	public enum Status {DRY, BUTTERED, JAMMED}
	private Status status = Status.DRY;
	private final int id;
	public Toast(int idn) { id = idn; }
	public void butter() {status = Status.BUTTERED;}
	public void jam(){status = Status.JAMMED;}
	public Status getStatus(){return status;}
	public int getId(){return id;}
	public String toString(){return "Toast " + id + ": "+ status;}
}
class ToastQueue extends LinkedBlockingQueue<Toast>{}
class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	public Toaster(ToastQueue toastQueue) { this.toastQueue = toastQueue;}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println(t);
			}
		} catch (InterruptedException e) {
			System.out.println("toaster interrupted");
		}
		System.out.println("toaster off");
	}
}
class Butterer implements Runnable{

	@Override
	public void run() {

	}
}
public class ToastMatic {
}
