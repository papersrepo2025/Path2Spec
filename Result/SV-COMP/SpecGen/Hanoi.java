class Hanoi {

    /*@ spec_public @*/ static int counter = 0;

    /*@
      public normal_behavior
      requires 1 <= n && n <= 31;
      ensures \result == spec_hanoi(n);
      ensures \result >= 1 && \result <= Integer.MAX_VALUE;
    @*/
    /*@ spec_public @*/ static int hanoi(int n) {
        if (n == 1) {
          return 1;
        }
        return 2 * (hanoi(n - 1)) + 1;
    }
    
    /*@
      public normal_behavior
      requires n >= 0;
      assignable counter;
      ensures counter == \old(counter) + spec_hanoi(n);
    @*/
    /*@ spec_public @*/ static void applyHanoi(int n, int from, int to, int via) {
        if (n == 0) {
          return;
        }
        // increment the number of steps
        counter++;
        applyHanoi(n - 1, from, via, to);
        applyHanoi(n - 1, via, to, from);
    }

    /*@
      public model pure static int spec_hanoi(int n) {
          if (n == 0) {
              return 0;
          } else {
              return 2 * spec_hanoi(n - 1) + 1;
          }
      }
    @*/
}