package com.tntcpu.javabase.tij.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @program: firefly
 * @description:
 * @author: ZhangZhentao
 * @create: 2019-10-21
 **/
public class Pool<T> {
	private int size;
	private List<T> items = new ArrayList<>();
	private volatile boolean[] checkedOut;
	private Semaphore available;

	public Pool(Class<T> tClass,int size) {
		this.size = size;
		checkedOut = new boolean[size];
		available = new Semaphore(size,true);
		for (int i = 0; i < size; i++) {
			try {
				items.add(tClass.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	public T checkOut(){
		available.acquire();;
		return getItem();
	}
	public void checkIn(T x){
		if (releaseItem(x)){available.release();}
	}

	private synchronized boolean releaseItem(T x) {
		int index = items.indexOf(x);
		if (index==-1) return false;
		if (checkedOut[index]){
			checkedOut[index] =false;
			return true;
		}
		return false;
	}

	private synchronized T getItem() {
		for (int i = 0; i < size; ++i) {
			if (!checkedOut[i]){
				checkedOut[i] = true;
				return items.get(i);
			}
		}
		return null;
	}
}
