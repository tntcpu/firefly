package com.tntcpu.javabase.tij.concurrency.example;

import java.nio.channels.SocketChannel;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-09-06
 **/
class NIOBlocked implements Runnable{
	private final SocketChannel sc;
	public NIOBlocked(SocketChannel sc){
		this.sc = sc;
	}
	@Override
	public void run() {
		System.out.println("");
	}
}
public class NIOInterruption {
}
