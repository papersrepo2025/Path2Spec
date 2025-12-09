class lookupswitch1 {
  //@ requires i == 1 || i == 10 || i == 100 || i == 1000 || i == 10000 || i == 100000;
  //@ ensures \result;
  //@ also
  //@ requires !(i == 1 || i == 10 || i == 100 || i == 1000 || i == 10000 || i == 100000);
  //@ ensures \result;
  public static boolean f(int i) {
    int j;

    switch (i) {
      case 1:
        //@ assert i == 1;
        j = 2;
        break;
      case 10:
        //@ assert i == 10;
        j = 11;
        break;
      case 100:
        //@ assert i == 100;
        j = 101;
        break;
      case 1000:
        //@ assert i == 1000;
        j = 1001;
        break;
      case 10000:
        //@ assert i == 10000;
        j = 10001;
        break;
      case 100000:
        //@ assert i == 100000;
        j = 100001;
        break;
      default:
        //@ assert i != 1 && i != 10 && i != 100 && i != 1000 && i != 10000 && i != 100000;
        j = -1;
    }

    if (i == 1 || i == 10 || i == 100 || i == 1000 || i == 10000 || i == 100000) return j == i + 1;
    else return j == -1;
  }
}