
class PowerOfFourLoop {
    //@ requires n == 1;
//@ ensures \result == true;
    public boolean isPowerOfFour(int n) {
        if(n <= 0) {
            return false;
        }
        //@ maintaining n == 1;
        //@ decreases 0;
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
}
