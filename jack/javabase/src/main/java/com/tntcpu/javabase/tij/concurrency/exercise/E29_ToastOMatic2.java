package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-14
 **/
class Toast {
	public enum Status {
		DRY, BUTTERED, JAMMED, READY {
			public String toString() {
				return BUTTERED.toString() + " & " + JAMMED.toString();
			}
		}
	}
	private Status status = Status.DRY;
	private final int id;
	public Toast(int id) { this.id = id;}
	public void butter() {status = (status == Status.DRY) ? Status.BUTTERED : Status.READY;}
	public void jam() {status = (status == Status.DRY) ? Status.JAMMED : Status.READY;}
	public Status getStatus() {return status;}
	public int getId() {return id;}
	public String toString() {return "Toast" + id + " : " + status;}
}
class ToastQueue extends LinkedBlockingQueue<Toast> {}
class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count;
	private Random rand = new Random(47);
	public Toaster(ToastQueue toastQueue) {this.toastQueue = toastQueue;}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				Toast t = new Toast(count++);
				System.out.println(t);
				toastQueue.put(t);
			}
		} catch (Exception e) {
			System.out.println("toaster interrupted");
		}
		System.out.println("toaster off");
	}
}
class Butterer implements Runnable {
	private ToastQueue inQueue, butteredQueue;
	public Butterer(ToastQueue inQueue, ToastQueue butteredQueue) {
		this.inQueue = inQueue;
		this.butteredQueue = butteredQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = inQueue.take();
				t.butter();
				System.out.println(t);
				butteredQueue.put(t);
			}
		} catch (Exception e) {
			System.out.println("butter interrupted");
		}
		System.out.println("butterer off");
	}
}
class Jammer implements Runnable {
	private ToastQueue inQueue, jammedQueue;
	public Jammer(ToastQueue inQueue, ToastQueue jammedQueue) {
		this.inQueue = inQueue;
		this.jammedQueue = jammedQueue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = inQueue.take();
				t.jam();
				System.out.println(t);
				jammedQueue.put(t);
			}
		} catch (Exception e) {
			System.out.println("jammer interrupted");
		}
		System.out.println("jammer off");
	}
}
class Eater implements Runnable {
	private ToastQueue finishedQueue;
	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				if (t.getStatus() != Toast.Status.READY) {
					System.out.println(">>>> Error: " + t);
					System.exit(1);
				} else {
					System.out.println("Chomp! " + t);
				}
			}
		} catch (Exception e) {
			System.out.println("Eater interrupted");
		}
		System.out.println("Eater off");
	}
}
class Alternator implements Runnable {
	private ToastQueue inQueue, out1Queue, out2Queue;
	private boolean outTo2;
	public Alternator(ToastQueue inQueue, ToastQueue out1Queue, ToastQueue out2Queue) {
		this.inQueue = inQueue;
		this.out1Queue = out1Queue;
		this.out2Queue = out2Queue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = inQueue.take();
				if (!outTo2) {
					out1Queue.put(t);
				} else {
					out2Queue.put(t);
				}
				outTo2 = !outTo2;
			}
		} catch (Exception e) {
			System.out.println("alternator interrupted");
		}
		System.out.println("alternator off");
	}
}
class Merger implements Runnable {
	private ToastQueue in1Queue, in2Queue, toBeButteredQueue, toBeJammedQueue, finishedQueue;

	public Merger(ToastQueue in1Queue, ToastQueue in2Queue, ToastQueue toBeButteredQueue, ToastQueue toBeJammedQueue, ToastQueue finishedQueue) {
		this.in1Queue = in1Queue;
		this.in2Queue = in2Queue;
		this.toBeButteredQueue = toBeButteredQueue;
		this.toBeJammedQueue = toBeJammedQueue;
		this.finishedQueue = finishedQueue;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = null;
				while (t == null) {
					t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
					if (t != null){
						break;
					}
					t = in2Queue.poll(50,TimeUnit.MILLISECONDS);
				}
				switch (t.getStatus()){
					case BUTTERED:
						toBeJammedQueue.put(t);
						break;
					case JAMMED:
						toBeButteredQueue.put(t);
						break;
					default:
						finishedQueue.put(t);
				}
			}
		} catch (Exception e) {
			System.out.println("merger interrupted");
		}
		System.out.println("merger off");
	}
}

public class E29_ToastOMatic2 {
	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(),
				butteredQueue = new ToastQueue(),
				toBeButteredQueue = new ToastQueue(),
				jammedQueue = new ToastQueue(),
				toBeJammedQueue = new ToastQueue(),
				finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Alternator(dryQueue, toBeButteredQueue,toBeJammedQueue));
		exec.execute(new Butterer(toBeButteredQueue,butteredQueue));
		exec.execute(new Jammer(toBeJammedQueue, jammedQueue));
		exec.execute(new Merger(butteredQueue,jammedQueue,toBeButteredQueue,toBeJammedQueue,finishedQueue));
//		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}





























