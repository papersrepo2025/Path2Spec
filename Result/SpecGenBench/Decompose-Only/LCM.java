public class LCM {
    
    //@ requires num1 == 0 || num2 == 0;
    //@ ensures \result == -1;
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    //@ also
    //@ requires num1 != 0 && num2 != 0;
    //@ requires num1 != Integer.MIN_VALUE && num2 != Integer.MIN_VALUE;
    //@ ensures \result == -1 || (\result % (\old(num1) < 0 ? -\old(num1) : \old(num1)) == 0 && \result % (\old(num2) < 0 ? -\old(num2) : \old(num2)) == 0);
    //@ ensures \result != -1 ==> (\result >= (\old(num1) < 0 ? -\old(num1) : \old(num1)) && \result >= (\old(num2) < 0 ? -\old(num2) : \old(num2)));
    //@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
    public static int lcm(int num1, int num2) 
    {
        if (num1 == 0 || num2 == 0) {
            return -1;
        }	
        if (num1 < 0)
            num1 = -num1;
        if (num2 < 0)
            num2 = -num2;

            int result = (num1 > num2) ? num1 : num2;

            //@ maintaining num1 > 0 && num2 > 0;
            //@ maintaining result >= num1 && result >= num2;
            //@ maintaining Integer.MIN_VALUE <= result && result <= Integer.MAX_VALUE;
            //@ maintaining 0 <= Integer.MAX_VALUE - result;
            //@ decreases Integer.MAX_VALUE - result;
            while (result < Integer.MAX_VALUE)
            {
                if (result % num1 == 0 && result % num2 == 0)
                {
                    break;
                }
                result++;
            }

        if (result % num1 == 0 && result % num2 == 0) {
            return result;
        }
        return -1;
    }

}