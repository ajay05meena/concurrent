package akm.util;




public class MyQueue<T> implements Queue<T> {
	private int total; //total number of elements in queue
	private Node<T> first,last;
	
	@Override
	public T dequeue() {
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

	@Override
	public Queue<T> enqueue(T ele) {
		Node<T> current = last;
		last = new Node<T>();
		last.ele=ele;
		if(total++==0){
			first = last;
		}else{
			current.next = last;
		}
		
		return this;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Node<T> tmp = first;
		while(tmp!= null)
		{
			tmp = tmp.next;
			sb.append(tmp.ele).append(", ");
		}
		return sb.toString();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Node<T> getFirst() {
		return first;
	}

	public void setFirst(Node<T> first) {
		this.first = first;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

}
