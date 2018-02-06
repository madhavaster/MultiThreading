package com.madhav.demo1;

public class App {
	
	public static void main(String[] args) {
		Runner r1 = new Runner();
		r1.start();
	}
}
	
class Runner extends Thread{
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getName()+"..."+i);
		}
	}
}