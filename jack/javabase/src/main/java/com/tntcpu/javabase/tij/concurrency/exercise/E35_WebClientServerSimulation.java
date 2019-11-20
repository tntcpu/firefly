package com.tntcpu.javabase.tij.concurrency.exercise;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-11-19
 **/
class WebClient {
	private final int serviceTime;

	public WebClient(int tm) {
		serviceTime = tm;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public String toString() {
		return "[" + serviceTime + "]";
	}
}

class WebClientLine extends ArrayBlockingQueue<WebClient> {
	public WebClientLine(int capacity) {
		super(capacity);
	}

	public String toString() {
		if (this.size() == 0) {
			return "[Empty]";
		}
		StringBuilder result = new StringBuilder();
		for (WebClient client : this) {
			result.append(client);
		}
		return result.toString();
	}
}

class WebClientGenerator implements Runnable {
	private WebClientLine clients;
	volatile int loadFator = 1;
	private static Random rand = new Random(47);

	public WebClientGenerator(WebClientLine cq) {
		clients = cq;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				clients.put(new WebClient(rand.nextInt(1000)));
				TimeUnit.MILLISECONDS.sleep(1000 / loadFator);
			}
		} catch (InterruptedException e) {
			System.out.println("webclientGenerator interrupted");
		}
		System.out.println("webclientgenerator terminating");
	}
}

class Server implements Runnable {
	private static int counter;
	private final int id = counter++;
	private WebClientLine clients;

	public Server(WebClientLine cq) {
		clients = cq;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				WebClient client = clients.take();
				TimeUnit.MILLISECONDS.sleep(client.getServiceTime());
			}
		} catch (InterruptedException e) {
			System.out.println(this + "interrupted");
		}
		System.out.println(this + "terminating");
	}

	public String toString() {
		return "Server " + id + " ";
	}

	public String shortString() {
		return "T" + id;
	}
}

class SimulationManager implements Runnable {
	private ExecutorService exec;
	private WebClientGenerator gen;
	private WebClientLine webClients;
	private Queue<Server> servers = new LinkedList<>();
	private int adjustmentPeriod;
	private boolean stable = true;
	private int prevSize;

	public SimulationManager(ExecutorService exec, WebClientGenerator gen, WebClientLine webClients, int adjustmentPeriod, int n) {
		this.exec = exec;
		this.gen = gen;
		this.webClients = webClients;
		this.adjustmentPeriod = adjustmentPeriod;
		for (int i = 0; i < n; i++) {
			Server server = new Server(webClients);
			exec.execute(server);
			servers.add(server);
		}
	}

	public void adjustLoadFactor() {
		if (webClients.size() > prevSize) {
			if (stable) {
				stable = false;
			} else {
				System.out.println("peak load factor:~" + gen.loadFator);
				exec.shutdownNow();
			}
		} else {
			System.out.println("new load factor: " + ++gen.loadFator);
			stable = true;
		}
		prevSize = webClients.size();
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
				System.out.print(webClients + "{ ");
				for (Server server : servers) {
					System.out.println(server.shortString() + " ");
				}
				System.out.print("}");
				adjustLoadFactor();
			}
		} catch (Exception e) {
			System.out.println(this + "interrupted");
		}
		System.out.println(this + "terminating");
	}

	public String toString() {
		return "SimulationManager ";
	}
}

public class E35_WebClientServerSimulation {
	static final int MAX_LINE_SIZE = 50;
	static final int NUM_OF_SERVERS = 3;
	static final int ADJUSTMENT_PERIOD = 1000;

	public static void main(String[] args) throws IOException {
		ExecutorService exec = Executors.newCachedThreadPool();
		WebClientLine clients = new WebClientLine(MAX_LINE_SIZE);
		WebClientGenerator gen = new WebClientGenerator(clients);
		exec.execute(gen);
		exec.execute(new SimulationManager(exec, gen, clients, ADJUSTMENT_PERIOD, NUM_OF_SERVERS));
		System.out.println("Press 'ENTER' to quit");
		System.in.read();
		exec.shutdownNow();
	}
}























