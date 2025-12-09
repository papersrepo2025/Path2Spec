public class ChangeCase {
    //@ requires this != null;
    public char changeCase(char c) {
        char result = ' ';    
        if (c > 'z') {
            //@ requires c > 'z';
            //@ requires Integer.MIN_VALUE <= c && c <= Integer.MAX_VALUE;
            //@ ensures \result == c;
            result = c;
        } else if (c >= 'a') {
            //@ requires Integer.MIN_VALUE <= (c - 'a' + 'A') && (c - 'a' + 'A') <= Integer.MAX_VALUE;
            //@ ensures (c >= 'a') ==> \result == (char)(c - 'a' + 'A');
            result = (char)(c - 'a' + 'A');
        } else if (c > 'Z') {
            //@ ensures (c < 'a' && c > 'Z') ==> \result == c;
            result = c;
        } else if (c >= 'A') {
            //@ requires Integer.MIN_VALUE <= (c - 'A' + 'a') && (c - 'A' + 'a') <= Integer.MAX_VALUE;
            //@ ensures (c >= 'A' && c <= 'Z') ==> \result == (char)(c - 'A' + 'a');
            result = (char)(c - 'A' + 'a');
        } else {
            //@ ensures (c < 'A') ==> \result == c;
            result = c;
        }
        return result;
    }
}