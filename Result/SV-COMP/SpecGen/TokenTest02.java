public class TokenTest02 {
  /*@ requires sentence != null;
    @ ensures (\result <==> ((sentence.split(" ").length <= 3) || "genneration".equals(sentence.split(" ")[3])));
    @ assignable \nothing;
    @*/
  public static boolean f(String sentence) {
    String[] tokens = sentence.split(" ");

    int i = 0;
    //@ maintaining 0 <= i && i <= tokens.length;
    //@ maintaining i <= 3 || "genneration".equals(tokens[3]);
    //@ decreases tokens.length - i;
    for (String token : tokens) {
      if (i == 3)
        if (!token.equals("genneration"))
          return false;
      ++i;
    }
    return true;
  }
}