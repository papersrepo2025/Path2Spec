public class SortedListInsert {

  private static class List {
    public /*@ spec_public @*/ int x;
    public /*@ spec_public @*/ List next;

    private static final /*@ spec_public @*/ int SENTINEL = Integer.MAX_VALUE;

    /*@ public normal_behavior
      @ ensures this.x == x && this.next == next;
      @*/
    private List(int x, List next) {
      this.x = x;
      this.next = next;
    }

    /*@ public normal_behavior
      @ ensures this.x == SENTINEL && this.next == null;
      @*/
    public List() {
      this(SENTINEL, null);
    }

    /*@ public normal_behavior
      @ ensures data <= \old(this.x) ==> (this.x == data && this.next != null && this.next.x == \old(this.x));
 
      @*/
    public void insert(int data) {
      if (data > this.x) {
        next.insert(data);
      } else {
        next = new List(x, next);
        x = data;
      }
    }
  }
  
}
