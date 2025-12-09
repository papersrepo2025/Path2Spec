class PassPillow {
    //@ requires n >= 2 && time >= 0;
    //@ ensures 1 <= \result && \result <= n;
    //@ ensures time == \old(time) % ((n - 1) * 2);
    //@ ensures \result == (time < n ? time + 1 : n * 2 - time - 1);
    /*@ spec_public @*/ public int passPillow(int n, int time) {
        time %= (n - 1) * 2;
        return time < n ? time + 1 : n * 2 - time - 1;
    }
}