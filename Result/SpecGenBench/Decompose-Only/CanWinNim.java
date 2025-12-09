
class CanWinNim {
    //@ requires n % 4 == 0;
    //@ ensures \result == false;
    //@ also
    //@ requires n % 4 != 0;
    //@ ensures \result == true;
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}