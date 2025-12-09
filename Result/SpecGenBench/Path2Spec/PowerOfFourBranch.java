
public class PowerOfFourBranch {
    //@ requires n > 0;
    //@ requires (n & (n - 1)) != 0;
    //@ ensures \result == false;
    //@ also
    //@ requires n <= 0;
    //@ ensures \result == false;
    public boolean isPowerOfFour(int n) {
        if(n <= 0) {
            return false;
        }
        if((n & (n - 1)) != 0) {
            return false;
        }
        if((n & 0xaaaaaaaa) != 0) {
            return false;
        }
        return true;
    }
}
