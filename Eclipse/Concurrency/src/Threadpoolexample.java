import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//Eaxample for creating Threadpool..you can magage how many threads you want to simntanously run for program 
public class Threadpoolexample  implements Runnable {

	private String name ;
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");

	@Override
	public void run() {
		
		try {
			System.out.println(Thread.currentThread().getName() + "  Starting Time " + df.format(System.currentTimeMillis()));
			System.out.println(name);
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + "  Ending Time " + df.format(System.currentTimeMillis()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public Threadpoolexample(String n){
		this.name = n;
	}
	public String get_number(){
		return this.name;
	}
	public static void main(String [] args){
		ExecutorService executor =  Executors.newFixedThreadPool(5);
		for(int i =0 ; i<10;i++){
			Runnable worker = new Threadpoolexample("name" + i);
			executor.execute(worker);
		}
		executor.shutdown();
		while(!executor.isTerminated()){
			
		}
		
	}
}
