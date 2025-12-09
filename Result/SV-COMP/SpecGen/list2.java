class LinkedListEntry {
  /*@ spec_public @*/ public LinkedListEntry Next;
  /*@ spec_public @*/ public int Value;
}

class LinkedList {
  /*@ spec_public @*/ public LinkedListEntry Head;

  //@ ensures \result >= 0;
  //@ ensures Head == \old(Head);
  public int size() {
    int count = 0;
    for (LinkedListEntry entry = Head; entry != null; entry = entry.Next) ++count;
    return count;
  }

  //@ ensures (index == 0) ==> (Head != null && Head.Value == e);
  //@ ensures (index > 0) ==> (Head == \old(Head));
  public void add(int index, int e) {
    LinkedListEntry newEntry = new LinkedListEntry();
    newEntry.Value = e;
    if (index == 0) {
      newEntry.Next = Head;
      Head = newEntry;
      return;
    }
    LinkedListEntry entry = Head;
    for (int i = 1; i < index; ++i) entry = entry.Next;
    newEntry.Next = entry.Next;
    entry.Next = newEntry;
  }

  //@ ensures (\old(Head) == null) ==> (Head != null && Head.Value == e);
 
  public void add(int e) {
    add(size(), e);
  }

 
  public void remove(int index) {
    if (index == 0) {
      Head = Head.Next;
      return;
    }
    LinkedListEntry entry = Head;
    for (int i = 1; i < index; ++i) entry = entry.Next;
    entry.Next = entry.Next.Next;
  }

  //@ ensures Head == \old(Head);
  public int get(int index) {
    LinkedListEntry entry = Head;
    for (int i = 0; i < index; ++i) entry = entry.Next;
    return entry.Value;
  }
}

class Utils_nondet {
  //@ ensures true;
  public static int nondet_int() {
    // No external verifier dependency; return an arbitrary int
    return 0;
  }
}

class Utils_synthesis {
  //@ ensures \result == (aggregated < e ? e : aggregated);
  public static int accumulator(int aggregated, int e) {
    if (aggregated < e) return e;
    return aggregated;
  }

  //@ ensures \result == true;
  public static boolean predicate(int lhs) {
    return true;
  }
}
