
class CheckABeforeB {
    //@ requires s != null && s.length() == 0;
//@ ensures \result == true;
    public boolean checkString(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        //@ maintaining 0 <= i && i <= chars.length;
        //@ decreases chars.length - i;
        while (i < chars.length && chars[i] == 'a') {
            i++;
        }
        int j = i;
        //@ maintaining i <= j && j <= chars.length;
        //@ decreases chars.length - j;
        while (j < chars.length && chars[j] == 'b') {
            j++;
        }
        return j == chars.length;
    }
}
