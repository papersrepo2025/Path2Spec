class MyInteger {
  /*@ spec_public @*/ int value;

  /*@ ensures this.value == value; @*/
  public MyInteger(int value) {
    this.value = value;
  }

  /*@ 
    @ ensures \result <==> (obj == this) || (obj instanceof MyInteger && ((MyInteger)obj).value == this.value);
    @ assignable \nothing;
    @*/
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