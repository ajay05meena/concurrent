import java.text.SimpleDateFormat;

//Thread control from another thread using volatile variable
//when boolean var is normal output is
/*
main  Starting Time 25/11/13 10:11 PM
main
main  Ending Time 25/11/13 10:11 PM
main  Starting Time 25/11/13 10:11 PM
main
main  Ending Time 25/11/13 10:11 PM

 */
//when boolean var is volatile output is
/*
 main  Starting Time 25/11/13 10:13 PM
main
main  Ending Time 25/11/13 10:13 PM
main  Starting Time 25/11/13 10:13 PM
main
main  Ending Time 25/11/13 10:13 PM

 */
//When boolean var is static output is
/*
 main  Starting Time 25/11/13 10:16 PM
main
main  Ending Time 25/11/13 10:16 PM
main  Starting Time 25/11/13 10:16 PM
main  Ending Time 25/11/13 10:16 PM

 */
public class Threadcontrol implements Runnable{
	int n ;
	static boolean flag  ;
	
	SimpleDateFormat df = new SimpleDateFormat();
	public Threadcontrol(int n , boolean flag){
		this.n = n;
		this.flag = flag;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Threadcontrol(1,true));
		Thread t2 = new Thread(new Threadcontrol(5,true));
		t1.run();
		t2.run();

	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "  Starting Time " + df.format(System.currentTimeMillis()));
		while(flag){
				n++;
				if(n>10){flag = false;
				System.out.println(Thread.currentThread().getName());
				}
				
			
		}
		System.out.println(Thread.currentThread().getName() + "  Ending Time " + df.format(System.currentTimeMillis()));
	}

}
