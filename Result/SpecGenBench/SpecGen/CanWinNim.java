class CanWinNim {
    //@ ensures \result <==> (n % 4 != 0);
    //@ assignable \nothing;
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}