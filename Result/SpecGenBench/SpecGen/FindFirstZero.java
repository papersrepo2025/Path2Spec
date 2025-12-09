public class FindFirstZero {

    	 //@ requires x != null;
    	 //@ ensures (\result == -1) <==> (\forall int i; 0 <= i && i < x.length; x[i] != 0);
    	 //@ ensures \result == -1 || (0 <= \result && \result < x.length);
    	 //@ ensures 0 <= \result && \result < x.length ==> (x[\result] == 0 && (\forall int j; 0 <= j && j < \result; x[j] != 0));
    	 public static int FindFirstZero(int[] x) {
         	assert x.length >= 0;
         	if (x.length == 0) {
            		return -1;
        	} else {
            		int index = 0;

            		//@ maintaining 0 <= index && index <= x.length;
            		//@ maintaining (\forall int j; 0 <= j && j < index; x[j] != 0);
            		//@ decreases x.length - index;
            		while (x.length - index > 0 && x[index] != 0) {
                		index = index + 1;
            		}
            		if (x.length - index == 0) {
               			index = -1;
            		}
            	return index;
        	}
    	}
}