public class SubString01 {

  //@ assignable \nothing;
  //@ ensures \result;
  //@ ensures \result ==> ("attachfilesbydragginganddroppingthem".substring(20).equals("ganddroppingthem") && "attachfilesbydragginganddroppingthem".substring(6,10).equals("file"));
  /*@ spec_public @*/ public static boolean f() {
    String letters = "attachfilesbydragginganddroppingthem";

    String tmp = letters.substring(20);
    if (!tmp.equals("ganddroppingthem"))
      return false;

    tmp = letters.substring(6, 10);
    if (!tmp.equals("file"))
      return false;
    return true;
  }
  
}