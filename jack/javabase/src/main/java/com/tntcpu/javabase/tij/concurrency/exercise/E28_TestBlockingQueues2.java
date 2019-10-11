package com.tntcpu.javabase.tij.concurrency.exercise;

import com.tntcpu.javabase.tij.concurrency.example.LiftOff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-11
 **/
class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
		this.rockets = rockets;
	}
	public void add(LiftOff lo) throws InterruptedException {
		rockets.put(lo);
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				LiftOff rocket = rockets.take();
				rocket.run();
			}
		} catch (InterruptedException e) {
			System.out.println("waking from take");
		}
		System.out.println("exiting liftOffRunner");
	}
}
class LiftOffProducer implements Runnable{
	private LiftOffRunner liftOffRunner;
	public LiftOffProducer(LiftOffRunner liftOffRunner) { this.liftOffRunner = liftOffRunner;}
	@Override
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				liftOffRunner.add(new LiftOff(5));
			}
		} catch (InterruptedException e) {
			System.out.println("waking from put");
		}
		System.out.println("exiting liftOffProducer");
	}
}
public class E28_TestBlockingQueues2 {
	private static void getkey(){
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	private static void getkey(String msg){
		System.out.println(msg);
		getkey();
	}
	private static void test(String msg,BlockingQueue<LiftOff> queue){
		ExecutorService exec = Executors.newFixedThreadPool(2);
		System.out.println(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		LiftOffProducer producer = new LiftOffProducer(runner);
		exec.execute(runner);
		exec.execute(producer);
		getkey("press 'ENTER' (" + msg + ")");
		exec.shutdownNow();
		System.out.println("finished "+msg+" test");
	}

	public static void main(String[] args) {
		test("linked",new LinkedBlockingQueue<>());
		test("array",new ArrayBlockingQueue<>(3));
		test("sync",new SynchronousQueue<>());
	}
}

























