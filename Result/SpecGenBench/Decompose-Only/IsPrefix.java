
public class IsPrefix {

    //@ requires pat != null && txt != null;
    //@ ensures \result ==> (pat.length() <= txt.length() && (\forall int k; 0 <= k && k < pat.length(); pat.charAt(k) == txt.charAt(k)));
    //@ also ensures !\result ==> (!(pat.length() <= txt.length()) || (\exists int k; 0 <= k && k < pat.length(); pat.charAt(k) != txt.charAt(k)));
    public boolean isPrefix (String pat, String txt) {
        int i = 0;

        //@ maintaining 0 <= i && i <= pat.length() && i <= txt.length();
        //@ maintaining (\forall int k; 0 <= k && k < i; pat.charAt(k) == txt.charAt(k));
        //@ decreases pat.length() - i;
        while(i < pat.length()) {
            if(i >= txt.length() || pat.charAt(i) != txt.charAt(i))
                return false;
            i = i + 1;
            //@ assume i != 0;
        }

        return true;
    }

}