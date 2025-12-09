class ReverseString {
    //@ requires s != null;
    //@ assignable s[*];
    //@ ensures s.length == \old(s.length);
 
    public void reverseString(char[] s) {
        int n = s.length;
        //@ maintaining n == s.length;
        //@ maintaining 0 <= left && left <= n && -1 <= right && right < n;
        //@ maintaining left + right == n - 1;
 
 
        //@ decreases right - left;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }
}
