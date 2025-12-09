public class Divs32 {

  static final int MAX_INT = 2147483647;
  static final int MIN_INT = -2147483648;

      //@ requires denominator == 0;
      //@ assignable \nothing;
      //@ ensures \result == (\old(numerator) >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE);
      //@ also
      //@ requires denominator != 0;
      //@ requires (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0);
      //@ ensures \result == -(((numerator >= 0 ? numerator : -numerator) / (denominator >= 0 ? denominator : -denominator)));
      //@ also
      //@ requires denominator != 0;
      //@ requires !((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0));
      //@ ensures \result == ((numerator >= 0 ? numerator : -numerator) / (denominator >= 0 ? denominator : -denominator));
  public static int divS32(int numerator, int denominator) {
    int quotient;
    int tempAbsQuotient;
    boolean quotientNeedsNegation = false;
    if (denominator == 0) {
          //@ assert denominator == 0;
      quotient = numerator >= 0 ? MAX_INT : MIN_INT;

      /* Divide by zero handler */
    } else {
      // quotientNeedsNegation = ((numerator < 0) != (denominator < 0));

      if ((numerator < 0) && (denominator > 0)) quotientNeedsNegation = true;
      else if ((numerator > 0) && (denominator < 0)) quotientNeedsNegation = true;
      else quotientNeedsNegation = false;

      int calcDenominator;

      /* replacing this computation
      tempAbsQuotient = (int) (numerator >= 0 ? numerator : -numerator) /
              (denominator >= 0 ? denominator : -denominator);*/

      if (denominator >= 0) calcDenominator = denominator;
      else calcDenominator = -denominator;

          //@ assert calcDenominator != 0;
      tempAbsQuotient = (int) (numerator >= 0 ? numerator : -numerator) / calcDenominator;
      quotient = quotientNeedsNegation ? -(int) tempAbsQuotient : (int) tempAbsQuotient;
    }

    return quotient;
  }
}