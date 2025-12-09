
class ReverseString {
    //@ requires s == null;
    //@ signals (NullPointerException e) true;
    //@ also
    //@ requires s != null;
    //@ assignable s[*];
    //@ ensures s.length == \old(s.length);
    //@ ensures (\forall int k; 0 <= k && k < s.length; s[k] == ((char[]) \old(s.clone()))[\old(s.length) - 1 - k]);
    public void reverseString(char[] s) {
        int n = s.length;
        //@ maintaining 0 <= left && left <= n;
        //@ maintaining -1 <= right && right < n;
        //@ maintaining left + right == n - 1;
        //@ maintaining (\forall int k; 0 <= k && k < left; s[k] == ((char[]) \old(s.clone()))[\old(s.length) - 1 - k]);
        //@ maintaining (\forall int k; right < k && k < n; s[k] == ((char[]) \old(s.clone()))[\old(s.length) - 1 - k]);
        //@ decreases right - left + 1;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }
}