public class Primes {

  // Multiplies two integers n and m
  //@ requires m < 0;
  //@ ensures \result == mult(n, -m);
  //@ also
  //@ requires m == 0;
  //@ ensures \result == 0;
  //@ also
  //@ requires m == 1;
  //@ ensures \result == 1;
  //@ also
  //@ requires m > 1;
  //@ ensures \result == n + mult(n, m - 1);
  public static /*@ pure @*/ int mult(int n, int m) {
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
  //@ requires m < 0;
  //@ ensures \result == multiple_of(n, -m);
  //@ also
  //@ requires m >= 0 && n < 0;
  //@ ensures \result == multiple_of(-n, m);
  //@ also
  //@ requires m == 0 && n >= 0;
  //@ ensures \result == 0;
  //@ also
  //@ requires m > 0 && n == 0;
  //@ ensures \result == 1;
  //@ also
  //@ requires m > 0 && n > 0;
  //@ ensures \result == multiple_of(n - m, m);
  //@ also
  //@ ensures \result == 0 || \result == 1;
  public static /*@ pure @*/ int multiple_of(int n, int m) {
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
  //@ ensures \result == is_prime_(n, n - 1);
  public static /*@ pure @*/ int is_prime(int n) {
    return is_prime_(n, n - 1);
  }

  //@ requires n <= 1;
  //@ ensures \result == 0;
  //@ also
  //@ requires n == 2;
  //@ ensures \result == 1;
  //@ also
  //@ requires n > 2 && m <= 1;
  //@ ensures \result == 1;
  //@ also
  //@ requires n > 2 && m > 1 && multiple_of(n, m) == 0;
  //@ ensures \result == 0;
  //@ also
  //@ requires n > 2 && m > 1 && multiple_of(n, m) != 0;
  //@ ensures \result == is_prime_(n, m - 1);
  //@ also
  //@ ensures \result == 0 || \result == 1;
  public static /*@ pure @*/ int is_prime_(int n, int m) {
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