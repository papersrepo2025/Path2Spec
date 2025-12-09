class SQLException extends Exception {}

class Statement {
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ ensures true;
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
  //@ requires s != null && !s.contains("<bad/>");
  public void executeQuery(String s) {
    if (s.contains("<bad/>")) {
      assert false;
    }
  }
}

class Connection {
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ also
  //@ requires s != null;
  //@ requires !s.contains("<bad/>");
  //@ ensures true;
  //@ also
  //@ requires s != null && !s.contains("<bad/>");
  public void prepareStatement(String s) throws SQLException {
    if (s.contains("<bad/>")) {
      assert false;
    }
  }
  //@ ensures true;
  public void close() throws SQLException {}
  //@ ensures \result != null;
  //@ also
  //@ ensures \result != null;
  //@ ensures \fresh(\result);
  public Statement createStatement() {
    return new Statement();
  }
}

public class DriverManager {
  //@ ensures \result != null;
  //@ also
  //@ ensures \result != null;
  //@ ensures \fresh(\result);
  public static Connection getConnection(String connectionString) {
    return new Connection();
  }
}
