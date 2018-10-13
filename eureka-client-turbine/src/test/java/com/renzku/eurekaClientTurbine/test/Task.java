package com.renzku.eurekaClientTurbine.test;

public class Task {
	public synchronized static void doLongTimeTaskA() {
		System.out.println("name = " + Thread.currentThread().getName() + ", begain");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("name = " + Thread.currentThread().getName() + ", end");
	}

	public synchronized static void doLongTimeTaskB() {
		System.out.println("name = " + Thread.currentThread().getName() + ", begain");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("name = " + Thread.currentThread().getName() + ", end");
	}

	public synchronized void doLongTimeTaskC() {

		System.out.println("name = " + Thread.currentThread().getName() + ", begain");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("name = " + Thread.currentThread().getName() + ", end");

	}

	public void doLongTimeTaskD() {
		synchronized(this){
			System.out.println("name = " + Thread.currentThread().getName() + ", begain");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("name = " + Thread.currentThread().getName() + ", end");

		}
	}
}
