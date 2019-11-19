package com.tntcpu.javabase.tij.concurrency.exercise;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-11-19
 **/
class WebClient {
	private final int serviceTime;
	public WebClient(int tm){
		serviceTime = tm;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	public String toString(){
		return "[" + serviceTime + "]";
	}
}

public class E35_WebClientServerSimulation {

}
