
public class GCD {
    //@ requires num2 == 0 && num1 > 0;
    //@ ensures \result == num1;
    //@ also
    //@ requires num1 == 0 && num2 > 0;
    //@ ensures \result == num2;
    //@ also
    //@ requires num1 == 0 && num2 == 0;
    //@ ensures \result == -1;
    public static int gcd(int num1, int num2) {
        int result = 1;
        num1 = (0 <= num1) ? num1 : -num1;
        num2 = (0 <= num2) ? num2 : -num2;

        if (num1 == 0 && num2 == 0) {
            return -1;
        }

        if (num1 == 0 || num2 == 0) {
            return (num1 > num2) ? num1 : num2;
        }

        //@ loop_invariant 1 <= i && i <= num1 && i <= num2;
        //@ decreases num1 + num2 - i;
        for (int i = 1; i <= num1 && i <= num2; i++) {
            if (num1 % i == 0 && num2 % i == 0) {
                result = i;
            }
        }
        return result;
    }
}
