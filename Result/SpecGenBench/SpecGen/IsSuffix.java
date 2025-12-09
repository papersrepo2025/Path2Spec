public class IsSuffix {

 
    //@ ensures !\result ==> (pat.length() > txt.length()) || (\exists int k; 0 <= k && k < pat.length(); (txt.length() - pat.length() + k < 0) || pat.charAt(k) != txt.charAt(txt.length() - pat.length() + k));
    public boolean isSuffix (String pat, String txt) {
	
        int i = pat.length() - 1;

        //@ maintaining -1 <= i && i < pat.length();
        //@ maintaining (\forall int k; i < k && k < pat.length();
        //@                 0 <= txt.length() - pat.length() + k && txt.length() - pat.length() + k < txt.length() &&
        //@                 pat.charAt(k) == txt.charAt(txt.length() - pat.length() + k));
        //@ decreases i + 1;
        while(i >= 0) {
	    int j = i - pat.length() + txt.length();
            if(j < 0 || pat.charAt(i) != txt.charAt(j))
                return false;
            i = i - 1;
        }

        return true;
    }

}
