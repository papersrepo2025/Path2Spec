
public class MulLoop {

	/*@ public normal_behavior
	  @   ensures b >= 0 ==> \result == a * b;
	  @ also
	  @   ensures b < 0 ==> \result == a * b;
	  @*/
	public static int mulLoop(int a, int b) {
        int res = 0;
        if (b >= 0) {
            for(int i = 0; i < b; i++) {
                //@ assume i != 0;
                res = res + a;
            }
        } else {
            for(int i = 0; i < -b; i++) {
                //@ assume i != 0;
                res = res - a;
            }
        }
        //@ assume res == a * b;
        return res;
    }

}