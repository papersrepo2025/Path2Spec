class JewelsInStones {
    //@ public ghost int i;
    //@ public model int i;
    //@ requires jewels != null;
    //@ requires stones != null;
    //@ also
    //@ requires 0 <= jewels.length() && jewels.length() <= Integer.MAX_VALUE;
    //@ requires 0 <= stones.length() && stones.length() <= Integer.MAX_VALUE;
    //@ requires (\forall int i; i >= stones.length(); true);
    //@ ensures \result == 0;
    //@ ensures (\forall int i; i >= stones.length(); \result == 0);
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    //@ also
    //@ requires i >= stones.length();
    //@ ensures \result == 0;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    //@ also
    //@ requires stones.length() <= 0;
    //@ ensures \result == 0;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    //@ also
    //@ requires Integer.MIN_VALUE <= i && i <= Integer.MAX_VALUE;
    //@ requires i >= stones.length();
    //@ ensures \result == 0;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    public int numJewelsInStones(String jewels, String stones) {
        int jewelsCount = 0;
        int jewelsLength = jewels.length(), stonesLength = stones.length();
        return jewelsCount;
    }
}