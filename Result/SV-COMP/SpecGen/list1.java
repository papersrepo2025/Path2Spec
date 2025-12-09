class LinkedListEntry {
  /*@ spec_public @*/ 
  public LinkedListEntry Next;
  /*@ spec_public @*/ 
  public int Value;
}

class LinkedList {
  /*@ spec_public @*/ 
  public LinkedListEntry Head;

  //@ ensures \result >= 0;
  //@ ensures Head == null ==> \result == 0;
  public /*@ pure @*/ int size() {
    int count = 0;
    //@ maintaining count >= 0;
    for (LinkedListEntry entry = Head; entry != null; entry = entry.Next) ++count;
    return count;
  }

  //@ requires 0 <= index && index <= size();
 
 
 
  public void add(int index, int e) {
    LinkedListEntry newEntry = new LinkedListEntry();
    newEntry.Value = e;
    if (index == 0) {
      Head = newEntry;
      return;
    }
    LinkedListEntry entry = Head;
    //@ maintaining 1 <= i && i <= index;
    //@ maintaining entry != null;
    for (int i = 1; i < index; ++i) entry = entry.Next;
    entry.Next = newEntry;
  }

 
 
  public void add(int e) {
    add(size(), e);
  }

  //@ requires 1 <= index && index < size();
 
 
  public void remove(int index) {
    LinkedListEntry entry = Head;
    //@ maintaining 1 <= i && i <= index;
    //@ maintaining entry != null && entry.Next != null;
    for (int i = 1; i < index; ++i) entry = entry.Next;
    entry.Next = entry.Next.Next;
  }

  //@ requires 0 <= index && index < size();
  //@ ensures true;
  public /*@ pure @*/ int get(int index) {
    LinkedListEntry entry = Head;
    //@ maintaining 0 <= i && i <= index;
    //@ maintaining i < index ==> entry != null;
    for (int i = 0; i < index; ++i) entry = entry.Next;
    return entry.Value;
  }
}

class Utils_synthesis {
  //@ ensures (e % 2 == 0 && aggregated < e) ==> \result == e;
  //@ ensures !(e % 2 == 0 && aggregated < e) ==> \result == aggregated;
  //@ ensures \result == aggregated || \result == e;
  public static int accumulator(int aggregated, int e) {
    if (e % 2 == 0) if (aggregated < e) return e;
    return aggregated;
  }

  //@ ensures \result == true;
  public static boolean predicate(int lhs) {
    return true;
  }
}
