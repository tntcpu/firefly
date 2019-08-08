package com.tntcpu.javabase.tij.concurrency.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-08
 **/
@Slf4j
class UnresponsiveUI {
	private Logger logger = LoggerFactory.getLogger(UnresponsiveUI.class);
	private volatile double d = 1;

	public UnresponsiveUI() throws IOException {
		while (d > 0) {
			d = d + (Math.E + Math.PI) / d;
		}
		System.in.read();
	}

}

@Slf4j
public class ResponsiveUI extends Thread {
	private static Logger logger = LoggerFactory.getLogger(ResponsiveUI.class);
	private static volatile double d = 1;

	public ResponsiveUI() {
		setDaemon(true);
		start();
	}

	@Override
	public void run() {
		while (true) {
			d = d + (Math.PI + Math.E) / d;
		}
	}

	public static void main(String[] args) throws IOException {
//		new UnresponsiveUI();
		new ResponsiveUI();
		System.in.read();
		logger.info(String.valueOf(d));
	}
}
