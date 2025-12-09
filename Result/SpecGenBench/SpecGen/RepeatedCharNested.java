public class RepeatedCharNested {

    /*@ 
      @ requires s != null;
      @ ensures \result == -1 || (0 <= \result && \result < s.length());
      @ ensures \result == -1 ==> (\forall int p, q; 0 <= p && p < q && q < s.length(); s.charAt(p) != s.charAt(q));
      @ ensures \result != -1 ==> 
      @     ((\exists int j; \result < j && j < s.length(); s.charAt(\result) == s.charAt(j)) &&
      @      (\forall int p; 0 <= p && p < \result; (\forall int q; p < q && q < s.length(); s.charAt(p) != s.charAt(q))));
      @*/
    public /*@ spec_public pure @*/ static int repeatedChar(String s) {
        /*@ 
          @ maintaining 0 <= i && i <= s.length();
          @ maintaining (\forall int p, q; 0 <= p && p < i && p < q && q < s.length(); s.charAt(p) != s.charAt(q));
          @ decreases s.length() - i;
          @*/
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            /*@
              @ maintaining i + 1 <= j && j <= s.length();
              @ maintaining (\forall int k; i < k && k < j; s.charAt(i) != s.charAt(k));
              @ decreases s.length() - j;
              @*/
            for (int j = i + 1; j < s.length(); ++j) {
                char c2 = s.charAt(j);
                if(c1 == c2)
                    return i;
            }
        }
        return -1;
    }
    
}