public class TokenTest01 {
  //@ ensures \result == false;
  public static boolean f() {
    String sentence = "automatic test case generation";
    String[] tokens = sentence.split(" ");
    if(tokens.length == 4) return false;

    int i = 0;
    //@ maintaining 0 <= i && i <= tokens.length;
    //@ maintaining (\forall int j; 0 <= j && j < i;
    //@              (j == 0 ==> tokens[j].equals("automatic")) &&
    //@              (j == 1 ==> tokens[j].equals("test")) &&
    //@              (j == 2 ==> tokens[j].equals("case")) &&
    //@              (j == 3 ==> tokens[j].equals("generation")));
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