package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-10
 **/
//队列
class FlowQueue<T> {
	private Queue<T> queue = new LinkedList<T>();
	private int maxSize;
	public FlowQueue(int maxSize) { this.maxSize = maxSize; }
	public synchronized void put(T v) throws InterruptedException {
		while (queue.size() >= maxSize){ wait();}
		queue.offer(v);
		maxSize++;
		notifyAll();
	}
	public synchronized T get() throws InterruptedException {
		while (queue.isEmpty()){wait();}
		T returnVal = queue.poll();
		maxSize--;
		notifyAll();
		return returnVal;
	}
}
//队列中的元素
class Item{
	private static int counter;
	private int id = counter++;
	public String toString(){return "Item " + id; }
}
//生产者
class Producer implements Runnable {
	private int delay;
	private FlowQueue<Item> output;

	public Producer(int sleepTime, FlowQueue<Item> output) {
		delay = sleepTime;
		this.output = output;
	}
	@Override
	public void run() {
		for (;;){
			try {
				output.put(new Item());
				TimeUnit.MILLISECONDS.sleep(delay);
			} catch (InterruptedException e) {return;}
		}
	}
}
//消费者
class Consumer implements Runnable{
	private int delay;
	private FlowQueue<Item> input;
	public Consumer(int sleepTime, FlowQueue<Item> input) {
		delay = sleepTime;
		this.input = input;
	}
	@Override
	public void run() {
		for (;;){
			try {
				System.out.println(input.get());
				TimeUnit.MILLISECONDS.sleep(delay);
			} catch (InterruptedException e) {return;}
		}

	}
}
public class E24_ProducerConsumer {
	public static void main(String[] args) throws InterruptedException {
		int producerSleep = 100;
		int consumerSleep = 100;
		FlowQueue<Item> fq = new FlowQueue<>(0);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Producer(producerSleep,fq));
		exec.execute(new Consumer(consumerSleep,fq));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}



























