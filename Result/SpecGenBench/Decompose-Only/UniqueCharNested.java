public class UniqueCharNested {

    //@ requires s != null;
    //@ requires s.length() == 0;
    //@ ensures \result == -1;
    //@ also
    //@ requires s != null;
    //@ requires s.length() > 0 && (\exists int r; 0 <= r && r < s.length(); (\forall int q; 0 <= q && q < s.length(); q == r || s.charAt(q) != s.charAt(r)));
    //@ ensures 0 <= \result && \result < s.length();
    //@ ensures (\forall int q; 0 <= q && q < s.length(); q == \result || s.charAt(q) != s.charAt(\result));
    //@ ensures (\forall int t; 0 <= t && t < \result; (\exists int u; 0 <= u && u < s.length(); u != t && s.charAt(u) == s.charAt(t)));
    //@ also
    //@ requires s != null;
    //@ requires s.length() > 0 && (\forall int r; 0 <= r && r < s.length(); (\exists int q; 0 <= q && q < s.length(); q != r && s.charAt(q) == s.charAt(r)));
    //@ ensures \result == -1;
    public static int uniqueChar(String s) {
        //@ maintaining s != null && 0 <= i && i <= s.length();
        //@ maintaining (\forall int t; 0 <= t && t < i; (\exists int u; 0 <= u && u < s.length(); u != t && s.charAt(u) == s.charAt(t)));
        //@ decreases s.length() - i;
        for (int i = 0; i < s.length(); ++i) {
            int j = 0;
            //@ maintaining s != null && 0 <= j && j <= s.length();
            //@ maintaining 0 <= i && i < s.length();
            //@ maintaining (\forall int t; 0 <= t && t < j; t == i || s.charAt(i) != s.charAt(t));
            //@ decreases s.length() - j;
            while(j < s.length()) {
                if(i != j && s.charAt(i) == s.charAt(j))
                    break;
                j++;
            }
            if(j == s.length())
                return i;
        }
        return -1;
    }
    
}