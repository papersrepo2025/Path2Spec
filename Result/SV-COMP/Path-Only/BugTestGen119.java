import java.util.Random;

public class BugTestGen119 {
  //@ requires true;
//@ ensures \result == true;
  public static boolean main(String[] args) {
    boolean booleanValue = new Random().nextBoolean();

    String tmp = String.valueOf(booleanValue);
    if (booleanValue) return tmp.equals("true");
    else return tmp.equals("false");
  }
}