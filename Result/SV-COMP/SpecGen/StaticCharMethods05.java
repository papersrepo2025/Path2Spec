public class StaticCharMethods05 {

  /*@
    public normal_behavior
      requires arg != null;
      assignable \everything;
      accessible \everything;
      ensures true;
  @*/
  public static boolean f(String arg) {
    int n = arg.length();
    int i = 0;

    // parse first int: radix
    while (i < n && Character.isWhitespace(arg.charAt(i))) i++;
    int sign = 1;
    if (i < n && (arg.charAt(i) == '+' || arg.charAt(i) == '-')) {
      if (arg.charAt(i) == '-') sign = -1;
      i++;
    }
    int radix = 0;
    boolean any = false;
    while (i < n && Character.isDigit(arg.charAt(i))) {
      any = true;
      radix = radix * 10 + (arg.charAt(i) - '0');
      i++;
    }
    radix *= sign;

    // parse second int: choice
    while (i < n && Character.isWhitespace(arg.charAt(i))) i++;
    sign = 1;
    if (i < n && (arg.charAt(i) == '+' || arg.charAt(i) == '-')) {
      if (arg.charAt(i) == '-') sign = -1;
      i++;
    }
    int choice = 0;
    any = false;
    while (i < n && Character.isDigit(arg.charAt(i))) {
      any = true;
      choice = choice * 10 + (arg.charAt(i) - '0');
      i++;
    }
    choice *= sign;

    // normalize to 0..2
    choice = ((choice % 3) + 3) % 3;

    if (choice == 1) {
      // parse third int: digit
      while (i < n && Character.isWhitespace(arg.charAt(i))) i++;
      sign = 1;
      if (i < n && (arg.charAt(i) == '+' || arg.charAt(i) == '-')) {
        if (arg.charAt(i) == '-') sign = -1;
        i++;
      }
      int digit = 0;
      any = false;
      while (i < n && Character.isDigit(arg.charAt(i))) {
        any = true;
        digit = digit * 10 + (arg.charAt(i) - '0');
        i++;
      }
      digit *= sign;

      char tmp = Character.forDigit(digit, radix);
      if (tmp != 't') return false;

    } else if (choice == 2) {
      // parse third token: single character
      while (i < n && Character.isWhitespace(arg.charAt(i))) i++;
      char character = (i < n) ? arg.charAt(i) : '\0';
      int d = Character.digit(character, radix);
      // Use 'd' if needed; kept to mirror the original intent.
    }

    return true;
  }
}