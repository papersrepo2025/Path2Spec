import java.io.BufferedReader;
import java.io.StringReader;

public class BufferedReaderReadLine {

  //@ requires br != null;
  //@ signals (Exception e) true;
  public static String check(BufferedReader br) throws Exception {
    String s = br.readLine();
    return s;
  }

  //@ requires arg == null;
  //@ ensures true;
  //@ signals (Exception e) false;
  public static void main(String arg) {
    String thisLine = null;
    int numLines = 0;

    try {
      BufferedReader br = new BufferedReader(new StringReader(arg));
      String line = check(br);
      //@ maintaining true;
      //@ decreases 0;
      while ((thisLine = check(br)) != null) {
        System.out.println(thisLine);
        numLines += 1;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
    assert numLines > 0;
  }
}