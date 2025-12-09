public class Trie {
  /*@ spec_public @*/ private static final int R = 256; // extended ASCII

  /*@ spec_public @*/ private Node root; // root of trie
  /*@ spec_public @*/ private int N = 0; // number of keys in trie

  // R-way trie node
  /*@ spec_public @*/ private static class Node {
    /*@ spec_public @*/ private int val = -1;
    /*@ spec_public @*/ private Node[] next = new Node[R];
  }

  /*@ spec_public @*/ private static class CharArray {
    /*@ spec_public @*/ private int length = 0;
    /*@ spec_public @*/ public char[] array;
    /*@ spec_public @*/ private static final int DEFAULT_LENGTH = 42;

    //@ ensures this.length == DEFAULT_LENGTH && array != null && array.length == this.length;
    public CharArray() {
      this(DEFAULT_LENGTH);
    }

    //@ ensures this.length == length && array != null && array.length == this.length;
    public CharArray(int length) {
      this.length = length;
      array = new char[length];
    }

    //@ ensures \result == this.length;
    public int length() {
      return length;
    }

    //@ ensures i < length ==> array[i] == c;
    public void set(int i, char c) {
      if (i < length) array[i] = c;
      else throw new ArrayIndexOutOfBoundsException("Error while setting char!");
    }

    //@ ensures i < length ==> \result == array[i];
    public char get(int i) {
      if (i < length) return array[i];
      else throw new ArrayIndexOutOfBoundsException("Error while getting char!");
    }

    //@ ensures \result != null && \result.length() == (end - start);
    //@ ensures \result != null ==> (\forall int i; 0 <= i && i < (end - start); \result.get(i) == this.get(i));
    public CharArray substring(int start, int end) {
      int subLength = end - start;
      CharArray substr = new CharArray(subLength);
      //@ maintaining 0 <= i && i <= subLength;
      //@ maintaining substr.length() == subLength;
      //@ maintaining (\forall int k; 0 <= k && k < i; substr.get(k) == this.get(k));
      //@ decreases subLength - i;
      for (int i = 0; i < subLength; i++) substr.set(i, this.get(i));
      return substr;
    }
  }

  //@ ensures (get2(root, key, 0) == null ==> \result == -1) && (get2(root, key, 0) != null ==> \result == get2(root, key, 0).val);
  public int get(CharArray key) {
    Node x = get2(root, key, 0);
    if (x == null) return -1;
    return x.val;
  }

  //@ ensures \result == (get(key) != -1);
  public boolean contains(CharArray key) {
    return get(key) != -1;
  }

  /*@ spec_public @*/
  //@ ensures (x == null ==> \result == null)
  //@      && (x != null ==> ((d == key.length()) ==> \result == x)
  //@                     && ((d < key.length()) ==> \result == get2(x.next[key.get(d)], key, d + 1)));
  private Node get2(Node x, CharArray key, int d) {
    if (x == null) return null;
    if (d == key.length()) return x;
    char c = key.get(d);
    return get2(x.next[c], key, d + 1);
  }

  //@ ensures (val == -1 ==> !contains(key)) && (val != -1 ==> contains(key));
  public void put(CharArray key, int val) {
    if (val == -1) delete(key);
    else root = put2(root, key, val, 0);
  }

  /*@ spec_public @*/
  //@ ensures \result != null;
  //@ ensures d <= key.length() ==> (get2(\result, key, d) != null && get2(\result, key, d).val == val);
  private Node put2(Node x, CharArray key, int val, int d) {
    if (x == null) x = new Node();
    if (d == key.length()) {
      if (x.val == -1) N++;
      x.val = val;
      return x;
    }

    char c = key.get(d);
    x.next[c] = put2(x.next[c], key, val, d + 1);
    return x;
  }

  //@ ensures \result == N;
  public int size() {
    return N;
  }

  //@ ensures \result == (size() == 0);
  public boolean isEmpty() {
    return size() == 0;
  }

  //@ ensures (\result == null) <==> (longestPrefixOf(root, query, 0, -1) == -1);
  //@ ensures \result != null ==> (\result.length() == longestPrefixOf(root, query, 0, -1)
  //@                           && (\forall int i; 0 <= i && i < \result.length(); \result.get(i) == query.get(i)));
  public CharArray longestPrefixOf(CharArray query) {
    int length = longestPrefixOf(root, query, 0, -1);
    if (length == -1) return null;
    else return query.substring(0, length);
  }

  /*@ spec_public @*/
  //@ ensures (x == null ==> \result == length)
  //@      && (x != null ==> ((d == query.length()) ==> \result == ((x.val != -1) ? d : length))
  //@                      && ((d < query.length()) ==> \result == longestPrefixOf(x.next[query.get(d)], query, d + 1, ((x.val != -1) ? d : length))));
  private int longestPrefixOf(Node x, CharArray query, int d, int length) {
    if (x == null) return length;
    if (x.val != -1) length = d;
    if (d == query.length()) return length;
    char c = query.get(d);
    return longestPrefixOf(x.next[c], query, d + 1, length);
  }

  //@ ensures !contains(key);
  public void delete(CharArray key) {
    root = delete(root, key, 0);
  }

  /*@ spec_public @*/
  //@ ensures (x == null ==> \result == null);
  //@ ensures d <= key.length() ==> (get2(\result, key, d) == null || get2(\result, key, d).val == -1);
  private Node delete(Node x, CharArray key, int d) {
    if (x == null) return null;
    if (d == key.length()) {
      if (x.val != -1) N--;
      x.val = -1;
    } else {
      char c = key.get(d);
      x.next[c] = delete(x.next[c], key, d + 1);
    }

    // remove subtrie rooted at x if it is completely empty
    if (x.val != -1) return x;
    //@ maintaining 0 <= c && c <= R;
    //@ maintaining (\forall int j; 0 <= j && j < c; x.next[j] == null);
    //@ decreases R - c;
    for (int c = 0; c < R; c++) if (x.next[c] != null) return x;
    return null;
  }

}