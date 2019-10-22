package com.tntcpu.javabase.tij.concurrency.exercise;

import com.tntcpu.javabase.tij.util.CountingGenerator;
import com.tntcpu.javabase.tij.util.Generator;

import java.util.List;
import java.util.concurrent.*;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-22
 **/
class ExchangerProducer<T> implements Runnable{
	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;

	public ExchangerProducer(Generator<T> generator, Exchanger<List<T>> exchanger, List<T> holder) {
		this.generator = generator;
		this.exchanger = exchanger;
		this.holder = holder;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				for (int i = 0; i < E34_ExchangerDemo2.size; i++) {
					holder.add(generator.next());
				}
				holder = exchanger.exchange(holder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class ExchangerConsumer<T> implements Runnable{
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;

	public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
		this.exchanger = exchanger;
		this.holder = holder;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()){
				holder = exchanger.exchange(holder);
				for (T x:holder){
					value = x;
					holder.remove(x);
				}
			}
		} catch (Exception e) {

		}
		System.out.println("final value: "+value);
	}
}
public class E34_ExchangerDemo2 {
	static int size = 10;
	static int delay = 2;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Integer>> xc = new Exchanger<>();
		List<Integer> producerList = new CopyOnWriteArrayList<>(),
				consumerList = new CopyOnWriteArrayList<>();
		exec.execute(new ExchangerProducer<>(new CountingGenerator.Integer(),xc,producerList));
		exec.execute(new ExchangerConsumer<>(xc,consumerList));
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();
	}
}











