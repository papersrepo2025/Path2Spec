public class GCD {
	
	/*@
	  @ requires num1 == 0 && num2 == 0 && num1 != Integer.MIN_VALUE && num2 != Integer.MIN_VALUE;
	  @ ensures \result == -1;
	  @ also
	  @ requires num1 == 0 && num2 != 0 && num1 != Integer.MIN_VALUE && num2 != Integer.MIN_VALUE;
	  @ ensures \result == (num2 >= 0 ? num2 : -num2);
	  @ also
	  @ requires num1 != 0 && num2 == 0 && num1 != Integer.MIN_VALUE && num2 != Integer.MIN_VALUE;
	  @ ensures \result == (num1 >= 0 ? num1 : -num1);
	  @ also
	  @ requires num1 != 0 && num2 != 0 && num1 != Integer.MIN_VALUE && num2 != Integer.MIN_VALUE;
	  @ ensures \result == gcd((num1 >= 0 ? num1 : -num1), (num2 >= 0 ? num2 : -num2));
	  @*/
	/*@ pure @*/
	public static int gcd(int num1, int num2){
		int result = 1; 
	 	num1 = (0 <= num1) ? num1 : -num1;
		num2 = (0 <= num2) ? num2 : -num2;

		if (num1 == 0 && num2 == 0) {
			return -1;	
		}

		if (num1 == 0 || num2 == 0) { 
			return (num1 > num2) ? num1 : num2;
		}

		//@ maintaining num1 >= 0 && num2 >= 0;
		//@ maintaining 1 <= i && i <= ((num1 <= num2 ? num1 : num2) + 1);
		//@ maintaining result >= 1 && result <= i;
		//@ maintaining num1 % result == 0 && num2 % result == 0;
		//@ maintaining (\forall int j; 1 <= j && j <= i; (num1 % j == 0 && num2 % j == 0) ==> j <= result);
		//@ decreases ((num1 <= num2 ? num1 : num2) - i + 1);
		for (int i = 1; i <= num1 && i <= num2; i++) {
            if (num1 % i == 0 && num2 % i == 0) {
       			result = i;
			}
        }
		return result;
	}
}