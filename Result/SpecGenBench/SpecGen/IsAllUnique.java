class IsAllUnique {
    //@ requires str != null;
    //@ requires (\forall int k; 0 <= k && k < str.length(); 'a' <= str.charAt(k) && str.charAt(k) <= 'z');
 
    boolean isAllUnique(String str) {
        int len = str.length();
        if (len > 26) {
            return false;
        }
        int num = 0;
        //@ maintaining 0 <= i && i <= len;
        //@ maintaining (\forall int p,q; 0 <= p && p < q && q < i; str.charAt(p) != str.charAt(q));
        //@ maintaining (\forall int p; 0 <= p && p < i; (num & (1 << (str.charAt(p) - 'a'))) != 0);
        //@ decreases len - i;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            int index = c - 'a';
            if ((num & (1 << index)) != 0) {
                return false;
            } else {
                num |= (1 << index);
            }
        }
        return true;
    }
}
