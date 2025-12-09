public class Calculator {
        //@ requires operator != '+' && operator != '-' && operator != '*' && operator != '/' && operator != '%';
        //@ assignable \nothing;
        //@ ensures \result == -1;
        //@ also
        //@ requires operator == '%' && num2 != 0;
        //@ assignable \nothing;
        //@ ensures \result == num1 % num2;
        //@ also
        //@ requires operator == '*';
        //@ assignable \nothing;
        //@ ensures \result == \old(num1) * \old(num2);
        //@ also
        //@ requires operator == '+';
        //@ assignable \nothing;
        //@ ensures \result == num1 + num2;
    public  int calculate(int num1, int num2, char operator) {

        int output;

        switch (operator)
        {
            case '+':
                output = num1 + num2;
                break;

            case '-':
                output = num1 - num2;
                break;

            case '*':
                    //@ assert true;
                output = num1 * num2;
                break;

            case '/':
                    //@ assume num2 != 0;
                output = num1 / num2;
                break;

            case '%':
                output = num1 % num2;
                break;

            default:
                return -1;
        }
        return output;
    }
}