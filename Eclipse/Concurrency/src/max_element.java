//vary bad thread implimentation
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class max_element implements Runnable  {
	private int start; 
	private int A[];
	private int end;
	private int max;
	public max_element(int [] A,int i,int j){
		this.start = i;
		this.A = A;
		this.end = j;
	}
	public static void main(String[] args) {
		
		int limit  = 10;
		int n = 2;
		int max = 0;
		SimpleDateFormat df = new SimpleDateFormat();
		String starttime = df.format(System.currentTimeMillis());
		int A[] = new int[limit];
		limit = limit-1;
		Random randomgenrator = new Random();
		for(int i = 0; i<limit;i++){
			A[i]= randomgenrator.nextInt();
		}
		ExecutorService executor = Executors.newFixedThreadPool(n);
		for(int i = 0 ; i<n;i++){
			int j =0;
			Runnable worker = new max_element(A,j,j+(limit/n)-1);
			executor.execute(worker);
		}
		for(int i=limit/n;i<limit;i=i+limit/n){
			if(A[i]>A[i-limit/n]){
				max = A[i];
			}
			
			String  Endtime = df.format(System.currentTimeMillis());
			System.out.println("max element is " + max);
			System.out.println("Start time " + starttime + "EndTime " + Endtime);
		}

	}

	@Override
	public void run() {
		System.out.println();
		process(A,start,end);
		System.out.println();
		
	}

	private void process(int A[] , int i , int j) {
		for(int k=i+1;k<j;k++){
			if(A[k-1]>A[k]){
				int temp = A[k];
				A[k] =A[k-1];
				A[k-1]= temp;
			}
		}
		
	}

}
