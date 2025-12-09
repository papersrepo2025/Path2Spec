public class Absolute {
	
	//@ ensures (0 <= num) ==> \result == num;
	//@ ensures (num < 0) ==> \result == -num;
	public  int Absolute(int num) {
		if (0 <= num)
			return num;
		else
			return -num;
	}

	
	//@ ensures (0 <= num) ==> \result == num;
	//@ ensures (num < 0) ==> \result == -num;
	public  long Absolute(long num) {
		if (0 <= num)
			return num;
		else
			return -num;	
	}
}