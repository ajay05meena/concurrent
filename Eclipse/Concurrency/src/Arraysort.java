import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
1.output with input 10000,100,1
total time taken with single 112572966
total time taken with 1 threads  39406405
2.output with input 10000,1000,1
total time taken with single 74717876
total time taken with 1 threads  76842727
3.output with input 10000,10,1
total time taken with single 75010092
total time taken with 1 threads  82737890
4.output with input 10000,10,2
total time taken with single 66400059
total time taken with 2 threads  32218620
5.output with input 10000,10,3
total time taken with single 91812228
total time taken with 3 threads  43410824
6.output with input 10000,10,4
total time taken with single 78038689
total time taken with 4 threads  27143394
*/
public class Arraysort {
	public static int max_element(int[] A , int L){
		int i =0;
		int max =0;
		while(i<A.length){
			if(max<A[i]){
				max =A[i];
			}
			i = i+L;
		}
		return max;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random randomgenrator = new Random();
		int limit =100;
		int L = 100;
		int threads =1;
		for(int i=0;i<args.length;i++){
			if(args[i].equals("-length")){
				limit = Integer.parseInt(args[i+1]);
			}
			if(args[i].equals("interval")){
				L = Integer.parseInt(args[i+1]);
			}
			if(args[i].equals("-numthreads")){
				threads = Integer.parseInt(args[i+1]);	
			}
		}
		
		int A[] = new int[limit];
		for(int i = 0; i<limit;i++){
			A[i]= randomgenrator.nextInt(limit);
		}	
		singlethread st = new singlethread(A);
		int max_single = st.max_element(A);
		long starttime = System.nanoTime();
		for(int i = 0; i<limit;i++){
			A[i]= randomgenrator.nextInt(limit);
		}	
		
        ExecutorService es = Executors.newFixedThreadPool(1);
        for(int i=0;i<limit;i=i+L){
        	
        Runnable worker = new threadsort(A,i,i+L<limit?i+L:limit);
        es.execute(worker);
		
        }
        es.shutdown();
        while(!es.isTerminated()){
        	
        }
        int max_multi = max_element(A,L);
        long endtime = System.nanoTime();
		long time_taken = endtime - starttime ;
		System.out.println("total time taken with " + threads + " threads  " + time_taken);
		//if(max_single!=max_multi){
			//System.out.println("Error " + max_single + "  " + max_multi );
		//}
        
	}

}
class threadsort implements Runnable{

	private int[] array;
	private int start;
	private int end;
	public threadsort(int [] A , int i,int j){
		this.array = A;
		this.start =i;
		this.end = j;
	}

	@Override
	public void run() {
		process(array,start,end);
		
	}

	private void process(int[] array2, int start2, int end2) {
		Arrays.sort(array2, start2, end2);
	}
	
	
	
}
class singlethread{
	int [] array;
	public singlethread(int [] A){
		this.array = A;
	}
	
	public int max_element(int [] A)
	{
		long starttime = System.nanoTime();
		Arrays.sort(A);
		long endtime = System.nanoTime();
		long time_taken = endtime - starttime ;
		System.out.println("total time taken with single " + time_taken);
		return A[A.length-1];
	}
}