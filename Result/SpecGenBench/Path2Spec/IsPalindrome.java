
class IsPalindrome {
    //@ requires s != null && s.length() == 1;
//@ ensures \result == true;
    public static boolean isPalindrome(String s) {
        int n = s.length();

        //@ maintaining 0 <= i && i <= n;
//@ decreases n - i;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }
        
        return true;
    }
}
