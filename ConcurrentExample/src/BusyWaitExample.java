
public class BusyWaitExample {
	
	public static void main(String[] args) {
		final ThreadSignal mySignal = new ThreadSignal();
		Thread a = new Thread(){
			public void run(){
				System.out.println("Thread A");
			     try {
					Thread.sleep(1000*10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mySignal.setHasDataToProcess(true);
			}
		};
		Thread b = new Thread(){
			public void run(){
				while(!mySignal.hasDataToProcess()){
					System.out.println("Thread B Busy Waiting");
				}
			}
		};
		
		a.start();
		b.start();

	}

}
// this class is used for signaling between multiple threads to prevent
// errors because of share resources between threads



