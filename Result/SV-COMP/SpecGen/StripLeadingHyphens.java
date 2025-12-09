import java.util.Arrays;

class StripLeadingHyphens {

    /*@ 
      @ requires str == null || str.length >= 2;
      @ assignable \nothing;
      @ ensures str == null ==> \result == null;
      @ ensures str != null && str[0] == '-' && str[1] == '-'
      @          ==> \result != null 
      @              && \result.length == str.length - 2
      @              && (\forall int i; 0 <= i && i < \result.length; \result[i] == str[i+2]);
 
      @          ==> \result != null 
      @              && \result.length == str.length - 1
      @              && (\forall int i; 0 <= i && i < \result.length; \result[i] == str[i+1]);
      @ ensures str != null && str[0] != '-' ==> \result == str;
      @*/
    /*@ spec_public @*/ static char[] stripLeadingHyphens(char[] str) {
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
