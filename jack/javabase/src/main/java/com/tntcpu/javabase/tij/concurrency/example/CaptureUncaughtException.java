package com.tntcpu.javabase.tij.concurrency.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-13
 **/
@Slf4j
class ExceptionThread2 implements Runnable {
	private Logger logger = LoggerFactory.getLogger(ExceptionThread2.class);

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		logger.info("run() by " + t);
		logger.info("eh = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

@Slf4j
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(MyUncaughtExceptionHandler.class);

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.info("caught " + e);
	}
}

@Slf4j
class HandlerThreadFactory implements ThreadFactory {
	private Logger logger = LoggerFactory.getLogger(HandlerThreadFactory.class);

	@Override
	public Thread newThread(Runnable r) {
		logger.info(this + " creating new Thread");
		Thread t = new Thread(r);
		logger.info("created " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		logger.info("eh = " + t.getUncaughtExceptionHandler());
		return t;
	}
}

public class CaptureUncaughtException {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
	}
}
