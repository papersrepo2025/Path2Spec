public class ValidateInput01 {
  //@ requires firstName != null;
  //@ ensures \result == firstName.matches("[A-Z][a-zA-Z]*");
  //@ ensures firstName == \old(firstName);
  public /*@ pure @*/ static boolean validateFirstName(String firstName) {
    return firstName.matches("[A-Z][a-zA-Z]*");
  }

  //@ requires lastName != null;
  //@ ensures \result == lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
  //@ ensures lastName == \old(lastName);
  public /*@ pure @*/ static boolean validateLastName(String lastName) {
    return lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
  }

  //@ requires address != null;
  //@ ensures \result == address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  //@ ensures address == \old(address);
  public /*@ pure @*/ static boolean validateAddress(String address) {
    return address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  }

  //@ requires city != null;
  //@ ensures \result == city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  //@ ensures city == \old(city);
  public /*@ pure @*/ static boolean validateCity(String city) {
    return city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  }

  //@ requires state != null;
  //@ ensures \result == state.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  //@ ensures state == \old(state);
  public /*@ pure @*/ static boolean validateState(String state) {
    return state.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  }

  //@ requires zip != null;
  //@ ensures \result == zip.matches("\\d{5}");
  //@ ensures zip == \old(zip);
  public /*@ pure @*/ static boolean validateZip(String zip) {
    return zip.matches("\\d{5}");
  }

  //@ requires phone != null;
  //@ ensures \result == phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
  //@ ensures phone == \old(phone);
  public /*@ pure @*/ static boolean validatePhone(String phone) {
    return phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
  }
}