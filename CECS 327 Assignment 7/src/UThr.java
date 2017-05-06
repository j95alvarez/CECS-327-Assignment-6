import java.util.*;

public class UThr extends Thread {
	int id;
	String request;
	public UThr(int id, String request){
		this.id = id;
		this.request = request;
	}
	public void run() {
		Random ran = new Random();						//Creates random object
		for(int i = 0; i < 20; i++){
			int x = ran.nextInt(5);						//Chooses random number from 0 - 4
			if (x == 0){
				System.out.println("nextEven");			//If x = 0, go to nextEven thread
			}
			if (x == 1){
				System.out.println("nextOdd");			//If x = 1, go to nextOdd thread
			}
			if (x == 2){
				System.out.println("nextEvenFib");		//If x = 2, go to nextEvenFib thread
			}	
			if (x == 3){
				System.out.println("nextLargerRand");	//If x = 3, go to nextLargerRand thread
			}
			if (x == 4){
				System.out.println("nextPrime");		//If x = 4, go to nextPrime thread
			}
			
			//while returnque.peak() != string
				//Do nothing
			//string som = returnQue.pull()
			//Print to terminal
		}

		
		System.out.println("Hello");
	}
}