/*
 * Origin of the benchmark:
 *     license: 4-clause BSD (see /java/jbmc-regression/LICENSE)
 *     repo: https://github.com/diffblue/cbmc.git
 *     branch: develop
 *     directory: regression/cbmc-java/exceptions15
 * The benchmark was taken from the repo: 24 January 2018
 */
class InetAddress {}

class InetSocketAddress {}

public class exceptions15 {
  /*@ public normal_behavior
    @   requires address == null;
    @   assignable \nothing;
    @   ensures \result != null && \result.equals("Foo");
    @ also
    @ public normal_behavior
    @   requires address != null;
    @   assignable \nothing;
    @   ensures \result != null && \result.equals("Foo");
    @*/
  public static String lookupPtrRecord(InetAddress address) {
    return "Foo";
  }

  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures \result == address;
    @*/
  public static InetAddress reverse(InetAddress address) {
    return address;
  }

  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures true;
    @*/
  public static void main(String[] args) {
    try {
      InetAddress address = new InetAddress();
      //@ assert reverse(address) == address;
      //@ assert lookupPtrRecord(reverse(address)).equals("Foo");
      String domainName = lookupPtrRecord(reverse(address));
    } catch (Exception e) {
      assert false;
    }
  }
}