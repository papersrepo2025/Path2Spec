
class FizzBuzz {
    //@ requires n > 0 && n % 3 != 0 && n % 5 != 0;
    //@ ensures \result == 0;
    //@ also
    //@ requires n > 0 && n % 3 == 0 && n % 5 != 0;
    //@ ensures \result == 3;
    //@ also
    //@ requires n > 0 && n % 3 != 0 && n % 5 == 0;
    //@ ensures \result == 5;
    //@ also
    //@ requires n > 0 && n % 3 == 0 && n % 5 == 0;
    //@ ensures \result == 8;
    //@ also
    //@ requires n == 0;
    //@ ensures \result == 8;
    public int fizzBuzz(int n) {
        int res = 0;
        if (n % 3 == 0) {
            res += 3;
        }
        if (n % 5 == 0) {
            res += 5;
        }
        return res;
    }
}
