enum enum1 {
  VAL1,
  VAL2,
  VAL3,
  VAL4,
  VAL5;

  static {
    for (enum1 e : enum1.values()) {
      System.out.println(e);
    }
  }
}

class Main {
  //@ requires true;
  //@ ensures true;
  public static void main(String[] args) {
    enum1 e = enum1.VAL5;
    assert (e == enum1.VAL5);
  }
}
