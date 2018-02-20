package com.madhav.demo4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumerDemo {

	public static void main(String[] args) {
		System.out.println("inside main");
		Queue<Integer> sharedObject = new LinkedList();
		ProducerThread producer = new ProducerThread(sharedObject);
		ConsumerThread consumer = new ConsumerThread(sharedObject);
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();t2.start();
	}
}
class ProducerThread implements Runnable{
	Queue<Integer> sharedObject = null;
	public ProducerThread(Queue sharedObject) {
		this.sharedObject=sharedObject;
	}
	public void run() {
		System.out.println("ProduceThread run method called");
		while(true) {
			synchronized(sharedObject) {
				while(sharedObject.size()==5) {
					try {
						System.out.println("Producer is waiting to consume object by consumer");
						sharedObject.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Random r = new Random();
				int nextInt = r.nextInt(5);
				sharedObject.add(nextInt);
				System.out.println("Produced::"+nextInt);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sharedObject.notify();
			}
		}
	}
}
class ConsumerThread implements Runnable{
	Queue<Integer> sharedObject = null;
	public ConsumerThread(Queue sharedObject) {
		this.sharedObject=sharedObject;
	}
	public void run() {
		System.out.println("ConsumerThread run method called");
		while(true) {
			synchronized (sharedObject) {
				while(sharedObject.isEmpty()) {
					try {
						System.out.println("consumer is waiting for producer thread to produce");
						sharedObject.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Integer poll = sharedObject.poll();
				System.out.println("consumer::"+poll);
				sharedObject.notify();
			}
		}
	}
}