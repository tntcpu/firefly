package com.tntcpu.javabase.tij.concurrency.example.component;

import com.tntcpu.javabase.tij.util.BasicGenerator;
import com.tntcpu.javabase.tij.util.Generator;

import java.util.List;
import java.util.concurrent.*;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-22
 **/
class ExchangerProducer<T> implements Runnable {
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
			while (!Thread.interrupted()) {
				for (int i = 0; i < ExchangerDemo.size; i++) {
					holder.add(generator.next());
				}
				holder = exchanger.exchange(holder);
			}
		} catch (Exception e) {
		}
	}
}

class ExchangerConsumer<T> implements Runnable {
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
			while (!Thread.interrupted()) {
				holder = exchanger.exchange(holder);
				for (T x : holder) {
					value = x;
					holder.remove(x);
				}
			}
		} catch (Exception e) {
		}
		System.out.println("final value: " + value);
	}
}

public class ExchangerDemo {
	static int size = 10;
	static int delay = 5;

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> xc = new Exchanger<>();
		List<Fat> producerList = new CopyOnWriteArrayList<>(), consumerList = new CopyOnWriteArrayList<>();
		exec.execute(new ExchangerProducer<>(BasicGenerator.create(Fat.class), xc, producerList));
		exec.execute(new ExchangerConsumer<>(xc,consumerList));
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();
	}
}






















