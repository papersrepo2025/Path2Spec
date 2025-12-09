
public class Calculator {
    //@ requires operator == '+' && Integer.MIN_VALUE <= num1 + num2 && num1 + num2 <= Integer.MAX_VALUE;
    //@ ensures \result == num1 + num2;
    //@ also
    //@ requires operator == '-' && (num1 >= Integer.MIN_VALUE + num2) && (num1 <= Integer.MAX_VALUE + num2);
    //@ ensures \result == num1 - num2;
    //@ also
    //@ requires operator == '*' ==> Integer.MIN_VALUE <= num1 * num2 && num1 * num2 <= Integer.MAX_VALUE;
    //@ requires operator != '/' || num2 != 0;
    //@ requires operator != '%' || num2 != 0;
    //@ ensures operator == '*' ==> \result == num1 * num2;
    //@ ensures operator == '+' || operator == '-' || operator == '*' || (operator == '/' && num2 != 0) || (operator == '%' && num2 != 0) ==> (\result == num1 + num2 || \result == num1 - num2 || \result == num1 * num2 || \result == num1 / num2 || \result == num1 % num2);
    //@ also
    //@ requires operator == '/' && num2 != 0;
    //@ ensures \result == num1 / num2;
    //@ also
    //@ requires operator == '%' && num2 != 0;
    //@ ensures \result == num1 % num2;
    //@ also
    //@ requires operator != '+' && operator != '-' && operator != '*' && operator != '/' && operator != '%';
    //@ ensures \result == -1;
    public int calculate(int num1, int num2, char operator) {

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
            	output = num1 * num2;
                break;

            case '/':
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
