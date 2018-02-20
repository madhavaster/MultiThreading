package com.madhav.demo5;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/******************************************************************
 * 02.Solving Producer and Consumer Problem using BlockingQueue   *
 ******************************************************************/
public class ProducerConsumerDemo {
	public static void main(String[] args) {
		BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(500);
		new Thread(new ProducerThread(sharedQueue)).start();
		new Thread(new ConsumerThread(sharedQueue)).start();
	}
}
class ProducerThread implements Runnable{
	private BlockingQueue<Integer> sharedQueue;
	public ProducerThread(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue=sharedQueue;
	}
	public void run() {
		while(true) {
		Random r = new Random();
		int element = r.nextInt();
		try {
			sharedQueue.put(element);
			System.out.println("Produced::"+element+"::::"+sharedQueue.size());
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
}
class ConsumerThread implements Runnable{
	private BlockingQueue<Integer> sharedQueue;
	public ConsumerThread(BlockingQueue<Integer> sharedQueue) {
		this.sharedQueue=sharedQueue;
	}
	public void run() {
		while(true) {
		try {
			Integer take = sharedQueue.take();
			System.out.println("Consumed::"+take);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
}