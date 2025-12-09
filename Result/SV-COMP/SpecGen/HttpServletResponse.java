import java.io.IOException;
import java.io.PrintWriter;

public class HttpServletResponse {

  /*@
    @ public normal_behavior
    @   requires string == null || !string.contains("<bad/>");
    @   ensures true;
    @*/
  public void sendRedirect(String string) throws IOException {
    checkNoSymbolic(string);
  }

  /*@
    @ public normal_behavior
    @   ensures \result != null;
    @*/
  public PrintWriter getWriter() throws IOException {
    return new PrintWriter(System.out) {
      /*@
        @ public normal_behavior
        @   requires x == null || !x.contains("<bad/>");
        @   ensures true;
        @*/
      @Override
      public void println(String x) {
        checkNoSymbolic(x);
      }

      /*@
        @ public normal_behavior
        @   requires !String.valueOf(x).contains("<bad/>");
        @   ensures true;
        @*/
      @Override
      public void println(Object x) {
        checkNoSymbolic(String.valueOf(x));
      }
    };
  }

  /*@
    @ public normal_behavior
    @   requires s == null || !s.contains("<bad/>");
    @   ensures true;
    @*/
  public void setContentType(String s) {
    checkNoSymbolic(s);
  }

  /*@
    @ public normal_behavior
    @   requires s == null || !s.contains("<bad/>");
    @   ensures true;
    @*/
  private void checkNoSymbolic(String s) {
    if (s != null && s.contains("<bad/>")) {
      assert false;
    }
  }
}