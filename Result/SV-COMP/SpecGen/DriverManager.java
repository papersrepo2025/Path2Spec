class SQLException extends Exception {}

class Statement {

  /*@ assignable \nothing;
    @ ensures true;
    @*/
  public void execute(String s) throws SQLException {
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }

  /*@ assignable \nothing;
    @ ensures true;
    @*/
  public void executeUpdate(String s, Object... o) throws SQLException {
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }

  /*@ assignable \nothing;
    @ ensures true;
    @*/
  public void executeQuery(String s) {
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }
}

class Connection {

  /*@ assignable \nothing;
    @ ensures true;
    @*/
  public void prepareStatement(String s) throws SQLException {
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }

  /*@ assignable \nothing;
    @ ensures true;
    @*/
  public void close() throws SQLException {}

  /*@ assignable \nothing;
    @ ensures \result != null;
    @*/
  public Statement createStatement() {
    return new Statement();
  }
}

public class DriverManager {
  /*@ assignable \nothing;
    @ ensures \result != null;
    @*/
  public static Connection getConnection(String connectionString) {
    return new Connection();
  }
}