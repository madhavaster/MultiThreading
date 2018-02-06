package com.madhav.demo1;
class TestCallRun1 extends Thread{  
 public void run(){  
	 for(int i=0;i<100;i++) {
		 System.out.println("run method"+Thread.currentThread().getName());
	 }
 }  
 public static void main(String args[]){  
  TestCallRun1 t1=new TestCallRun1();  
  t1.run();//fine, but does not start a separate call stack  
  for(int i=0;i<100;i++) {
	  System.out.println("main method:"+Thread.currentThread().getName());
  }
 }  
}  