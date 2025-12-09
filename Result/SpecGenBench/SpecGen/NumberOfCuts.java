class NumberOfCuts {
    /*@ ensures \result == (n == 1 ? 0 : (n % 2 == 0 ? n / 2 : n));
      @ assignable \nothing;
      @*/
    public int numberOfCuts(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return n / 2;
        }
        return n;
    }
}