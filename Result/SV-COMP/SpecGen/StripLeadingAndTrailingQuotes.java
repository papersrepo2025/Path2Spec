class StripLeadingAndTrailingQuotes {

    //@ requires str != null;
    //@ assignable \nothing;
    //@ ensures \result != null;
    //@ ensures \result.equals( ((\old(str).startsWith("\"") ? \old(str).substring(1, \old(str).length()) : \old(str)).endsWith("\""))
    //@                      ? ((\old(str).startsWith("\"") ? \old(str).substring(1, \old(str).length()) : \old(str)).substring(0, (\old(str).startsWith("\"") ? \old(str).substring(1, \old(str).length()) : \old(str)).length() - 1))
    //@                      :  (\old(str).startsWith("\"") ? \old(str).substring(1, \old(str).length()) : \old(str)) );
    /*@ pure @*/ /*@ spec_public @*/ static String stripLeadingAndTrailingQuotes(String str) {
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