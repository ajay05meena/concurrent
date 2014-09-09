package akm.util;

public class MyQueue1<T> extends MyQueue<T>{
	@Override
	public T dequeue() {
		synchronized (this) {
			if(total == 0){
				throw new java.util.NoSuchElementException();
			}
			T ele = first.ele;
			first = first.next;
			if(--total==0){
				last = null;
			}
			return ele;
		}
		}
		


}
