import akm.util.MyQueue;


public class Consumer implements Runnable{
    MyQueue<String> queue ;
    ThreadSignal signal ;
    Consumer(MyQueue<String> queue,ThreadSignal signal){
    	this.queue = queue;
    	this.signal = signal;
    }
	
	@Override
	public void run() {
		//consumer1(queue);
		//consumer2(queue,signal);
		consumer3(queue,signal);
	}

	//no Exception but very low throughput as we locked the queue object in synchronize block
	private void consumer3(MyQueue<String> queue2,ThreadSignal signal) {
		while(true){
			synchronized(queue2){
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
	private void consumer2(MyQueue<String> queue2, ThreadSignal signal2) {
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
	private void consumer1(MyQueue<String> queue) {
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
