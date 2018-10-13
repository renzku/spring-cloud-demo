package com.renzku.eurekaClientTurbine.test;

public class MyThread{
	private Task task;

	public static void main(String args[]){
		Task task = new Task();
		Thread t1=new ThreadA(task);
		Thread t2=new ThreadB(task);
		Thread t3=new ThreadC(task);
		Thread t4=new ThreadA(task);
		Thread t5=new ThreadB(task);
		Thread t6=new ThreadC(task);
		Thread t7=new ThreadD(task);
		Thread t8=new ThreadD(task);
		t1.setName("A1");
		t2.setName("B1");
		t3.setName("C1");
		t4.setName("A2");
		t5.setName("B2");
		t6.setName("C2");
		t7.setName("D1");
		t8.setName("D2");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
	}
}

class ThreadA extends Thread{

	private Task task;

	public ThreadA(Task tk){
		task = tk;
	}

	public void run() {
		task.doLongTimeTaskA();
	}
}

class ThreadB extends Thread{

	private Task task;

	public ThreadB(Task tk){
		task = tk;
	}

	public void run() {
		task.doLongTimeTaskB();
	}
}

class ThreadC extends Thread{

	private Task task;

	public ThreadC(Task tk){
		task = tk;
	}

	public void run() {
		task.doLongTimeTaskC();
	}
}
class ThreadD extends Thread{

	private Task task;

	public ThreadD(Task tk){
		task = tk;
	}

	public void run() {
		task.doLongTimeTaskD();
	}
}