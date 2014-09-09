package akm.util;

public class MyBlockingQueue<T> extends MyQueue<T>{
	int limit = 100;
	@Override
	public synchronized T dequeue() {
				try {
					if(total == 0){
					System.out.println("waiting for producer to add element" );
					wait();
					}
					if(total==limit){
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
			while(total==limit){
				System.out.println("waiting for consumer to eat element" );
				wait();
			}
			if(total==0){
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
		

}
