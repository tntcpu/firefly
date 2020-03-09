package com.tntcpu.javabase.tij.concurrency.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2020-03-09
 **/
class WebClient {
    private final int serviceTime;

    public WebClient(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}

class WebClientLine extends ArrayBlockingQueue<WebClient> {
    public WebClientLine(int capacity) {
        super(capacity);
    }

    @Override
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
    volatile int loadFactor = 1;
    private static Random rand = new Random(47);

    public WebClientGenerator(WebClientLine clients) {
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                clients.put(new WebClient(rand.nextInt(1000)));
                TimeUnit.MILLISECONDS.sleep(1000 / loadFactor);
            }
        } catch (InterruptedException e) {
            System.out.print("WebClientGenerator terminated");
        }
        System.out.print("WebClientGenerator terminating");
    }
}

class Server implements Runnable {
    private static int counter;
    private final int id = counter++;
    private WebClientLine clients;

    public Server(WebClientLine clients) {
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                WebClient client = clients.take();
                TimeUnit.MILLISECONDS.sleep(client.getServiceTime());
            }
        } catch (InterruptedException e) {
            System.out.print(this + "interrupted");
        }
        System.out.print(this + "terminating");
    }

    @Override
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
    private WebClientLine clients;
    private Queue<Server> servers = new LinkedList<>();
    private int adjustmentPeriod;

    private boolean stable = true;
    private int prevSize;

    public SimulationManager(ExecutorService exec, WebClientGenerator gen, WebClientLine clients,
                             int adjustmentPeriod, int n) {
        this.exec = exec;
        this.gen = gen;
        this.clients = clients;
        this.adjustmentPeriod = adjustmentPeriod;
        for (int i = 0; i < n; i++) {
            Server server = new Server(clients);
            exec.execute(server);
            servers.add(server);
        }
    }

    public void adjustLoadFactor() {
        if (clients.size() > prevSize) {
            if (stable) {
                stable = false;
            } else if (!stable) {
                System.out.print("Peak load factor: ~" + gen.loadFactor);
                exec.shutdownNow();
            }
        } else {
            System.out.print("New load factor: " + ++gen.loadFactor);
            stable = true;
        }
        prevSize = clients.size();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                System.out.print(clients + " { ");
                for (Server server : servers) {
                    System.out.println(server.shortString() + " ");
                }
                System.out.print(" }");
                adjustLoadFactor();
            }
        } catch (InterruptedException e) {
            System.out.print(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public String toString() {
        return "SimulationManager ";
    }
}

public class E35_WebClientServerSimulation {

}




































































































