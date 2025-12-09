class Fibonacci {
    /*@ 
      @ requires n < 1;
      @ assignable \nothing;
      @ ensures \result == 0;
      @ also
      @ requires n == 1;
      @ assignable \nothing;
      @ ensures \result == 1;
      @ also
      @ requires n > 1;
      @ assignable \nothing;
      @ ensures \result >= 1;
      @*/
    static int fibonacci(int n) {
        if (n < 1) {
          return 0;
        } else if (n == 1) {
          return 1;
        } else {
          return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}