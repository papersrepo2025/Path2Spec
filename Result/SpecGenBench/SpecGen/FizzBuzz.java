class FizzBuzz {
    /*@
      @ ensures \result == ((n % 3 == 0 ? 3 : 0) + (n % 5 == 0 ? 5 : 0));
      @ ensures 0 <= \result && \result <= 8;
      @ assignable \nothing;
      @*/
    public /*@ pure @*/ int fizzBuzz(int n) {
        int res = 0;
        if (n % 3 == 0) {
            res += 3;
        }
        if (n % 5 == 0) {
            res += 5;
        }
        return res;
    }
}