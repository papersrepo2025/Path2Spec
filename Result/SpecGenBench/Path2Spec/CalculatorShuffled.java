
public class CalculatorShuffled {
    //@ requires operator == '-' && Integer.MIN_VALUE <= num1 * num2 && num1 * num2 <= Integer.MAX_VALUE;
    //@ ensures \result == num1 * num2;
    //@ also
    //@ requires operator == '*' && num2 != 0;
    //@ ensures \result == num1 / num2;
    //@ also
    //@ requires operator == '/' && num2 != 0;
    //@ ensures \result == num1 + num2;
    //@ also
    //@ requires operator != '-' && operator != '*' && operator != '/' && operator != '+';
    //@ ensures \result == -1;
    public int calculate(int num1, int num2, char operator) {

        int output;

        switch (operator)
        {
            case '-':
                output = num1 * num2;
                break;

            case '*':
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
