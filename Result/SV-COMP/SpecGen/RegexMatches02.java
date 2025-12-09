import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches02 {
  //@ requires string1 != null;
  //@ ensures true;
  //@ signals (NullPointerException e) string1 == null;
  public /*@ spec_public @*/ static boolean f(String string1) {
    Pattern expression = Pattern.compile("W.*\\d[0-35-9]-\\d\\d-\\d\\d");
    Matcher matcher = expression.matcher(string1);

    //@ maintaining string1 != null && expression != null && matcher != null;
    while (matcher.find()) {
      System.out.println(matcher.group());
      String tmp = matcher.group();
      if (!tmp.equals("WWWWW's Birthday is 12-17-77"))
        return false;
    }
    return true;
  }
}