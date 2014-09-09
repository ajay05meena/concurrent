
public class ThreadExample {

		public static void main(String[] args) {
		   int n = 5;
		   for(int i=0;i<n;i++){
			   final int j=i;
			   Thread t = new Thread(){
				   public void run(){
					   System.out.println("Thread " + j);
				   }
			   };
			   t.start(); // don't use t.run() as it will not start current thread 
			   //it invokes run of current thread
		   }
		}
}
// out put does not comes in order which we are creating the threads it totally 
//depend upon how os creates the threads