public class DivisorGame {
    //@ requires n % 2 == 0;
    //@ assignable \nothing;
    //@ ensures \result == true;
    //@ also
    //@ requires n % 2 != 0;
    //@ assignable \nothing;
    //@ ensures \result == false;
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}