public class AbsSeq {
	
	//@ ensures \result == (num < 0 ? -num : num);
	//@ ensures num != Integer.MIN_VALUE ==> \result >= 0;
	//@ assignable \nothing;
	public int Abs(int num) {
		return ((num < 0) ? (-num) : (num));
	}

}