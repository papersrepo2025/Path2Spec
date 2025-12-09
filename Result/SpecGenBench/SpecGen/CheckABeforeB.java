class CheckABeforeB {
    //@ requires s != null;
 
    /*@ spec_public @*/ /*@ pure @*/ public boolean checkString(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        //@ maintaining 0 <= i && i <= chars.length;
        //@ maintaining (\forall int k; 0 <= k && k < i; chars[k] == 'a');
        //@ decreases chars.length - i;
        while (i < chars.length && chars[i] == 'a') {
            i++;
        }
	int j = i;
        //@ maintaining 0 <= i && i <= j && j <= chars.length;
        //@ maintaining (\forall int k; 0 <= k && k < i; chars[k] == 'a');
        //@ maintaining (\forall int k; i <= k && k < j; chars[k] == 'b');
        //@ decreases chars.length - j;
        while (j < chars.length && chars[j] == 'b') {
            j++;
        }
        return j == chars.length;
    }
}
