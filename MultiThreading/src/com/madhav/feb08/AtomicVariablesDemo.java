package com.madhav.feb08;

public class AtomicVariablesDemo {

	public static void main(String[] args) {
		Counter c = new Counter();
		int increment = c.increment();
		System.out.println(increment);
	}
}

class Counter {
	private int counter;
	public int getValue() {
		return counter;
	}
	public int increment() {
		System.out.println(counter);
		counter=counter+1;
		return counter ;
	}
}