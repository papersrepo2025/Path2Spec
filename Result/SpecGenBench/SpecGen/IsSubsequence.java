class IsSubsequence {
    /*@
      @ requires s != null && t != null;
      @ assignable \nothing;
      @ ensures \result <==> isSubseq(s, t);
      @*/
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;

        /*@
          @ maintaining 0 <= i && i <= n;
          @ maintaining 0 <= j && j <= m;
          @ maintaining i <= j;
          @ maintaining isSubseq(s.substring(0, i), t.substring(0, j));
          @ decreases m - j;
          @*/
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
    
    /*@
      @ public model pure static boolean isSubseq(String s, String t) {
      @     if (s == null || t == null) return false;
      @     if (s.length() == 0) return true;
      @     if (t.length() == 0) return false;
      @     return (s.charAt(0) == t.charAt(0)) 
      @         ? isSubseq(s.substring(1), t.substring(1)) 
      @         : isSubseq(s, t.substring(1));
      @ }
      @*/
}