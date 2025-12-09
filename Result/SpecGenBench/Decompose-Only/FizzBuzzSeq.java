class FizzBuzzSeq {
    //@ requires n % 3 != 0 && n % 5 != 0;
    //@ assignable \nothing;
    //@ ensures \result == 0;
    //@ also
    //@ requires n % 3 == 0 && n % 5 != 0;
    //@ assignable \nothing;
    //@ ensures \result == 3;
    //@ also
    //@ requires n % 3 != 0 && n % 5 == 0;
    //@ assignable \nothing;
    //@ ensures \result == 5;
    //@ also
    //@ requires n % 3 == 0 && n % 5 == 0;
    //@ assignable \nothing;
    //@ ensures \result == 8;
    public int fizzBuzz(int n) {
        int res = 0;
        res += ((n % 3 == 0) ? 3 : 0);
        res += ((n % 5 == 0) ? 5 : 0);
        return res;
    }
}