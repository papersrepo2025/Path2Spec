public class NegAbs {

    /*@
      @ // Checklist:
      @ // - Identify execution paths based on (num < 0) and (num >= 0).
      @ // - Specify preconditions and postconditions for each path using 'also'.
      @ // - Confirm absence of loops and divisions; thus no related annotations needed.
      @ // - Mark method as pure since it has no side effects.
      @ // - Ensure specifications cover all inputs and corresponding outputs.
      @*/

    /*@
      @ public normal_behavior
      @   requires num < 0;
      @   ensures \result == num;
      @ also
      @ public normal_behavior
      @   requires num >= 0;
      @   ensures \result == -num;
      @*/
	
	public /*@ pure @*/ int negAbs(int num) {
		if (num < 0)
			return num;
		else
			return -num;
	}

}