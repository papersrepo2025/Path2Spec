public class ValidateInput01 {

  /*@
    @ requires firstName != null && firstName.matches("[A-Z][a-zA-Z]*");
    @ ensures \result == true;
    @ also
    @ requires firstName != null && !firstName.matches("[A-Z][a-zA-Z]*");
    @ ensures \result == false;
    @*/
  public static /*@ pure @*/ boolean validateFirstName(String firstName) {
    return firstName.matches("[A-Z][a-zA-Z]*");
  }

  /*@
    @ requires lastName != null && lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
    @ ensures \result == true;
    @ also
    @ requires lastName != null && !lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
    @ ensures \result == false;
    @*/
  public static /*@ pure @*/ boolean validateLastName(String lastName) {
    return lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
  }

  /*@
    @ requires address != null && address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    @ ensures \result == true;
    @ also
    @ requires address != null && !address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    @ ensures \result == false;
    @*/
  public static /*@ pure @*/ boolean validateAddress(String address) {
    return address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  }

  /*@
    @ requires city != null && city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    @ ensures \result == true;
    @ also
    @ requires city != null && !city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    @ ensures \result == false;
    @*/
  public static /*@ pure @*/ boolean validateCity(String city) {
    return city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  }

  /*@
    @ requires state != null && state.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    @ ensures \result == true;
    @ also
    @ requires state != null && !state.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    @ ensures \result == false;
    @*/
  public static /*@ pure @*/ boolean validateState(String state) {
    return state.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
  }

  /*@
    @ requires zip != null && zip.matches("\\d{5}");
    @ ensures \result == true;
    @ also
    @ requires zip != null && !zip.matches("\\d{5}");
    @ ensures \result == false;
    @*/
  public static /*@ pure @*/ boolean validateZip(String zip) {
    return zip.matches("\\d{5}");
  }

  /*@
    @ requires phone != null && phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
    @ ensures \result == true;
    @ also
    @ requires phone != null && !phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
    @ ensures \result == false;
    @*/
  public static /*@ pure @*/ boolean validatePhone(String phone) {
    return phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
  }
}