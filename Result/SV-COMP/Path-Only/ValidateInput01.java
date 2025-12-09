public class ValidateInput01 {
  //@ requires firstName != null;
  //@ requires !firstName.matches("[A-Z][a-zA-Z]*");
  //@ ensures \result == false;
  //@ also
  //@ requires firstName != null;
  //@ ensures \result == firstName.matches("[A-Z][a-zA-Z]*");
  public static boolean validateFirstName(String firstName) {
    return firstName.matches("[A-Z][a-zA-Z]*");
  }
  //@ requires lastName != null;
  //@ ensures \result == lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
  //@ also
  //@ requires lastName != null;
  //@ ensures lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*") ==> \result;
  //@ also
  //@ requires lastName != null;
  //@ ensures \result == lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
  //@ ensures !lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*") ==> \result == false;
  public static boolean validateLastName(String lastName) {
    return lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
  }
  //@ requires address != null;
  //@ ensures \result == address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  //@ also
  //@ requires address != null;
  //@ requires address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  //@ ensures \result == true;
  public static boolean validateAddress(String address) {
    return address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  }
  //@ requires city != null;
  //@ ensures \result == city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  //@ also
  //@ requires city != null && city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  //@ ensures \result == true;
  //@ also
  //@ requires city != null;
  //@ requires !city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  //@ ensures \result == false;
  public static boolean validateCity(String city) {
    return city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  }
  //@ requires state != null;
  //@ ensures \result == state.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  public static boolean validateState(String state) {
    return state.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  }
  //@ requires zip != null && !zip.matches("\\d{5}");
  //@ ensures \result == false;
  //@ also
  //@ requires zip != null;
  //@ ensures \result == zip.matches("\\d{5}");
  public static boolean validateZip(String zip) {
    return zip.matches("\\d{5}");
  }
  //@ requires phone != null;
  //@ requires phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
  //@ ensures \result == true;
  //@ also
  //@ requires phone != null;
  //@ ensures (!phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}")) ==> \result == false;
  //@ ensures \result == phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
  //@ also
  //@ requires phone != null;
  //@ ensures \result == phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
  public static boolean validatePhone(String phone) {
    return phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
  }
}
