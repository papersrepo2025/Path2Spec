public class StringStartEnd02 {

  //@ ensures (args != null && args.length < 4) ==> \result == false;
  //@ ensures (args != null && args.length >= 4 && (\forall int t; 0 <= t && t < 4; args[t] != null))
  //@          ==> (\result <==> ((\sum int t; 0 <= t && t < 4; (args[t].startsWith("te") ? 1 : 0)) == 1));
  public static /*@ spec_public @*/ /*@ pure @*/ boolean f(String[] args) {
    if (args.length < 4)
      return false;
    int i = 0;

    //@ maintaining args != null && args.length >= 4;
    //@ maintaining 0 <= j && j <= 4;
    //@ maintaining 0 <= i && i <= j;
    //@ maintaining i == (\sum int k; 0 <= k && k < j; ((args[k] != null && args[k].startsWith("te")) ? 1 : 0));
    //@ decreases 4 - j;
    for (int j = 0; j < 4; j++) {
      if (args[j].startsWith("te"))
        ++i;
    }

    return i == 1;
  }
  
}