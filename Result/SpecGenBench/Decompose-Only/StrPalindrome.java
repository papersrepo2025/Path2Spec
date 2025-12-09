public class StrPalindrome {
   //@ public ghost int i;
   
   //@ ensures str != null && (Integer.MIN_VALUE <= str.length() && str.length() <= Integer.MAX_VALUE) && i < 0 && (str != null ==> 0 <= str.length() && str.length() <= Integer.MAX_VALUE) && (0 <= str.length() && str.length() <= Integer.MAX_VALUE) ==> \result == (str.length() == 0) && \result == ("".equals(str)) && (i < 0 ==> \result == (str.length() == 0));
   public boolean isPalindrome(String str) {
      String reverse = "";
      
      int length = str.length();

      return reverse.equals(str);
   }

}