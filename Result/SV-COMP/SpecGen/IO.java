import java.io.*;
import java.sql.*;
import java.util.regex.*;

public class IO {

  /* fill in these parameters if you want to be able to actually connect
   * to a database
   */
  /*@ spec_public @*/ private static final String dbUrl = "";
  /*@ spec_public @*/ private static final String dbUsername = "";
  /*@ spec_public @*/ private static final String dbPassword = "";

  //@ ensures true;
  public static void writeString(String str) {
    System.out.print(str);
  }

  //@ ensures true;
  public static void writeLine(String line) {
    System.out.println(line);
  }

  //@ ensures true;
  public static void writeLine(int intNumber) {
    System.out.println(intNumber);
  }

  //@ ensures true;
  public static void writeLine(long longNumber) {
    System.out.println(longNumber);
  }

  //@ ensures true;
  public static void writeLine(double doubleNumber) {
    System.out.println(doubleNumber);
  }

  //@ ensures true;
  public static void writeLine(float floatNumber) {
    System.out.println(floatNumber);
  }

  //@ ensures true;
  public static void writeLine(short shortNumber) {
    System.out.println(shortNumber);
  }

  //@ ensures true;
  public static void writeLine(byte byteHex) {
    System.out.println(byteHex);
  }

  /* use this method to get a database connection for use in SQL
   * Injection and other test cases that use a database.
   */
  //@ ensures true;
  public static Connection getDBConnection() throws SQLException {
    return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
  }

  /* The variables below are declared "final", so a tool doing whole
  program analysis should be able to identify that reads of these
  will always return their initialized values. */
  /*@ spec_public @*/ public static final boolean STATIC_FINAL_TRUE = true;
  /*@ spec_public @*/ public static final boolean STATIC_FINAL_FALSE = false;
  /*@ spec_public @*/ public static final int STATIC_FINAL_FIVE = 5;

  /* The variables below are not defined as "final", but are never
  assigned any other value, so a tool doing whole program analysis
  should be able to identify that reads of these will always return
  their initialized values. */
  /*@ spec_public @*/ public static boolean staticTrue = true;
  /*@ spec_public @*/ public static boolean staticFalse = false;
  /*@ spec_public @*/ public static int staticFive = 5;

  //@ ensures \result == true;
  public static boolean staticReturnsTrue() {
    return true;
  }

  //@ ensures \result == false;
  public static boolean staticReturnsFalse() {
    return false;
  }

  //@ ensures \result == true || \result == false;
  public static boolean staticReturnsTrueOrFalse() {
    return (new java.util.Random()).nextBoolean();
  }

  /* Turns array of bytes into string.  Taken from:
  http://java.sun.com/developer/technicalArticles/Security/AES/AES_v1.html */
  //@ requires byteBuffer != null;
  //@ ensures \result != null && \result.length() == 2 * byteBuffer.length;
  public static String toHex(byte byteBuffer[]) {
    StringBuffer strBuffer = new StringBuffer(byteBuffer.length * 2);
    int i;

    //@ maintaining 0 <= i && i <= byteBuffer.length;
    //@ maintaining strBuffer != null && strBuffer.length() == 2 * i;
    //@ decreases byteBuffer.length - i;
    for (i = 0; i < byteBuffer.length; i++) {
      if (((int) byteBuffer[i] & 0xff) < 0x10) {
        strBuffer.append("0");
      }

      strBuffer.append(Long.toString((int) byteBuffer[i] & 0xff, 16));
    }

    return strBuffer.toString();
  }
}