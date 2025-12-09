public class Primes {

  //@ requires m < 0;
  //@ requires m != Integer.MIN_VALUE;
  //@ requires (\forall int j; 0 <= j && j <= (-m - 1); Integer.MIN_VALUE <= 1 + j * n && 1 + j * n <= Integer.MAX_VALUE);
  //@ ensures \result == ((-m - 1) * n) + 1;
  //@ also
  //@ requires m == 0;
  //@ ensures \result == 0;
  //@ also
  //@ requires m >= 1;
  //@ requires (\forall int j; 0 <= j && j <= (m - 1); Integer.MIN_VALUE <= 1 + j * n && 1 + j * n <= Integer.MAX_VALUE);
  //@ ensures \result == ((m - 1) * n) + 1;
  // Multiplies two integers n and m
  //@ ensures true;
  //@ also
  //@ ensures m == 0 ==> \result == 0;
  //@ ensures m == 1 ==> \result == 1;
  //@ also
  //@ requires m == 1;
  //@ ensures \result == 1;
  //@ also
  //@ requires m == 0;
  //@ ensures \result == 0;
  static int mult(int n, int m) {
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
  //@ ensures m == 0 ==> \result == 0;
  //@ ensures n == 0 && m != 0 ==> \result == 1;
  //@ also
  //@ ensures m == 0 ==> \result == 0;
  //@ ensures m != 0 && n == 0 ==> \result == 1;
  //@ ensures \result == 0 || \result == 1;
  static int multiple_of(int n, int m) {
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
  //@ ensures true;
  //@ also
  //@ ensures \result == 0 || \result == 1;
  //@ ensures n <= 1 ==> \result == 0;
  //@ ensures n == 2 ==> \result == 1;
  //@ also
  //@ requires n <= 1;
  //@ ensures \result == 0;
  //@ also
  //@ requires n > 1;
  //@ ensures true;
  //@ also
  //@ requires n == 2;
  //@ ensures \result == 1;
  //@ also
  //@ ensures n <= 1 ==> \result == 0;
  static int is_prime(int n) {
    return is_prime_(n, n - 1);
  }
  //@ requires true;
  //@ ensures n <= 1 ==> \result == 0;
  //@ ensures n == 2 ==> \result == 1;
  //@ ensures n > 2 && m <= 1 ==> \result == 1;
  //@ also
  //@ ensures \result == 0 || \result == 1;
  //@ ensures n <= 1 ==> \result == 0;
  //@ ensures n == 2 ==> \result == 1;
  //@ also
  //@ requires n <= 1;
  //@ ensures \result == 0;
  //@ also
  //@ requires n > 1;
  //@ ensures true;
  //@ also
  //@ requires n == 2;
  //@ ensures \result == 1;
  //@ also
  //@ ensures n <= 1 ==> \result == 0;
  static int is_prime_(int n, int m) {
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
