import akm.util.MyBlockingQueue;
import akm.util.MyQueue;


public class Producer implements Runnable{
	MyQueue<String> queue ;
	ThreadSignal signal ;
	MyBlockingQueue<String> queue1;
	public Producer(MyQueue<String> queue,ThreadSignal signal) {
		this.queue = queue;
		this.signal = signal;
	}
	
	public Producer(MyBlockingQueue<String> queue1){
		this.queue1 = queue1;
	}
	
	@Override
	public void run() {
		produce3();
	}
	
	private void produce3() {
		for(int i=0;i<Integer.MAX_VALUE;i++){
			queue1.enqueue("Element:"+i);
			
		}
		
	}

	void produce1(){
		for(int i=0;i<Integer.MAX_VALUE;i++){
		    DemoClass.sleep(500l);
		    if(!signal.hasDataToProcess){
				signal.setHasDataToProcess(true);
			}
			queue.enqueue("Element:"+i);
			
		}
	}
	
	void produce2(){
		for(int i=0;i<1000;i++){
		    DemoClass.sleep(500l);
		    if(!signal.hasDataToProcess){
				signal.setHasDataToProcess(true);
			}
			
			synchronized(queue){
				queue.enqueue("Element:"+i);
				queue.notifyAll();
			}
		}
	}

}
