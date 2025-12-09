static class Node {
  /*@ spec_public @*/ int elem;
  /*@ spec_public @*/ Node next;
  /*@ spec_public @*/ static Node static_next;

  /*@
    @ // The method returns a non-null node (either 'this' or the old 'next').
    @ ensures \result != null;
    @
    @ // Three new nodes are allocated and linked via the static field.
    @ ensures Node.static_next != null
    @      && Node.static_next.next != null
    @      && Node.static_next.next.next != null;
    @
    @ // 'elem' of this node is not modified by the operation.
    @ ensures this.elem == \old(this.elem);
    @
    @ // The result is either this or the old next.
    @ ensures \result == this || \result == \old(next);
    @
    @ // If a swap occurs (next != null and elem > next.elem), then:
    @ // - the returned node is the old 'next'
    @ // - 'this.next' becomes the old 'next.next'
    @ // - the returned node's 'next' points to 'this'
    @ // - the returned node's 'elem' equals the old next's elem
    @ ensures (\old(next) != null && \old(elem) > \old(next.elem))
    @      ==> (\result == \old(next)
    @           && this.next == \old(next.next)
    @           && \result.next == this
    @           && \result.elem == \old(next.elem));
    @
    @ // If no swap occurs, the method returns 'this' and 'next' is unchanged.
    @ ensures !(\old(next) != null && \old(elem) > \old(next.elem))
    @      ==> (\result == this
    @           && this.next == \old(next));
    @*/
  Node swapNode() {

    static_next = new Node();
    static_next.next = new Node();
    static_next.next.next = new Node();
    if (next != null)
      if (elem > next.elem) {
        assert next != null;
        Node t = next;
        next = t.next;
        t.next = this;
        return t;
      }
    return this;
  }
}