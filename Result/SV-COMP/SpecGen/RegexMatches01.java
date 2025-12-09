import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches01 {
  //@ ensures \result;
  public static boolean f() {
    Pattern expression = Pattern.compile("W.*\\d[0-35-9]-\\d\\d-\\d\\d");

    String string1 =
        "XXXX's Birthday is 05-12-75\n"
            + "YYYY's Birthday is 11-04-68\n"
            + "ZZZZ's Birthday is 04-28-73\n"
            + "WWWW's Birthday is 12-17-77";

    Matcher matcher = expression.matcher(string1);
    //@ ghost boolean okSoFar = true;

    //@ maintaining matcher != null;
    //@ maintaining okSoFar;
    while (matcher.find()) {
      System.out.println(matcher.group());
      String tmp = matcher.group();
      //@ set okSoFar = okSoFar && tmp.equals("WWWW's Birthday is 12-17-77");
      if (!tmp.equals("WWWW's Birthday is 12-17-77"))
        return false;
    }
    return true;
  }
}