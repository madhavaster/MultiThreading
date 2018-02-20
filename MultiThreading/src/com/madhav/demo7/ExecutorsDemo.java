package com.madhav.demo7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo {
	public static void main(String[] args) {
		Runnable task1 = new Runnable() {
			public void run() {
				for(int i=0;i<=10;i++) {
					System.out.println(Thread.currentThread().getName()+"....."+i);
				}
			}
		};
		Runnable task2 = ()->{
			for(int i=20;i<=30;i++) {
				System.out.println(Thread.currentThread().getName()+"....."+i);
			}
		};
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		newSingleThreadExecutor.submit(task1);
		newSingleThreadExecutor.submit(task2);
		newSingleThreadExecutor.shutdown();
	}
}