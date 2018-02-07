package com.madhav.demo2;
// Usage of wait and notify methods
public class ThreadA {
	
	public static void main(String[] args) throws InterruptedException {
	System.out.println(Thread.currentThread().getName()+"...started");
	ThreadB t = new ThreadB();
	t.start();
	synchronized(t) {
		System.out.println("main thread trying to call wait method");
		t.wait();// immediately main thread enters into waiting state.
		System.out.println("main thread got notification");
	}
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
		synchronized(this) {
			System.out.println("child thread to trying to give notification");
			this.notify();
		}
		System.out.println(Thread.currentThread().getName()+"...ended");
	}
}

/*o/p
main...started
main thread trying to call wait method
Thread-0...started
child thread to trying to give notification
Thread-0...ended
main thread got notification
5050
main...ended*/
