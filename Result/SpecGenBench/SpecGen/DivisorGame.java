public class DivisorGame {
    /*@ public normal_behavior
      @   requires true;
      @   assignable \nothing;
      @   ensures \result <==> (n % 2 == 0);
      @*/
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}