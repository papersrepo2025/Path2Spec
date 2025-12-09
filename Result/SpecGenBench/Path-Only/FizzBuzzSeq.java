
class FizzBuzzSeq {
    //@ requires n % 3 == 0 && n % 5 == 0;
    //@ ensures \result == 8;
    //@ also
    //@ requires n % 3 == 0 && n % 5 != 0;
    //@ ensures \result == 3;
    //@ also
    //@ requires n % 3 != 0 && n % 5 == 0 && Integer.MIN_VALUE <= n && n <= Integer.MAX_VALUE;
    //@ ensures \result == 5;
    //@ also
    //@ requires Integer.MIN_VALUE <= n && n <= Integer.MAX_VALUE;
    //@ ensures n % 3 != 0 && n % 5 != 0 ==> \result == 0;
    public int fizzBuzz(int n) {
        int res = 0;
        res += ((n % 3 == 0) ? 3 : 0);
        res += ((n % 5 == 0) ? 5 : 0);
        return res;
    }
}
