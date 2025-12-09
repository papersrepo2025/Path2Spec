public class startswith {
  //@ requires request != null;
  //@ requires "".equals(request) && "GOTCHA".equals(request);
  //@ ensures \result == false;
  //@ also
  //@ requires request != null && !(request.equals("") && request.equals("GOTCHA"));
  //@ ensures \result == true;
  public boolean doPost(String request)  {
    String param = request;

    String[] argsEnv = {param};

    if (param.equals("") && argsEnv[0].equals("GOTCHA")) {
      return false;
    }
    return true;
  }
}
