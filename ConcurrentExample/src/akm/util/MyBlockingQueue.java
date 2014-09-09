package akm.util;

public class MyBlockingQueue<T> extends MyQueue<T>{
	int limit = 100;
	@Override
	public synchronized T dequeue() {
				try {
					if(isEmpty()){
					System.out.println("waiting for producer to add element" );
					wait();
					}
					if(isFull()){
						notifyAll();
					}
					T ele = first.ele;
					first = first.next;
					if(--total==0){
						last = null;
					}
					return ele;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			return null;
		}
		
	@Override
	public synchronized Queue<T> enqueue(T ele) {
		if(ele==null){
			return this;
		}
		try{
			while(isFull()){
				System.out.println("waiting for consumer to eat element" );
				wait();
			}
			if(isEmpty()){
				notifyAll();
			}
			Node<T> current = last;
			last = new Node<T>();
			last.ele=ele;
			if(total++==0){
				first = last;
			}else{
				current.next = last;
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return this;
		}
	private boolean isEmpty(){
		return total==0;
	}
	
	private boolean idFull(){
		return total==limit;
	}

}
