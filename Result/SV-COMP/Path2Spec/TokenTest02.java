public class TokenTest02 {
  //@ requires sentence != null;
  //@ ensures (sentence.split(" ").length <= 3) ==> \result;
  //@ also
  //@ requires sentence != null;
  //@ ensures true;
  public static boolean f(String sentence) {
    String[] tokens = sentence.split(" ");

    int i = 0;
    //@ maintaining 0 <= i && i <= tokens.length;
    //@ decreases tokens.length - i;
    for (String token : tokens) {
      //@ assume i < tokens.length;
      //@ assert tokens.length <= 3 ==> i != 3;
      if (i == 3)
        if (!token.equals("genneration"))
          return false;
      ++i;
    }
    return true;
  }
}
