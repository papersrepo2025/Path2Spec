class IsPalindrome {
    //@ requires s != null;
    //@ assignable \nothing;
    //@ ensures \result <==> (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == s.charAt(s.length() - 1 - i));
    public static boolean isPalindrome(String s) {
        int n = s.length();

        //@ maintaining 0 <= i && i <= n;
        //@ maintaining n == s.length();
        //@ maintaining (\forall int k; 0 <= k && k < i; s.charAt(k) == s.charAt(n - 1 - k));
        //@ decreases n - i;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}