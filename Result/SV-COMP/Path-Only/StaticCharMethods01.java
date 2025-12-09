/*
 * Origin of the benchmark:
 *     license: 4-clause BSD (see /java/jbmc-regression/LICENSE)
 *     repo: https://github.com/diffblue/cbmc.git
 *     branch: develop
 *     directory: regression/jbmc-strings/StaticCharMethods01
 * The benchmark was taken from the repo: 24 January 2018
 */
public class StaticCharMethods01 {
  //@ requires true;
  //@ ensures \result == true;
  public static boolean f() {
    char c = 0;
    //@ assume Character.isDefined(c);
    //@ assume !Character.isDigit(c);
    //@ assume !Character.isJavaIdentifierStart(c);
    //@ assume Character.isJavaIdentifierPart(c);
    //@ assume !Character.isLetter(c);
    //@ assume !Character.isLetterOrDigit(c);
    //@ assume !Character.isLowerCase(c);
    //@ assume !Character.isUpperCase(c);
    //@ assume Character.toUpperCase(c) == Character.toLowerCase(c);
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