
public class IsSuffix {
    //@ requires pat != null && txt != null;
    //@ ensures pat.length() == 0 ==> \result == true;
    //@ also
    //@ requires pat != null && txt != null;
    //@ requires pat.length() <= txt.length();
    //@ requires (\forall int i; 0 <= i && i < pat.length(); pat.charAt(i) == txt.charAt(txt.length() - pat.length() + i));
    //@ ensures \result == true;
    public boolean isSuffix (String pat, String txt) {

        int i = pat.length() - 1;

        //@ maintaining i >= -1 && i < pat.length();
        //@ decreases i;
        while(i >= 0) {
            int j = i - pat.length() + txt.length();
            if(j < 0 || pat.charAt(i) != txt.charAt(j))
                return false;
            i = i - 1;
        }

        return true;
    }

}
