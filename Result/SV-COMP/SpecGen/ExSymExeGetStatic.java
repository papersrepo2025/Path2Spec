class Main {

  /*@ public normal_behavior
    @   ensures true;
    @*/
  public static void main(String[] args) {
    SNode sn = new SNode();
    SNode sn2 = sn.swap();
  }
}

class SNode {
  /*@ spec_public @*/ int elem;
  /*@ spec_public @*/ SNode next;
  /*@ spec_public @*/ static SNode head; // = new SNode(); //change is here

  /*@ public normal_behavior
    @   ensures \result == this;
    @*/
  SNode swap() {
    if (head != null) {
      assert false;
      System.out.println("head is not null");
    } else {
      System.out.println("head is null");
    }
    return this;
  }
}