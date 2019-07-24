package com.tntcpu.javabase.tij.concurrency.exercise;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-07-24
 **/
public class E01_Runnable_Self {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Printer_Self());
			t.start();
			System.out.println("closed!");
		}
	}
}

class Printer_Self implements Runnable{

	public Printer_Self(){
		System.out.println("started!");
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("test");
			Thread.yield();
		}
	}
}
