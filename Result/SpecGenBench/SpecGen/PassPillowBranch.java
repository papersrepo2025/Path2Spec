class PassPillowBranch {
    /*@ 
      @ requires n >= 2 && time >= 0 && n <= Integer.MAX_VALUE/2;
      @ assignable \nothing;
      @ ensures (((\old(time) % (n - 1)) * 2) < n) ==> \result == ((\old(time) % (n - 1)) * 2) + 1;
      @ ensures (((\old(time) % (n - 1)) * 2) >= n) ==> \result == 2*n - ((\old(time) % (n - 1)) * 2) - 1;
      @ ensures 1 <= \result && \result <= n;
      @*/
    public int passPillow(int n, int time) {
        time = time % (n - 1) * 2;
        if (time < n) {
            return time + 1;
        }
        return n * 2 - time - 1;
    }
}