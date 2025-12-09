class EvenOdd {
    //@ assignable \nothing;
    //@ requires n == 0;
    //@ ensures \result == 0;
    //@ also
    //@ requires n == 1;
    //@ ensures \result == 1;
    //@ also
    //@ requires n >= 2;
    //@ ensures \result == isEven(n - 1);
    /*@ pure @*/ static int isOdd(int n) {
        if (n == 0) {
          return 0;
        } else if (n == 1) {
          return 1;
        } else {
          return isEven(n - 1);
        }
    }
    
    //@ assignable \nothing;
    //@ requires n == 0;
    //@ ensures \result == 1;
    //@ also
    //@ requires n == 1;
    //@ ensures \result == 0;
    //@ also
    //@ requires n >= 2;
    //@ ensures \result == isOdd(n - 1);
    /*@ pure @*/ static int isEven(int n) {
        if (n == 0) {
          return 1;
        } else if (n == 1) {
          return 0;
        } else {
          return isOdd(n - 1);
        }
    }
}