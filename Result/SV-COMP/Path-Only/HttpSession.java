import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpSession {

  //@ spec_public; private final Map<String, Object> map = new HashMap<>();

  //@ invariant map != null;

  //@ requires true;
//@ ensures \result == map.get(name);
  public //@ nullable; Object getAttribute(String name) {
    return map.get(name);
  }

  //@ requires true;
  public //@ nullable; Enumeration<String> getAttributeNames() {
    return Collections.enumeration(map.keySet());
  }

  //@ requires true;
  public void setAttribute(String name, Object value) {
    this.map.put(name, value);
  }
}