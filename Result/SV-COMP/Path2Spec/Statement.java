class SQLException extends Exception {
}

public class Statement {
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ ensures true;
  //@ also
  //@ requires s == null;
  //@ also
  //@ requires s != null && !s.contains("<bad/>");
  public void execute(String s) throws SQLException {
    if (s.contains("<bad/>")) {
      assert false;
    }
  }
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ ensures true;
  //@ also
  //@ requires s == null;
  //@ also
  //@ requires s != null && !s.contains("<bad/>");
  public void executeUpdate(String s, Object... o) throws SQLException {
    if (s.contains("<bad/>")) {
      assert false;
    }
  }
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ ensures true;
  //@ also
  //@ requires s == null;
  //@ also
  //@ requires s != null && !s.contains("<bad/>");
  public void executeQuery(String s) {
    if (s.contains("<bad/>")) {
      assert false;
    }
  }
}
