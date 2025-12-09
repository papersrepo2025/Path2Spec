import java.util.Scanner;

public class StaticCharMethods05 {
  //@ requires arg != null;
  //@ also
  //@ requires arg != null;
  //@ ensures \result == true;
  public static boolean f(String arg) {
    Scanner scanner = new Scanner(arg);

    int radix = scanner.nextInt();

    int choice = scanner.nextInt() % 3;
    //@ assume choice < 0 || choice >= 3;
    if(!(choice >= 0 && choice < 3)) return false;

    switch (choice) {
      case 1: // convert digit to character
        System.out.println("Enter a digit:");
        int digit = scanner.nextInt();
        System.out.printf("Convert digit to character: %s\n", Character.forDigit(digit, radix));
        char tmp = Character.forDigit(digit, radix);
        if (tmp != 't')
          return false;
        break;

      case 2: // convert character to digit
        System.out.println("Enter a character:");
        char character = scanner.next().charAt(0);
        System.out.printf("Convert character to digit: %s\n", Character.digit(character, radix));
        break;
    }
    return true;
  }
}
