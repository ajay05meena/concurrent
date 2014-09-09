
import akm.util.LockObject;
import akm.util.MyBlockingQueue;
import akm.util.MyQueue1;


public class DemoClass {
	
	
    public static void sleep(long n) {
    	try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

	public static void main(String[] args) {
		boolean blockingqueueDemo = true;
		if(blockingqueueDemo){
			MyBlockingQueue<String> sharedQueue = new MyBlockingQueue<String>();
			
			Thread producerThread = new Thread(new Producer(sharedQueue),"PRODUCER");
			Thread consumerThread = new Thread(new Consumer(sharedQueue),"CONSUMER");
			Thread consumerThread1 = new Thread(new Consumer(sharedQueue),"CONSUMER1");
			
			producerThread.start();
			consumerThread.start();
     		consumerThread1.start();
			
		}else{
		
		MyQueue1<String> sharedQueue = new MyQueue1<String>();
		ThreadSignal td = new ThreadSignal();
		LockObject lock = new LockObject();
		Thread producerThread = new Thread(new Producer(sharedQueue,td),"PRODUCER");
		Thread consumerThread = new Thread(new Consumer(sharedQueue,td,lock),"CONSUMER");
		Thread consumerThread1 = new Thread(new Consumer(sharedQueue,td,lock),"CONSUMER1");
		
		producerThread.start();
		consumerThread.start();
		consumerThread1.start();
		}

	}

}
