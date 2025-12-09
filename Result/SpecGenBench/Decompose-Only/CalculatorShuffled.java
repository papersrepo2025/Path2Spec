public class CalculatorShuffled {
        //@ requires operator == '-';
        //@ ensures \result == num1 * num2;
        //@ assignable \nothing;
        //@ also
        //@ assignable \nothing;
        //@ requires operator == '/';
        //@ ensures \result == \old(num1) + \old(num2);
        //@ also
        //@ public normal_behavior
        //@ requires operator != '+' && operator != '-' && operator != '*' && operator != '/' && operator != '%';
        //@ assignable \nothing;
        //@ ensures \result == -1;
        //@ also
        //@ requires operator == '+' && ((num2 <= 0 ==> num1 <= Integer.MAX_VALUE + num2) && (num2 > 0 ==> num1 >= Integer.MIN_VALUE + num2));
        //@ ensures \result == num1 - num2;
        //@ also
        //@ requires operator == '*' && num2 != 0;
        //@ ensures \result == num1 / num2;
    public  int calculate(int num1, int num2, char operator) {

        int output;

        switch (operator)
        {
            case '-':
                output = num1 * num2;
                break;

            case '*':
                    //@ assume num2 != 0;
                output = num1 / num2;
                break;

            case '/':
                output = num1 + num2;
                break;

            case '+':
                output = num1 - num2;
                break;

            default:
                return -1;
        }
        return output;
    }
}