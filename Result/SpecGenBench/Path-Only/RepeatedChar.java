
import java.util.HashSet;
import java.util.Set;

class RepeatedChar {
    //@ requires s != null;
//@ ensures s.length() == 0 ==> \result == ' ';
    public char repeatedCharacter(String s) {
        Set<Character> seen = new HashSet<Character>();
        
        //@ loop_invariant 0 <= i && i <= s.length() && seen != null;
//@ decreases s.length() - i;
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
