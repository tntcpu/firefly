package com.tntcpu.javabase.tij.concurrency;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-07-23
 **/
public class MainThread {
	public static void main(String[] args) {
		LiftOff launch = new LiftOff();
		launch.run();
	}
}
