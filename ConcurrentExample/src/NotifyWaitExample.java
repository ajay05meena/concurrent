//doubts 1. why wait and notify inside synchronized block we need to obtain lock 
//on object 
//doubt 2 . why no deadlock here as main thread become inactive inside synchornoized 
//block as when we apply a.wait we release the lock and it went another thread after
//that thread notify again main hold the lock on a 




public class NotifyWaitExample{
	public static void main(String[] args) {
		ThreadA a = new ThreadA();
		a.start();
			synchronized (a) {
				try {
					System.out.println("Waiting for thread A to complete");
		
					a.wait();
				//current thread execution stops until another thread call notify
				// on same object	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Total: " + a.total);		

	}

}

class ThreadA extends Thread{
	int total = 0;
	public void run(){
		synchronized (this) {
		 for(int i=0;i<1000;i++){
			 total+=i;
		 }
		 notify();
		}
		
		System.out.println("ThreadA ended");
	}
}