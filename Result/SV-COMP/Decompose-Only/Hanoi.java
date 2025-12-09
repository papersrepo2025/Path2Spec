class Hanoi {

    /*@ spec_public @*/ static int counter = 0;

    /*@
      @ public normal_behavior
      @   requires n == 1;
      @   ensures \result == 1;
      @ also
      @ public normal_behavior
      @   requires 2 <= n && n <= 30;
      @   ensures \result == (1 << n) - 1;
      @*/
    static /*@ pure @*/ int hanoi(int n) {
        if (n == 1) {
          return 1;
        }
        return 2 * (hanoi(n - 1)) + 1;
    }
    
    /*@
      @ public normal_behavior
      @   requires n == 0;
      @   assignable \nothing;
      @   ensures counter == \old(counter);
      @ also
      @ public normal_behavior
      @   requires 1 <= n && n <= 30;
      @   requires counter <= Integer.MAX_VALUE - ((1 << n) - 1);
      @   assignable counter;
      @   ensures counter == \old(counter) + ((1 << n) - 1);
      @*/
    static void applyHanoi(int n, int from, int to, int via) {
        if (n == 0) {
          return;
        }
        // increment the number of steps
        //@ assert counter + 1 <= Integer.MAX_VALUE;
        counter++;
        applyHanoi(n - 1, from, via, to);
        applyHanoi(n - 1, via, to, from);
    }
}