class DigitRoot {
    /*@ 
      @ public normal_behavior
      @   requires num >= 0;
      @   assignable \nothing;
      @   ensures 0 <= \result && \result <= 9;
 
 
      @*/
    /*@ spec_public @*/ public int digitRoot(int num) {
        //@ maintaining num >= 0;
 
        while (num >= 10) {
            int sum = 0;
            //@ maintaining sum >= 0 && num >= 0;
            //@ decreases num;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }
}
