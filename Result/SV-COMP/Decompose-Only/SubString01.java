public class SubString01 {

  /*@ public normal_behavior
    @ assignable \nothing;
    @ ensures true;
    @*/
  public static boolean f() {
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