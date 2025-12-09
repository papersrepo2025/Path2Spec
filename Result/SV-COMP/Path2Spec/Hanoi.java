class Hanoi {

    static int counter = 0;
    //@ requires n == 1;
    //@ ensures \result == 1;
    //@ also
    //@ requires 1 <= n && n <= 30;
    //@ ensures \result == (1 << n) - 1;
    //@ ensures counter == \old(counter);
    //@ also
    //@ requires 1 <= n && n <= 30;
    //@ ensures \result == ((1 << n) - 1);
    //@ also
    //@ requires n >= 1;
    //@ ensures \result >= 1;
    //@ also
    //@ requires 1 <= n && n < 31;
    //@ ensures \result == (1 << n) - 1;
    static int hanoi(int n) {
        if (n == 1) {
          return 1;
        }
        return 2 * (hanoi(n - 1)) + 1;
    }
    //@ requires 0 <= n && n <= 30;
    //@ requires counter <= Integer.MAX_VALUE - ((1 << n) - 1);
    //@ ensures counter == \old(counter) + ((1 << n) - 1);
    //@ also
    //@ requires n == 0;
    //@ ensures counter == \old(counter);
    //@ also
    //@ requires n >= 0;
    //@ ensures counter >= \old(counter);
    //@ ensures n == 0 ==> counter == \old(counter);
    //@ ensures n > 0 ==> counter >= \old(counter) + 1;
    //@ also
    //@ requires 0 < n && n < 31;
    //@ requires counter <= Integer.MAX_VALUE - ((1 << n) - 1);
    //@ ensures counter == \old(counter) + ((1 << n) - 1);
    static void applyHanoi(int n, int from, int to, int via) {
        if (n == 0) {
          return;
        }
        // increment the number of steps
        counter++;
        applyHanoi(n - 1, from, via, to);
        applyHanoi(n - 1, via, to, from);
    }
}
