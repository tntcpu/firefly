package com.tntcpu.javabase.tij.concurrency.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-08-08
 **/
@Slf4j
public class NaiveExceptionHandling {
	private static Logger logger = LoggerFactory.getLogger(NaiveExceptionHandling.class);
	public static void main(String[] args) {
		ExecutorService exec = null;
		try {
			exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (Exception e) {
			logger.info("Exception has been handled");
		}
	}
}
