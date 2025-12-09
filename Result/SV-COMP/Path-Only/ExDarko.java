class MyInteger {
  int value;
  public MyInteger(int value) {
    this.value = value;
  }

  //@ requires obj == this;
  //@ ensures \result == true;
  @Override
  public boolean equals(Object obj) {
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
