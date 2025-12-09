import java.util.*;

public class HttpServletRequest {

  /*@ spec_public @*/ private Cookie cookie = null;
  /*@ spec_public @*/ private String tainted = null;

  // Minimal local Cookie class to avoid dependency on javax.servlet.http.Cookie
  public static class Cookie {
    /*@ spec_public @*/ private String name;
    /*@ spec_public @*/ private String value;

    //@ ensures this.name == name && this.value == value;
    public Cookie(String name, String value) {
      this.name = name;
      this.value = value;
    }

    //@ ensures \result == this.name;
    public /*@ pure @*/ String getName() {
      return name;
    }

    //@ ensures \result == this.value;
    public /*@ pure @*/ String getValue() {
      return value;
    }
  }

  //@ ensures this.tainted == value;
  //@ ensures this.cookie != null;
  public void setTaintedValue(String value) {
    tainted = value;
    this.cookie = new Cookie(tainted, tainted);
  }

  //@ ensures \result != null && \result.length == 1 && \result[0] == this.tainted;
  public String[] getParameterValues(String name) {
    return new String[] { tainted };
  }

  //@ ensures \result == this.tainted;
  public String getAuthType() {
    return tainted;
  }

  //@ ensures \result != null && \result.length == 1 && \result[0] == this.cookie;
  public Cookie[] getCookies() {
    return new Cookie[] { cookie };
  }

  //@ ensures \result == this.tainted;
  public String getHeader(String string) {
    return tainted;
  }

  //@ ensures \result != null;
  public Enumeration getHeaders(String string) {
    return Collections.enumeration(Collections.singleton(tainted));
  }

  //@ ensures \result != null;
  public Enumeration getHeaderNames() {
    return Collections.enumeration(Collections.singleton(tainted));
  }

  //@ ensures \result == this.tainted;
  public String getQueryString() {
    return tainted;
  }

  //@ ensures \result == this.tainted;
  public String getRemoteUser() {
    return tainted;
  }

  //@ ensures \result != null;
  public StringBuffer getRequestURL() {
    return new StringBuffer(tainted);
  }

  //@ ensures \result == this.tainted;
  public String getParameter(String string) {
    return tainted;
  }

  //@ ensures \result != null;
  public Enumeration getParameterNames() {
    return Collections.enumeration(Collections.singleton("name"));
  }

  //@ ensures \result != null;
  public Map getParameterMap() {
    Map map = new HashMap();
    map.put("name", tainted);
    return map;
  }

  //@ ensures \result == this.tainted;
  public String getProtocol() {
    return tainted;
  }

  //@ ensures \result == this.tainted;
  public String getScheme() {
    return tainted;
  }
}