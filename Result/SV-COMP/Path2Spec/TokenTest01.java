public class TokenTest01 {
  //@ requires true;
  //@ ensures \result == false;
  public static boolean f() {
    String sentence = "automatic test case generation";
    String[] tokens = sentence.split(" ");
    //@ assume tokens != null;
    //@ assume tokens.length == 4;
    if(tokens.length == 4) return false;

    int i = 0;
    //@ loop_invariant 0 <= i && i <= tokens.length;
    //@ decreases tokens.length - i;
    for (String token : tokens) {
      System.out.println(token);
      if (i == 0) {
        if (!token.equals("automatic"))
          return false;
      }
      else if (i == 1) {
        if (!token.equals("test"))
          return false;
      }
      else if (i == 2) {
        if (!token.equals("case"))
          return false;
      }
      else if (i == 3) {
        if (!token.equals("generation"))
          return false;
      }
      ++i;
    }
    return true;
  }
}
