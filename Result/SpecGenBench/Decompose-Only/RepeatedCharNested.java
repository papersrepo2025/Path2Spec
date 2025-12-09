
public class RepeatedCharNested {

    /*@ 
      @ requires s != null;
      @ ensures \result == -1 ==> (\forall int i; 0 <= i && i < s.length();
      @                              (\forall int j; i + 1 <= j && j < s.length();
      @                                   s.charAt(i) != s.charAt(j)));
      @ ensures \result != -1 ==> (0 <= \result && \result < s.length()) &&
      @                             (\exists int j; \result + 1 <= j && j < s.length();
      @                                  s.charAt(\result) == s.charAt(j)) &&
      @                             (\forall int k; 0 <= k && k < \result;
      @                                  (\forall int j; k + 1 <= j && j < s.length();
      @                                      s.charAt(k) != s.charAt(j)));
      @*/
    public static int repeatedChar(String s) {
        //@ maintaining 0 <= i && i <= s.length();
        //@ maintaining (\forall int k; 0 <= k && k < i; (\forall int j; k + 1 <= j && j < s.length(); s.charAt(k) != s.charAt(j)));
        //@ decreases s.length() - i;
        for (int i = 0; i < s.length(); ++i) {
            //@ assume i != 0;
            char c1 = s.charAt(i);
            //@ maintaining i + 1 <= j && j <= s.length();
            //@ maintaining (\forall int k; i + 1 <= k && k < j; s.charAt(k) != c1);
            //@ decreases s.length() - j;
            for (int j = i + 1; j < s.length(); ++j) {
                //@ assume i != 0;
                char c2 = s.charAt(j);
                if(c1 == c2)
                    return i;
            }
        }
        return -1;
    }
    
}