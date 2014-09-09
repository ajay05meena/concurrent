import akm.util.LockObject;
import akm.util.MyBlockingQueue;
import akm.util.MyQueue;


public class Consumer implements Runnable{
    MyQueue<String> queue ;
    ThreadSignal signal ;
    LockObject lock;
    MyBlockingQueue<String>queue1;
    Consumer(MyQueue<String> queue,ThreadSignal signal,LockObject lock){
    	this.queue = queue;
    	this.signal = signal;
    	this.lock = lock;
    }
    Consumer(MyQueue<String> queue,ThreadSignal signal){
    	this.queue = queue;
    	this.signal = signal;
    
    }
    Consumer(MyBlockingQueue<String> queue){
    	this.queue1 = queue;    
    }
	
	@Override
	public void run() {
		//consumer1();
		//consumer2();
		consumer5();
	}
	
	private void consumer5() {
		while(true){
			String poped = queue1.dequeue();
			System.out.println("Consumer " + poped + " from " + Thread.currentThread().getName());
		}
		
	}
	//better than consumer3 only because we are not locking queue so producer can continuously  put the ele in queue but 
	//consumer behave sequentially if we have multiple consumers
	private void consumer4(){
		while(true){
			synchronized (queue) {
				if(!signal.hasDataToProcess()){
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
						String poped = queue.dequeue();
						System.out.println("Consumer " + poped);
						if(queue.getTotal()==0){
							signal.setHasDataToProcess(false);
						}
					
				}
				}
			}
			
		}
			


	//no Exception but we are locking queue object so even producer also waits for queue object
	@SuppressWarnings("unused")
	private void consumer3() {
		while(true){
			synchronized(queue){
			if(!signal.hasDataToProcess){
				System.out.println("BusyWait Demo (Wasting cpu for no work)");
				DemoClass.sleep(1000l);
			}else{
				String poped = queue.dequeue();
				System.out.println("Consumer " + poped);
				if(queue.getTotal()==0){
					signal.setHasDataToProcess(false);
				}
			}
			}
		}
	}
		
	

	// from this consume method if we run multiple consumers than we can get exception(java.util.NoSuchElementException)
	@SuppressWarnings("unused")
	private void consumer2() {
		while(true){
			if(!signal.hasDataToProcess){
				System.out.println("BusyWait Demo (Wasting cpu for no work)");
				DemoClass.sleep(1000l);
			}else{
				String poped = queue.dequeue();
				System.out.println("Consumer " + poped);
				if(queue.getTotal()==0){
					signal.setHasDataToProcess(false);
				}
			}
		}
		
	}

	// This method demo's busy waiting 
	@SuppressWarnings("unused")
	private void consumer1() {
		while(true){
			
			if(queue.getTotal()==0){
				System.out.println("BusyWait Demo (Wasting cpu for no work)");
			}else{
				String poped = queue.dequeue();
				System.out.println("Consumer " + poped);
			}
		}
		
		
	}
	
	

}
