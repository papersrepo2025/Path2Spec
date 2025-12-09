import java.util.Random;

public final class Verifier {
  //@ ensures condition;
  public static void assume(boolean condition) {
    if (!condition) {
      Runtime.getRuntime().halt(1);
    }
  }

  //@ ensures \result == true || \result == false;
  public static boolean nondetBoolean() {
    return new Random().nextBoolean();
  }

  //@ ensures Byte.MIN_VALUE <= \result && \result <= Byte.MAX_VALUE;
  public static byte nondetByte() {
    return (byte) (new Random().nextInt());
  }

  //@ ensures Character.MIN_VALUE <= \result && \result <= Character.MAX_VALUE;
  public static char nondetChar() {
    return (char) (new Random().nextInt());
  }

  //@ ensures Short.MIN_VALUE <= \result && \result <= Short.MAX_VALUE;
  public static short nondetShort() {
    return (short) (new Random().nextInt());
  }

  //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
  public static int nondetInt() {
    return new Random().nextInt();
  }

  //@ ensures Long.MIN_VALUE <= \result && \result <= Long.MAX_VALUE;
  public static long nondetLong() {
    return new Random().nextLong();
  }

  //@ ensures 0.0f <= \result && \result < 1.0f;
  public static float nondetFloat() {
    return new Random().nextFloat();
  }

  //@ ensures 0.0 <= \result && \result < 1.0;
  public static double nondetDouble() {
    return new Random().nextDouble();
  }

  //@ ensures \result != null;
  public static String nondetString() {
    Random random = new Random();
    int size = random.nextInt();
    assume(size >= 0);
    byte[] bytes = new byte[size];
    random.nextBytes(bytes);
    return new String(bytes);
  }
}