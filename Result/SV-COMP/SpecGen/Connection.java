class SQLException extends Exception {
}

class Statement {

  /*@
    @ requires s != null;
    @ assignable \nothing;
    @ ensures true;
    @*/
  public void execute(String s) throws SQLException {
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }

  /*@
    @ requires s != null;
    @ assignable \nothing;
    @ ensures true;
    @*/
  public void executeUpdate(String s, Object... o) throws SQLException {
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }

  /*@
    @ requires s != null;
    @ assignable \nothing;
    @ ensures true;
    @*/
  public void executeQuery(String s) {
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }
}

public class Connection {

  /*@
    @ requires s != null;
    @ assignable \nothing;
    @ ensures true;
    @*/
  public void prepareStatement(String s) throws SQLException {
    if (s.contains("<bad/>")) {
      //@ assume false;
      assert false;
    }
  }

  /*@
    @ assignable \nothing;
    @ ensures true;
    @*/
  public void close() throws SQLException {}

  /*@
    @ assignable \nothing;
    @ ensures \result != null && \fresh(\result);
    @*/
  public Statement createStatement() {
    return new Statement();
  }
}