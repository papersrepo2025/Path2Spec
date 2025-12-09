class EvenOdd {
    //@ requires n >= 0 && n % 2 == 0;
    //@ ensures \result == 0;
    //@ also
    //@ requires n >= 0;
    //@ ensures 0 <= \result && \result <= 1;
    //@ ensures (n == 0) ==> \result == 0;
    //@ ensures (n == 1) ==> \result == 1;
    //@ also
    //@ requires true;
    //@ ensures \result == 0 || \result == 1;
    //@ also
    //@ requires n >= 0 && n % 2 == 1;
    //@ ensures \result == 1;
    //@ also
    //@ requires n >= 0;
    //@ ensures (n % 2 == 1) ==> \result == 1;
    //@ ensures (n % 2 == 0) ==> \result == 0;
    //@ ensures \result == 0 || \result == 1;
    //@ also
    //@ requires n >= 0;
    //@ ensures n % 2 == 0 ==> \result == 0;
    //@ ensures n % 2 != 0 ==> \result == 1;
    //@ ensures \result == 0 || \result == 1;
    //@ also
    //@ requires n >= 0 && n % 2 != 0;
    //@ ensures \result == 1;
    static int isOdd(int n) {
        if (n == 0) {
          return 0;
        } else if (n == 1) {
          return 1;
        } else {
          return isEven(n - 1);
        }
    }
    //@ requires n >= 0 && n % 2 == 1;
    //@ ensures \result == 0;
    //@ also
    //@ requires n >= 0;
    //@ ensures 0 <= \result && \result <= 1;
    //@ ensures (n == 0) ==> \result == 1;
    //@ ensures (n == 1) ==> \result == 0;
    //@ also
    //@ requires true;
    //@ ensures \result == 0 || \result == 1;
    //@ also
    //@ requires n >= 0 && n % 2 == 0;
    //@ ensures \result == 1;
    //@ also
    //@ requires n >= 0;
    //@ ensures (n % 2 == 0) ==> \result == 1;
    //@ ensures (n % 2 == 1) ==> \result == 0;
    //@ ensures \result == 0 || \result == 1;
    //@ also
    //@ requires n >= 0;
    //@ ensures n % 2 == 0 ==> \result == 1;
    //@ ensures n % 2 != 0 ==> \result == 0;
    //@ ensures \result == 0 || \result == 1;
    //@ also
    //@ requires n >= 0 && n % 2 != 0;
    //@ ensures \result == 0;
    static int isEven(int n) {
        if (n == 0) {
          return 1;
        } else if (n == 1) {
          return 0;
        } else {
          return isOdd(n - 1);
        }
    }
}
