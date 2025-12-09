public class Primes {

  // Multiplies two integers n and m
  /*@ public normal_behavior
    @   ensures m < 0 ==> \result == mult(n, -m);
    @   ensures m == 0 ==> \result == 0;
    @   ensures m == 1 ==> \result == 1;
    @   ensures m > 1 ==> \result == n + mult(n, m - 1);
    @   assignable \nothing;
    @*/
  /*@ spec_public @*/ /*@ pure @*/ static int mult(int n, int m) {
    if (m < 0) {
      return mult(n, -m);
    }
    if (m == 0) {
      return 0;
    }
    if (m == 1) {
      return 1;
    }
    return n + mult(n, m - 1);
  }

  // Is n a multiple of m?
  /*@ public normal_behavior
    @   ensures m < 0 ==> \result == multiple_of(n, -m);
    @   ensures m >= 0 && n < 0 ==> \result == multiple_of(-n, m);
    @   ensures m == 0 && n >= 0 ==> \result == 0;
    @   ensures m > 0 && n == 0 ==> \result == 1;
    @   ensures m > 0 && n > 0 ==> \result == multiple_of(n - m, m);
    @   ensures \result == 0 || \result == 1;
    @   assignable \nothing;
    @*/
  /*@ spec_public @*/ /*@ pure @*/ static int multiple_of(int n, int m) {
    if (m < 0) {
      return multiple_of(n, -m);
    }
    if (n < 0) {
      return multiple_of(-n, m); // false
    }
    if (m == 0) {
      return 0; // false
    }
    if (n == 0) {
      return 1; // true
    }
    return multiple_of(n - m, m);
  }

  // Is n prime?
  /*@ public normal_behavior
    @   ensures \result == is_prime_(n, n - 1);
    @   ensures \result == 0 || \result == 1;
    @   assignable \nothing;
    @*/
  /*@ spec_public @*/ /*@ pure @*/ static int is_prime(int n) {
    return is_prime_(n, n - 1);
  }

  /*@ public normal_behavior
    @   ensures n <= 1 ==> \result == 0;
    @   ensures n == 2 ==> \result == 1;
    @   ensures n > 2 && m <= 1 ==> \result == 1;
    @   ensures n > 2 && m > 1 && multiple_of(n, m) == 0 ==> \result == 0;
    @   ensures n > 2 && m > 1 && multiple_of(n, m) != 0 ==> \result == is_prime_(n, m - 1);
    @   ensures \result == 0 || \result == 1;
    @   assignable \nothing;
    @*/
  /*@ spec_public @*/ /*@ pure @*/ static int is_prime_(int n, int m) {
    if (n <= 1) {
      return 0; // false
    } else if (n == 2) {
      return 1; // true
    } else {
      if (m <= 1) {
        return 1; // true
      } else {
        if (multiple_of(n, m) == 0) {
          return 0; // false
        }
        return is_prime_(n, m - 1);
      }
    }
  }
  
}