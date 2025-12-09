public class Cookie {

  /*@ spec_public @*/ private String name;
  /*@ spec_public @*/ private String value;

  //@ ensures this.name == name && this.value == value;
  public Cookie(String name, String value) {
    this.name = name;
    this.value = value;
  }

  //@ ensures \result == this.name;
  public /*@ pure @*/ String getName() {
    return name;
  }

  //@ ensures \result == this.value;
  public /*@ pure @*/ String getValue() {
    return value;
  }
}