package org.self.learn;

public class RunnableLambda {

	public static void main(String[] args) throws InterruptedException {
		
		/*Runnable runnable = new Runnable() {
			
			@Override
			public void run() {

				for(int i = 0; i < 3; i++) {
					System.out.println("Hello world from thread[" + Thread.currentThread().getName() + "]");
				}
				
			}
		};*/
		
		Runnable runnable = () -> {
			for(int i = 0; i < 3; i++) {
				System.out.println("Hello world from thread[" + Thread.currentThread().getName() + "]");
			}
		};
		
		Thread t = new Thread(runnable);
		t.start();
		t.join();

	}

}
