import java.util.*;
public class LocalThr extends Thread {
	int x = 0;
	
	public void run() {
		if(x > 10){
			x = 0;
		}
		//if call == nextEven
			//setNextEven()
		//if call == nextOdd
			//setNextOdd()
		System.out.println("Hello");
	}
	
	public void setNextEven(){
		while(x % 2 != 0){
			x++;
		}
		//put x in queue if even
	}
	
	public void setNextOdd(){
		while(x % 2 == 0){
			x++
		}
		//put x in queue if odd
	}
}