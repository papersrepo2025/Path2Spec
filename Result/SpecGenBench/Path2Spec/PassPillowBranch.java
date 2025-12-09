
class PassPillowBranch {
    //@ requires n == 1;
//@ requires (n - 1) * 2 != 0; // This ensures no division by zero;
//@ ensures \result == time + 1 || \result == n * 2 - time - 1;
    public int passPillow(int n, int time) {
        time = time % (n - 1) * 2;
        if (time < n) {
            return time + 1;
        }
        return n * 2 - time - 1;
    }
}
