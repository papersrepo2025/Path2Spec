class CompareArray {

    /*@
      @ signals (NullPointerException e) a == null || b == null;
      @ also
      @ requires a != null && b != null && a.length != b.length;
      @ assignable \nothing;
      @ ensures \result == false;
      @ also
      @ requires a != null && b != null && a.length == b.length && (\forall int i; 0 <= i && i < a.length; a[i] == b[i]);
      @ assignable \nothing;
      @ ensures \result == true;
      @ also
      @ requires a != null && b != null && a.length == b.length && (\exists int i; 0 <= i && i < a.length; a[i] != b[i]);
      @ assignable \nothing;
      @ ensures \result == false;
      @*/
    public static boolean arrcmp(int[] a, int[] b) {
        if(a.length != b.length) {
	    return false;
        }

        //@ maintaining 0 <= i && i <= a.length;
        //@ maintaining a.length == b.length;
        //@ maintaining (\forall int j; 0 <= j && j < i; a[j] == b[j]);
        //@ decreases a.length - i;
        for(int i = 0; i < a.length; i++) {
            //@ assume i != 0;
            if(a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }
}