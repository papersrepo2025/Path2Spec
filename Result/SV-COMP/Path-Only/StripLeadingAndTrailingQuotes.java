public class StripLeadingAndTrailingQuotes {

    //@ requires str != null;
    //@ requires str.length() == 0 || (str.length() > 0 && str.charAt(0) != '"' && str.charAt(str.length() - 1) != '"');
    //@ ensures \result == \old(str);
    static String stripLeadingAndTrailingQuotes(String str) {
        boolean temp_Boolean = false;
        temp_Boolean = str.startsWith("\"");
        if (temp_Boolean)
        {
            str = str.substring(1, str.length());
        }
        temp_Boolean = str.endsWith("\"");
        if (temp_Boolean)
        {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
    
}