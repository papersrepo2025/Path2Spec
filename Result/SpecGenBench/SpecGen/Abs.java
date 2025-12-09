public class Abs {
	
	//@ ensures num >= 0 ==> \result == num;
	//@ ensures num < 0 ==> \result == -num;
	public /*@ pure @*/ int Abs(int num) {
		if (num < 0)
			return -num;
		else
			return num;
	}

}