
public class RepeatedCharNested {

    //@ requires s != null;
    //@ ensures \result == -1 || (0 <= \result && \result < s.length());
    public static int repeatedChar(String s) {
        //@ loop_invariant 0 <= i && i <= s.length();
//@ decreases s.length() - i;
        for (int i = 0; i < s.length(); ++i) {
            char c1 = s.charAt(i);
            //@ loop_invariant i + 1 <= j && j <= s.length();
//@ decreases s.length() - j;
            for (int j = i + 1; j < s.length(); ++j) {
                char c2 = s.charAt(j);
                if(c1 == c2)
                    return i;
            }
        }
        return -1;
    }
    
}
