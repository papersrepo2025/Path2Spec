public class startswith {
  /*@
    @ requires request != null;
    @ assignable \nothing;
    @ ensures \result;
    @*/
  public boolean doPost(String request)  {
    String param = request;

    String[] argsEnv = {param};

    if (param.equals("") && argsEnv[0].equals("GOTCHA")) {
      return false;
    }
    return true;
  }
}