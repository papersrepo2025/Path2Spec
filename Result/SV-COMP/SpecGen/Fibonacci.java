class Fibonacci {
    /*@ 
      @ public normal_behavior
      @ ensures n < 1 ==> \result == 0;
      @ ensures n == 1 ==> \result == 1;
      @ ensures n >= 2 ==> \result == fibonacci(n - 1) + fibonacci(n - 2);
      @ ensures \result >= 0;
      @ assignable \nothing;
      @*/
    /*@ spec_public @*/ /*@ pure @*/ static int fibonacci(int n) {
        if (n < 1) {
          return 0;
        } else if (n == 1) {
          return 1;
        } else {
          return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}