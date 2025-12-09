import java.util.Arrays;

class StripLeadingHyphens {
    //@ requires str == null || str.length >= 2;
    //@ ensures (str == null) ==> (\result == null);
    //@ also
    //@ requires str == null || str.length >= 1;
    //@ requires str == null || str[0] != '-' || str.length >= 2;
    //@ ensures (str == null) ==> (\result == null);
    //@ ensures (str != null && str.length >= 1 && str[0] != '-') ==> (\result == str);
    static char[] stripLeadingHyphens(char[] str) {
        boolean temp_Boolean = false;
        if (str == null) {
            return null;
        }
        temp_Boolean = (str[0] == '-' && str[1] == '-');
        if (temp_Boolean) {
            return Arrays.copyOfRange(str, 2, str.length);
        } else {
            temp_Boolean = str[0] == '-';
            if (temp_Boolean) {
                return Arrays.copyOfRange(str, 1, str.length);
            }
        }
        return str;
    }
    
}
