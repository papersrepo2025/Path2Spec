import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpSession {

  /*@ spec_public @*/ private final Map<String, Object> map = new HashMap<>();
  //@ invariant map != null;

  //@ ensures \result == map.get(name);
  //@ assignable \nothing;
  public Object getAttribute(String name) {
    return map.get(name);
  }

  //@ ensures \result != null;
  //@ assignable \nothing;
  public Enumeration<String> getAttributeNames() {
    return Collections.enumeration(map.keySet());
  }

  //@ ensures map.get(name) == value;
  //@ ensures map.size() == \old(map.size()) + (\old(map.containsKey(name)) ? 0 : 1);
  public void setAttribute(String name, Object value) {
    this.map.put(name, value);
  }
}