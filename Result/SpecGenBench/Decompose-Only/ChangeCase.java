
public class ChangeCase {

  /*@
    @ requires c > 'z';
    @ ensures \result == c;
    @ also
    @ requires 'a' <= c && c <= 'z';
    @ ensures \result == (char)(c - 'a' + 'A');
    @ also
    @ requires 'Z' < c && c < 'a';
    @ ensures \result == c;
    @ also
    @ requires 'A' <= c && c <= 'Z';
    @ ensures \result == (char)(c - 'A' + 'a');
    @ also
    @ requires c < 'A';
    @ ensures \result == c;
    @*/
  public char changeCase(char c) {
    char result = ' ';    
    if (c > 'z') {
      result = c;
    } else if (c >= 'a') {
      result =  (char)(c - 'a' + 'A');
    } else if (c > 'Z') {
      result =  c;
    } else if (c >= 'A') {
      result =  (char)(c - 'A' + 'a');
    } else {
      result = c;
    }
    return result;
  }

}