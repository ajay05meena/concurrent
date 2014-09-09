import akm.util.MyQueue;


public class DemoClass {
	
    public static void sleep(long n) {
    	try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

	public static void main(String[] args) {
		MyQueue<String> sharedQueue = new MyQueue<String>();
		ThreadSignal td = new ThreadSignal();
		Thread producerThread = new Thread(new Producer(sharedQueue,td),"PRODUCER");
		Thread consumerThread = new Thread(new Consumer(sharedQueue,td),"CONSUMER");
		Thread consumerThread1 = new Thread(new Consumer(sharedQueue,td),"CONSUMER");
		
		producerThread.start();
		consumerThread.start();
		consumerThread1.start();

	}

}
