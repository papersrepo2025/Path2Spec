
class PassPillow {
    //@ requires n > 1; // n must be greater than 1 to avoid divide by zero
    //@ requires time >= 0; // time must be non-negative
    //@ ensures \result >= 1 && \result <= n; // result must be within the range of people
    public int passPillow(int n, int time) {
        time %= (n - 1) * 2;
        return time < n ? time + 1 : n * 2 - time - 1;
    }
}
