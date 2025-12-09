
class CanWinNim {
    //@ requires n % 4 == 0;
    //@ ensures \result == false;
    //@ also
    //@ requires n >= 0;
    //@ ensures \result == (n % 4 != 0);
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
