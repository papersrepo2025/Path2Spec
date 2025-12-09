
public class OddEven {

    //@ assignable \nothing;
    //@ requires x % 2 == 0;
    //@ ensures \result == true;
    //@ also
    //@ requires x % 2 != 0;
    //@ ensures \result == false;
    public /*@ pure @*/ boolean isEven(int x) {
        return x%2 == 0;
    }

    //@ assignable \nothing;
    //@ requires x % 2 != 0;
    //@ ensures \result == true;
    //@ also
    //@ requires x % 2 == 0;
    //@ ensures \result == false;
    public /*@ pure @*/ boolean isOdd(int x) {
        return x%2 != 0;
    }
}