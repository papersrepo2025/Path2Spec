class EvenOdd {
    /*@ requires n >= 0;
      @ ensures \result == 0 || \result == 1;
      @ ensures \result == n % 2;
      @*/
    static /*@ spec_public @*/ int isOdd(int n) {
        if (n == 0) {
          return 0;
        } else if (n == 1) {
          return 1;
        } else {
          return isEven(n - 1);
        }
    }
    
    /*@ requires n >= 0;
      @ ensures \result == 0 || \result == 1;
      @ ensures (\result == 1) <==> (n % 2 == 0);
      @*/
    static /*@ spec_public @*/ int isEven(int n) {
        if (n == 0) {
          return 1;
        } else if (n == 1) {
          return 0;
        } else {
          return isOdd(n - 1);
        }
    }
}