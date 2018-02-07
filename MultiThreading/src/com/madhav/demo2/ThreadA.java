package com.madhav.demo2;
// 2 threads waiting for notification on an object scenario.
public class ThreadA {
	
	public static void main(String[] args) throws InterruptedException {
		ThreadB b = new ThreadB();
		Thread1 t1 = new Thread1(b);
		Thread2 t2 = new Thread2(b);
		b.start();
		t1.start();
		t2.start();
	}
}
class Thread1 extends Thread{
	private ThreadB b;
	public Thread1(ThreadB b) {
		this.b=b;
	}
	@Override
	public void run() {
		System.out.println("Thread1 started");
		synchronized(b) {
		System.out.println("Thread1 got the lock of object b");
			try {
				System.out.println("Thread1 calling wait method on b");
				b.wait();
				System.out.println("Thread1 is notified now");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread1 printing total value"+ b.getTotal());
		System.out.println("Thread1 is releasing the lock of object b");
		}
		System.out.println("Thread1 ended");
	}
}
class Thread2 extends Thread{
	private ThreadB b;

	public Thread2(ThreadB b) {
		this.b=b;
	}
	@Override
	public void run() {
		System.out.println("Thread2 started");
		synchronized(b) {
			System.out.println("Thread2 got the lock of object b");
			try {
				System.out.println("Thread2 caling wait method on b");
				b.wait();
				System.out.println("Thread2 is notified now");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread2 printing total value"+b.getTotal());
			System.out.println("Thread2 is releasing the lock of object b");
		}
		System.out.println("Thread2 ended");
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
		System.out.println("ThreadB started");
		try {
			System.out.println("ThreadB going to sleep for 10000ms");
			Thread.sleep(10000);
			System.out.println("ThreadB is awake now");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(this) {
			System.out.println("ThreadB got the lock of this and started calculating");
			for(int i=1;i<=100;i++) {
				total = total+i;
			} 
			System.out.println("ThreadB is done with calculation"+total+"..and giving notification now");
			this.notify();
		}
		System.out.println("ThreadB ended");
	}
}

/*output of this program
========================================================================
ThreadB started
ThreadB going to sleep for 10000ms
Thread1 started
Thread2 started
Thread1 got the lock of object b
Thread1 calling wait method on b
Thread2 got the lock of object b
Thread2 caling wait method on b
ThreadB is awake now
ThreadB got the lock of this and started calculating
ThreadB is done with calculation5050..and giving notification now
Thread1 is notified now
Thread1 printing total value5050
Thread1 is releasing the lock of object b
Thread1 ended
ThreadB ended
Thread2 is notified now
Thread2 printing total value5050
Thread2 is releasing the lock of object b
Thread2 ended
========================================================================
*/
