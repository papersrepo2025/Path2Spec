
class IsPalindrome {
    /*@
      @ requires s == null;
      @ signals (NullPointerException) true;
      @ also
      @ requires s != null;
      @ ensures \result <==> (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == s.charAt(s.length() - 1 - i));
      @*/
    public static /*@ pure @*/ boolean isPalindrome(String s) {
        //@ assert s != null;

        int n = s.length();

        //@ maintaining 0 <= i && i <= n && n == s.length();
        //@ maintaining (\forall int j; 0 <= j && j < i; s.charAt(j) == s.charAt(n - 1 - j));
        //@ decreases n - i;
        for(int i = 0; i < s.length(); i++) {
            //@ assume i != 0;
            //@ assert 0 <= i && i < n && 0 <= n - 1 - i && n - 1 - i < n;
            if(s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }

        //@ assert (\forall int j; 0 <= j && j < n; s.charAt(j) == s.charAt(n - 1 - j));
        return true;
    }
}