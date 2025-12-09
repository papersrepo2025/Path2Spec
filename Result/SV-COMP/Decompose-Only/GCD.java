class GCD {
    //@ requires y1 <= 0 || y2 <= 0;
    //@ ensures \result == 0;
    //@ also
    //@ requires y1 > 0 && y2 > 0 && y1 == y2;
    //@ ensures \result == y1;
    //@ also
    //@ requires y1 > 0 && y2 > 0 && y1 > y2;
    //@ ensures true;
    //@ also
    //@ requires y1 > 0 && y2 > 0 && y1 < y2;
    //@ ensures true;
    static int gcd(int y1, int y2) {
        if (y1 <= 0 || y2 <= 0) {
          return 0;
        }
        if (y1 == y2) {
          return y1;
        }
        if (y1 > y2) {
          return gcd(y1 - y2, y2);
        }
        return gcd(y1, y2 - y1);
    }
}