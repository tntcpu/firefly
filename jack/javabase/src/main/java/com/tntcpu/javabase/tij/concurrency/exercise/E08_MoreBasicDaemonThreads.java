package com.tntcpu.javabase.tij.concurrency.exercise;

import com.tntcpu.javabase.tij.concurrency.example.LiftOff;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-07-25
 **/
public class E08_MoreBasicDaemonThreads {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new LiftOff());
			t.setDaemon(true);
			t.start();
		}
		System.out.println("Waiting for LiftOff");
	}
}
