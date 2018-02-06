package com.madhav.demo1;
public class TestThreadTwice1 extends Thread{  
 public void run(){
	 for(int i=0;i<10;i++) {
		// System.out.println(i);
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
   System.out.println("child thread ends...");  
 }  
 public static void main(String args[]){  
	 try {
  TestThreadTwice1 t1=new TestThreadTwice1();  
  t1.start();  
  t1.start();
	 }catch(Exception e) {
		 System.out.println("in exception block"+e.getClass().getName());
		 e.printStackTrace();
	 }
  System.out.println("main ends");
 }  
}  