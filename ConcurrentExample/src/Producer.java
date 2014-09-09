import akm.util.MyQueue;


public class Producer implements Runnable{
	MyQueue<String> queue ;
	ThreadSignal signal ;
	public Producer(MyQueue<String> queue,ThreadSignal signal) {
		this.queue = queue;
		this.signal = signal;
	}
	
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
		    DemoClass.sleep(500l);
			queue.enqueue("Element:"+i);
			if(!signal.hasDataToProcess){
				signal.setHasDataToProcess(true);
			}
		}
	}

}
