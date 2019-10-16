package com.tntcpu.javabase.tij.concurrency.example;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-16
 **/
class Sender implements Runnable {
	private Random random = new Random(47);
	private PipedWriter out = new PipedWriter();
	public PipedWriter getOut() {
		return out;
	}
	@Override
	public void run() {
		try {
			while (true){
				for (char c = 'A'; c <= 'z'; c++){
					out.write(c);
					TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
				}
			}
		} catch (IOException e) {
			System.out.println(e + " sender write exception");
		}catch (InterruptedException e){
			System.out.println(e + " sender sleep exception");
		}
	}
}
class Receiver implements Runnable {
	private PipedReader in;

	public Receiver(Sender sender) throws IOException {
		this.in = new PipedReader(sender.getOut());
	}
	@Override
	public void run() {
		try {
			while (true){
				System.out.println("Read: " + (char)System.in.read() + ", ");
			}
		} catch (IOException e) {
			System.out.println(e + " receiver read exception");
		}
	}
}
public class PipedIO {
	public static void main(String[] args) throws IOException, InterruptedException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}






















