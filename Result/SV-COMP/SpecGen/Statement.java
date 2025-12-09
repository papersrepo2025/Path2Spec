class SQLException extends Exception {
}

public class Statement {

  //@ assignable \nothing;
  //@ requires s != null;
  //@ ensures true;
  public void execute(String s) throws SQLException {
    //@ assume !s.contains("<bad/>");
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }

  //@ assignable \nothing;
  //@ requires s != null;
  //@ ensures true;
  public void executeUpdate(String s, Object... o) throws SQLException {
    //@ assume !s.contains("<bad/>");
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }

  //@ assignable \nothing;
  //@ requires s != null;
  //@ ensures true;
  public void executeQuery(String s) {
    //@ assume !s.contains("<bad/>");
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }
}