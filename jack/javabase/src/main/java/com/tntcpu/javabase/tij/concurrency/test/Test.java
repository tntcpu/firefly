package com.tntcpu.javabase.tij.concurrency.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-11-21
 **/
public class Test {
	public static void main(String[] args) {
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String format = now.format(dft);
		System.out.println(format);
	}
}
