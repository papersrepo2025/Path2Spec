import java.util.Random;

public class aastore_aaload1 {
  static class A {
    int value = 0;
  }

  //@ requires true;
  //@ ensures true;
  public static void main(String[] args) {
    int size = new Random().nextInt();
    if (size < 0) return;
    //@ assert size >= 0;
    A[] array = new A[size];

    //@ maintaining array != null && array.length == size;
    //@ maintaining 0 <= i && i <= size;
    //@ maintaining (\forall int k; 0 <= k && k < i; array[k] != null);
    //@ decreasing size - i;
    for (int i = 0; i < size; i++) {
      //@ assert 0 <= i && i < array.length;
      array[i] = new A();
      //@ assert array[i] != null;
    }

    //@ maintaining array != null && array.length == size;
    //@ maintaining 0 <= i && i <= size;
    //@ maintaining (\forall int k; 0 <= k && k < size; array[k] != null);
    //@ decreasing size - i;
    for (int i = 0; i < size; i++) {
      //@ assert 0 <= i && i < array.length;
      assert array[i] != null;
    }
    //@ assert (\forall int k; 0 <= k && k < size; array[k] != null);
  }
}