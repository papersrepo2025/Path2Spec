
public class IsPrefix {
    //@ requires pat != null && txt != null;
    //@ ensures (pat.length() == 0) ==> (\result == true);
    //@ also
    //@ requires pat != null && txt != null;
    //@ requires pat.length() <= txt.length();
    //@ requires (\forall int i; 0 <= i && i < pat.length(); pat.charAt(i) == txt.charAt(i));
    //@ ensures \result == true;
    //@ also
    //@ requires pat != null && txt != null;
    //@ ensures \result == false ==> pat.length() > txt.length() || (\exists int i; 0 <= i && i < pat.length(); pat.charAt(i) != txt.charAt(i));
    public boolean isPrefix (String pat, String txt) {
        int i = 0;

        //@ maintaining 0 <= i && i <= pat.length();
        //@ maintaining i <= txt.length();
        //@ decreases pat.length() - i;
        while(i < pat.length()) {
            if(i >= txt.length() || pat.charAt(i) != txt.charAt(i))
                return false;
            i = i + 1;
        }

        return true;
    }

}
