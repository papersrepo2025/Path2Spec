public class startswith {
  //@ requires request != null && request.equals("") && request.equals("GOTCHA");
  //@ ensures \result == false;
  //@ also
  //@ requires request != null && !(request.equals("") && request.equals("GOTCHA"));
  //@ ensures \result == true;
  public boolean doPost(String request)  {
    //@ assert request != null;
    String param = request;

    //@ assert param == request;
    String[] argsEnv = {param};

    if (param.equals("") && argsEnv[0].equals("GOTCHA")) {
      return false;
    }
    return true;
  }
}