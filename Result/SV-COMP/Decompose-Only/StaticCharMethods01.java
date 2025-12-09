/*
 * Origin of the benchmark:
 *     license: 4-clause BSD (see /java/jbmc-regression/LICENSE)
 *     repo: https://github.com/diffblue/cbmc.git
 *     branch: develop
 *     directory: regression/jbmc-strings/StaticCharMethods01
 * The benchmark was taken from the repo: 24 January 2018
 */
public class StaticCharMethods01 {
  //@ requires Character.isDefined((char)0);
  //@ requires !Character.isDigit((char)0);
  //@ requires !Character.isJavaIdentifierStart((char)0);
  //@ requires Character.isJavaIdentifierPart((char)0);
  //@ requires !Character.isLetter((char)0);
  //@ requires !Character.isLetterOrDigit((char)0);
  //@ requires !Character.isLowerCase((char)0);
  //@ requires !Character.isUpperCase((char)0);
  //@ requires Character.toUpperCase((char)0) == Character.toLowerCase((char)0);
  //@ ensures \result == true;
  public static boolean f() {
    char c = 0;
    if(Character.isDefined(c) == false) return false;
    if(Character.isDigit(c) == true) return false;
    if(Character.isJavaIdentifierStart(c) == true) return false;
    if(Character.isJavaIdentifierPart(c) == false) return false;
    if(Character.isLetter(c) == true) return false;
    if(Character.isLetterOrDigit(c) == true) return false;
    if(Character.isLowerCase(c) == true) return false;
    if(Character.isUpperCase(c) == true) return false;
    if (Character.toUpperCase(c) != Character.toLowerCase(c)) return false;
    return true;
  }
}