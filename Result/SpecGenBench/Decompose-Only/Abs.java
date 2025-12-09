
public class Abs {
	
	//@ requires num < 0;
	//@ ensures \result == -num;
	//@ also
	//@ requires num >= 0;
	//@ ensures \result == num;
	public int Abs(int num) {
		if (num < 0)
			return -num;
		else
			return num;
	}

}