public class Cookie {

  //@ spec_public; private String name;
  //@ spec_public; private String value;

  //@ requires true;
//@ ensures this.name == name && this.value == value;
  public Cookie(String name, String value) {
    this.name = name;
    this.value = value;
  }

  //@ ensures \result == this.name;
  public String getName() {
    return name;
  }

  //@ ensures \result == this.value;
  public String getValue() {
    return value;
  }
}