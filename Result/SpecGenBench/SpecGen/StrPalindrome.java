public class StrPalindrome {
   
   //@ requires str != null;
   //@ ensures \result <==> (\forall int k; 0 <= k && k < str.length(); str.charAt(k) == str.charAt(str.length() - 1 - k));
   public boolean isPalindrome(String str) {
      String reverse = "";
      
      int length = str.length();

      //@ maintaining length == str.length();
      //@ maintaining -1 <= i && i < length;
      //@ maintaining reverse.length() == length - 1 - i;
      //@ maintaining (\forall int t; 0 <= t && t < reverse.length(); reverse.charAt(t) == str.charAt(length - 1 - t));
      //@ decreases i + 1;
      for (int i = length - 1; 0 <= i; i--) {
         reverse = reverse + str.charAt(i);
	 
      }
      
      return reverse.equals(str);
   }

}