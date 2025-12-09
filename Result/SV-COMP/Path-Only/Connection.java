class SQLException extends Exception {
}

class Statement {
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s != null && !s.contains("<bad/>");
  //@ ensures true;
  //@ also
  //@ requires s == null;
  //@ also
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ ensures true;
  public void execute(String s) throws SQLException {
    if (s.contains("<bad/>")) {
      assert false;
    }
  }
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s != null && !s.contains("<bad/>");
  //@ ensures true;
  //@ also
  //@ requires s != null && o != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s == null;
  //@ also
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ ensures true;
  public void executeUpdate(String s, Object... o) throws SQLException {
    if (s.contains("<bad/>")) {
      assert false;
    }
  }
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s != null && !s.contains("<bad/>");
  //@ ensures true;
  //@ also
  //@ requires s == null;
  //@ also
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ ensures true;
  public void executeQuery(String s) {
    if (s.contains("<bad/>")) {
      assert false;
    }
  }
}

public class Connection {
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s != null && !s.contains("<bad/>");
  //@ ensures true;
  //@ also
  //@ requires s == null;
  //@ also
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ ensures true;
  public void prepareStatement(String s) throws SQLException {
    if (s.contains("<bad/>")) {
      assert false;
    }
  }
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ ensures true;
  public void close() throws SQLException {}
  //@ requires true;
  //@ ensures \result != null;
  //@ also
  //@ ensures \result != null;
  public Statement createStatement() {
    return new Statement();
  }
}
