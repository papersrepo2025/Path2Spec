public class Fibonacci {

	//@ requires n < 0;
	//@ ensures \result == -1;
	//@ also
	//@ requires n == 0;
	//@ ensures \result == 0;
	//@ also
	//@ requires n == 1;
	//@ ensures \result == 1;
	//@ also
	//@ requires n >= 2;
	//@ requires n <= 46;
	//@ ensures \result == (((1 + sqrt(5)) / 2)^n - ((1 - sqrt(5)) / 2)^n) / sqrt(5);
	public static int fibCompute(int n) {
		if(n < 0) return -1;
		else if(n == 0) return 0;

		int[] fib = new int[n + 1];
		fib[0] = 0;
		fib[1] = 1;
		int index = 2;
		//@ maintaining fib != null;
		//@ maintaining 2 <= index && index <= fib.length;
		//@ maintaining fib.length == n + 1;
		//@ maintaining fib[0] == 0;
		//@ maintaining (n >= 1) ==> fib[1] == 1;
		//@ maintaining (\forall int k; 2 <= k && k < index; fib[k] == fib[k - 2] + fib[k - 1]);
		//@ maintaining (\forall int k; 0 <= k && k < index; fib[k] >= 0);
		//@ decreases fib.length - index;
		while (index < fib.length) {
			fib[index] = fib[index - 2] + fib[index - 1];
			index++;         
		}

		return fib[n];
	}

}