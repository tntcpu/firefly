package com.tntcpu.javabase.tij.concurrency.exercise;

import com.tntcpu.javabase.tij.concurrency.example.LiftOff;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-09-16
 **/
class LiftOff2 implements Runnable {
	protected int countDown = 5000;
	private static int taskCount;
	private final int id = taskCount++;

	public LiftOff2(){}
	public LiftOff2(int countDown){
		this.countDown = countDown;
	}

	public String status(){
		return "#" + id + "(" + (countDown) +"), ";
	}

	@Override
	public void run() {
		while (countDown-- > 0){
			if (Thread.currentThread().isInterrupted()){
				System.out.println("Interrupted: #");
			}
		}
	}
}

public class E20_InterruptCachedThreadPool {
}
