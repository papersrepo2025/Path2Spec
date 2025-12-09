import java.util.Random;

public class aastore_aaload1 {
  static class A {
    /*@ spec_public @*/ int value = 0;
  }

  /*@ public normal_behavior
    @   ensures true;
    @*/
  public static void main(String[] args) {
    int size = new Random().nextInt();
    if (size < 0) return;
    A[] array = new A[size];
    //@ assert size >= 0 && array != null && array.length == size;

    //@ maintaining 0 <= i && i <= size && array.length == size;
    //@ maintaining (\forall int k; 0 <= k && k < i; array[k] != null);
    //@ decreases size - i;
    for (int i = 0; i < size; i++) {
      array[i] = new A();
    }
    //@ assert (\forall int k; 0 <= k && k < size; array[k] != null);
    //@ maintaining 0 <= i && i <= size && array.length == size;
    //@ maintaining (\forall int k; 0 <= k && k < i; array[k] != null);
    //@ decreases size - i;
    for (int i = 0; i < size; i++) {
      assert array[i] != null;
    }
  }
}