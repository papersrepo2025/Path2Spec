
public class NotCommonMultiple {

    /*@ public normal_behavior
      @     requires a != 0 && m % a != 0;
      @     assignable \nothing;
      @     ensures \result;
      @ also
      @ public normal_behavior
      @     requires a != 0 && m % a == 0 && b != 0;
      @     assignable \nothing;
      @     ensures \result <==> (m % b != 0);
      @ also
      @ public exceptional_behavior
      @     requires a == 0;
      @     assignable \nothing;
      @     signals_only ArithmeticException;
      @ also
      @ public exceptional_behavior
      @     requires a != 0 && m % a == 0 && b == 0;
      @     assignable \nothing;
      @     signals_only ArithmeticException;
      @*/
    public /*@ pure @*/ boolean NotCommonMultiple (int a, int b, int m) {
        return m % a != 0 || m % b != 0;
    }
}