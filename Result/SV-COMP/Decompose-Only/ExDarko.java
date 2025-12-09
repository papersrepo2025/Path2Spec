class MyInteger {
  /*@ spec_public @*/ int value;

  //@ assignable \everything;
  //@ ensures this.value == \old(value);
  public MyInteger(int value) {
    this.value = value;
  }

  //@ requires obj == this;
  //@ ensures \result == true;
  //@ also
  //@ requires obj != this && !(obj instanceof MyInteger);
  //@ ensures \result == false;
  //@ also
  //@ requires obj != this && (obj instanceof MyInteger);
  //@ ensures \result == (this.value == ((MyInteger)obj).value);
  @Override
  public /*@ pure @*/ boolean equals(/*@ nullable @*/ Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof MyInteger)) {
      return false;
    }
    MyInteger other = (MyInteger) obj;
    return this.value == other.value;
  }
}