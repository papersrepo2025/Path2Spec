public class Divs32 {

  /*@ spec_public @*/ static final int MAX_INT = 2147483647;
  /*@ spec_public @*/ static final int MIN_INT = -2147483648;

  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures (denominator == 0) ==> (\result == (numerator >= 0 ? MAX_INT : MIN_INT));
 
    @*/
  public static int divS32(int numerator, int denominator) {
    int quotient;
    int tempAbsQuotient;
    boolean quotientNeedsNegation = false;
    if (denominator == 0) {
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

      tempAbsQuotient = (int) (numerator >= 0 ? numerator : -numerator) / calcDenominator;
      quotient = quotientNeedsNegation ? -(int) tempAbsQuotient : (int) tempAbsQuotient;
    }

    return quotient;
  }
}
