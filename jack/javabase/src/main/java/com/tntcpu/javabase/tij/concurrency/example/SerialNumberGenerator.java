package com.tntcpu.javabase.tij.concurrency.example;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-21
 **/
public class SerialNumberGenerator {
	private static volatile int serialNumber = 0;

	public synchronized static int nextSerialNumber() {
		return serialNumber++;
	}
}
