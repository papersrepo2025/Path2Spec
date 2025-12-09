class tableswitch1 {
  //@ public normal_behavior
  //@ requires -1 <= i && i <= 11;
  //@ assignable \nothing;
  //@ ensures \result;
  //@ also
  //@ public normal_behavior
  //@ requires i < -1 || i > 11;
  //@ assignable \nothing;
  //@ ensures \result;
  public static /*@ pure @*/ boolean f(int i) {
    int j;

    switch (i) {
      case -1:
        //@ assert i == -1;
        j = 0;
        break;
      case 0:
        //@ assert i == 0;
        j = 1;
        break;
      case 1:
        //@ assert i == 1;
        j = 2;
        break;
      case 2:
        //@ assert i == 2;
        j = 3;
        break;
      case 3:
        //@ assert i == 3;
        j = 4;
        break;
      case 4:
        //@ assert i == 4;
        j = 5;
        break;
      case 5:
        //@ assert i == 5;
        j = 6;
        break;
      case 6:
        //@ assert i == 6;
        j = 7;
        break;
      case 7:
        //@ assert i == 7;
        j = 8;
        break;
      case 8:
        //@ assert i == 8;
        j = 9;
        break;
      case 9:
        //@ assert i == 9;
        j = 10;
        break;
      case 10:
        //@ assert i == 10;
        j = 11;
        break;
      case 11:
        //@ assert i == 11;
        j = 12;
        break;
      default:
        //@ assert i < -1 || i > 11;
        j = 1000;
    }

    if (i >= -1 && i <= 11) return j == i + 1;
    else return j == 1000;
  }
}