public class XOR {
    //@ assignable \nothing;
    //@ ensures \result <==> ((b1 && !b2) || (!b1 && b2));
    public static /*@ pure @*/ boolean xor(boolean b1, boolean b2) {
        if(b1 == true) {
            if(b2 == true) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            if(b2 == true) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}