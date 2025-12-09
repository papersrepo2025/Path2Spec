public class BinaryTree {
  /** Internal class representing a Node in the tree. */
  private class Node {
    /*@ spec_public @*/ int value;
    /*@ spec_public @*/ Node left;
    /*@ spec_public @*/ Node right;

    //@ ensures value == v && left == l && right == r;
    Node(int v, Node l, Node r) {
      value = v;
      left = l;
      right = r;
    }
  }

  /*@ spec_public @*/ private Node root = null;

  /** Inserts a value in to the tree. */
  //@ ensures root != null;
  //@ ensures this.search(v);
  //@ ensures (\forall int u; \old(this.search(u)) ==> this.search(u));
  public void insert(int v) {

    if (root == null) {
      root = new Node(v, null, null);
      return;
    }

    Node curr = root;
    //@ maintaining curr != null && root != null;
    while (true) {
      if (curr.value < v) {
        if (curr.right != null) {
          curr = curr.right;
        } else {
          curr.right = new Node(v, null, null);
          break;
        }
      } else if (curr.value > v) {
        if (curr.left != null) {
          curr = curr.left;
        } else {
          curr.left = new Node(v, null, null);
          break;
        }
      } else {
        break;
      }
    }
  }

  /** Searches for a value in the tree. */
  //@ assignable \nothing;
  //@ ensures (root == null) ==> (\result == false);
  //@ ensures \result ==> (root != null);
  public /*@ pure @*/ boolean search(int v) {
    Node curr = root;
    //@ maintaining true;
    while (curr != null) { // N branches
      if (curr.value == v) { // N-1 branches
        return true;
      } else if (curr.value < v) { // N-1 branches
        curr = curr.right;
      } else {
        curr = curr.left;
      }
    }
    return false;
  }
}