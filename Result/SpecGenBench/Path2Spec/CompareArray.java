
class CompareArray {
    //@ requires a != null && b != null;
    //@ ensures a.length != b.length ==> \result == false;
    //@ also
    //@ requires a != null && b != null;
    //@ ensures a.length == 0 && b.length == 0 ==> \result == true;
    //@ also
    //@ requires a != null && b != null;
    //@ requires a.length == b.length && (\forall int i; 0 <= i < a.length; a[i] == b[i]);
    //@ ensures \result == true;
    public static boolean arrcmp(int[] a, int[] b) {
        if(a.length != b.length) {
            return false;
        }

        //@ loop_invariant 0 <= i && i <= a.length;
        //@ decreases a.length - i;
        for(int i = 0; i < a.length; i++) {
            //@ requires 0 <= i && i < a.length && 0 <= i && i < b.length;
            if(a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }
}
