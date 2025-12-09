public class StaticCharMethods02 {
  /*@ 
    @ requires arg != null;
    @ ensures (\result == -1) <==> (arg.length() < 1);
    @ ensures arg.length() >= 1 ==> (\result == 0 || \result == 1);
    @ ensures arg.length() >= 1 ==> ((\result == 1) <==> (Character.toUpperCase(arg.charAt(0)) != Character.toLowerCase(arg.charAt(0))));
    @ assignable \nothing;
    @*/
  public static int f(String arg) {
    if (arg.length() < 1) return -1;
    char c = arg.charAt(0);
    return (Character.toUpperCase(c) != Character.toLowerCase(c)) ? 1 : 0;
  }
}