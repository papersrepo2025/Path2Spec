import java.util.Arrays;

class StripLeadingHyphens {

    /*@ 
      @ public normal_behavior
      @   requires str == null;
      @   assignable \nothing;
      @   ensures \result == null;
      @ also
      @ public normal_behavior
      @   requires str != null && str.length >= 2 && str[0] == '-' && str[1] == '-';
      @   assignable \nothing;
      @   ensures true;
      @ also
      @ public normal_behavior
      @   requires str != null && str.length >= 2 && !(str[0] == '-' && str[1] == '-') && str[0] == '-';
      @   assignable \nothing;
      @   ensures true;
      @ also
      @ public normal_behavior
      @   requires str != null && str.length >= 2 && !(str[0] == '-' && str[1] == '-') && str[0] != '-';
      @   assignable \nothing;
      @   ensures \result == \old(str);
      @*/
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