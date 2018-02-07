package com.madhav.demo2;

public class ThreadA {
	
	public static void main(String[] args) throws InterruptedException {
	System.out.println(Thread.currentThread().getName()+"...started");
	ThreadB t = new ThreadB();
	t.start();
	
	t.wait();
	System.out.println(t.getTotal());
	System.out.println(Thread.currentThread().getName()+"...ended");
	}
}
	
class ThreadB extends Thread{
	private int total;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"...started");
		for(int i=1;i<=100;i++) {
			total = total+i;
		}
		this.notify();
		System.out.println(Thread.currentThread().getName()+"...ended");
	}
}

/*o/p
main...started
Thread-0...started
Exception in thread "main" Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
	at java.lang.Object.notify(Native Method)
	at com.madhav.demo2.ThreadB.run(ThreadA.java:30)
java.lang.IllegalMonitorStateException
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at com.madhav.demo2.ThreadA.main(ThreadA.java:10)*/
