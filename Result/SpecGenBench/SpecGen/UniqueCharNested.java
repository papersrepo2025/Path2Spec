public class UniqueCharNested {

    /*@ public normal_behavior
      @   requires s != null;
      @   assignable \nothing;
 
      @           <==> (\forall int i; 0 <= i && i < s.length();
      @                 (\exists int j; 0 <= j && j < s.length() && j != i; s.charAt(i) == s.charAt(j)));
      @   ensures \result != -1 ==>
      @           (0 <= \result && \result < s.length()
      @            && (\forall int j; 0 <= j && j < s.length() && j != \result; s.charAt(\result) != s.charAt(j))
      @            && (\forall int k; 0 <= k && k < \result;
      @                   (\exists int j; 0 <= j && j < s.length() && j != k; s.charAt(k) == s.charAt(j))));
      @*/
    public static int uniqueChar(String s) {
        //@ maintaining 0 <= i && i <= s.length();
        //@ maintaining (\forall int k; 0 <= k && k < i; (\exists int j; 0 <= j && j < s.length() && j != k; s.charAt(k) == s.charAt(j)));
        //@ decreases s.length() - i;
        for (int i = 0; i < s.length(); ++i) {
            int j = 0;
            //@ maintaining 0 <= j && j <= s.length();
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
