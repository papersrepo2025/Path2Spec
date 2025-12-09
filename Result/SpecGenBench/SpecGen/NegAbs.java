public class NegAbs {
	
	/*@ 
	  @ assignable \nothing;
	  @ ensures (num < 0 ==> \result == num) && (num >= 0 ==> \result == -num);
	  @ ensures \result <= 0;
	  @*/
	public int negAbs(int num) {
		if (num < 0)
			return num;
		else
			return -num;
	}

}