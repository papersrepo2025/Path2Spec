
public class DivisorGame {
    //@ requires n >= 0 && n % 2 == 0;
    //@ ensures \result == true;
    //@ also
    //@ requires n >= 0 && n % 2 != 0;
    //@ ensures \result == false;
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
