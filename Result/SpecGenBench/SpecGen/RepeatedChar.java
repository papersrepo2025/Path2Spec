import java.util.HashSet;
/*@ model import java.util.Set; @*/

class RepeatedChar {
    //@ requires s != null;
    //@ ensures (\exists int j; 0 <= j && j < s.length(); (\exists int k; 0 <= k && k < j; s.charAt(k) == s.charAt(j)))
    //@          ==> (\exists int j; 0 <= j && j < s.length()
    //@                       && (\exists int k; 0 <= k && k < j; s.charAt(k) == s.charAt(j))
    //@                       && (\forall int j2; 0 <= j2 && j2 < j; (\forall int k2; 0 <= k2 && k2 < j2; s.charAt(k2) != s.charAt(j2)))
    //@                       && \result == s.charAt(j));
    //@ ensures !(\exists int j; 0 <= j && j < s.length(); (\exists int k; 0 <= k && k < j; s.charAt(k) == s.charAt(j))) ==> \result == ' ';
    public /*@ spec_public @*/ char repeatedCharacter(String s) {
        Set<Character> seen = new HashSet<Character>();
        
        /*@ maintaining 0 <= i && i <= s.length();
          @ maintaining (\forall int j1; 0 <= j1 && j1 < i;
          @                 (\forall int j2; 0 <= j2 && j2 < j1; s.charAt(j2) != s.charAt(j1)));
          @ decreases s.length() - i;
          @*/
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!seen.add(ch)) {
                return ch;
            }
        }
        // impossible
        return ' ';
    }
}