
class IsOneBitCharacter {
    //@ requires bits == null;
    //@ signals (NullPointerException) true;
    //@ also
    //@ requires bits != null && (\forall int k; 0 <= k && k < bits.length; bits[k] == 0 || bits[k] == 1);
    //@ ensures bits.length == 0 ==> \result == false;
    //@ ensures \result ==> bits.length >= 1;
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, i = 0;
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining (\forall int k; 0 <= k && k < n; bits[k] == 0 || bits[k] == 1);
        //@ decreases n - i;
        while (i < n - 1) {
            //@ assume i != 0;
            i += bits[i] + 1;
        }
        return i == n - 1;
    }
}