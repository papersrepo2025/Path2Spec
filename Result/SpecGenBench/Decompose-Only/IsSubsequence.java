class IsSubsequence {
    //@ requires s != null;
    //@ requires t != null;
    //@ requires 0 <= s.length() && s.length() <= Integer.MAX_VALUE;
    //@ requires 0 <= t.length() && t.length() <= Integer.MAX_VALUE;
    //@ requires s.length() < Integer.MAX_VALUE && t.length() < Integer.MAX_VALUE;
    //@ requires s.length() > 0 && t.length() > 0;
    //@ assignable \nothing;
    //@ ensures s.length() == 0 ==> \result == true;
    //@ ensures t.length() == 0 && s.length() > 0 ==> \result == false;
    //@ ensures \result ==> s.length() <= t.length();
    //@ also
    //@ requires !(0 < s.length() && 0 < t.length());
    //@ requires Integer.MIN_VALUE <= s.length() && s.length() <= Integer.MAX_VALUE;
    //@ requires Integer.MIN_VALUE <= t.length() && t.length() <= Integer.MAX_VALUE;
    //@ ensures \result == (s.length() == 0);
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;

        //@ maintaining 0 <= i && i <= n;
        //@ maintaining 0 <= j && j <= m;
        //@ maintaining n == s.length() && m == t.length();
        //@ maintaining i <= j;
        //@ decreases m - j;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}