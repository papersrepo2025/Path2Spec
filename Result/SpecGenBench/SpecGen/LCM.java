public class LCM {
    
    //@ ensures (\old(num1) == 0 || \old(num2) == 0) ==> \result == -1;
    //@ ensures \result != -1 ==> (
    //@     \result >= (\abs(\old(num1)) > \abs(\old(num2)) ? \abs(\old(num1)) : \abs(\old(num2))) &&
    //@     \result % \abs(\old(num1)) == 0 && \result % \abs(\old(num2)) == 0 &&
    //@     (\forall int k; ((\abs(\old(num1)) > \abs(\old(num2)) ? \abs(\old(num1)) : \abs(\old(num2))) <= k && k < \result);
    //@         (k % \abs(\old(num1)) != 0 || k % \abs(\old(num2)) != 0))
    //@ );
    //@ ensures (\result == -1 && \old(num1) != 0 && \old(num2) != 0) ==> 
    //@     (\forall int k; ((\abs(\old(num1)) > \abs(\old(num2)) ? \abs(\old(num1)) : \abs(\old(num2))) <= k && k <= Integer.MAX_VALUE);
    //@         (k % \abs(\old(num1)) != 0 || k % \abs(\old(num2)) != 0));
    public static /*@ pure @*/ int lcm(int num1, int num2) 
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
            //@ maintaining result >= (num1 > num2 ? num1 : num2) && result <= Integer.MAX_VALUE;
            //@ maintaining (\forall int k; ((num1 > num2 ? num1 : num2) <= k && k < result); (k % num1 != 0 || k % num2 != 0));
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