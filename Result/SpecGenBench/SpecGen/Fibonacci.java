public class Fibonacci {

	//@ ensures (n < 0) ==> \result == -1;
	//@ ensures (n == 0) ==> \result == 0;
	//@ ensures (n > 0) ==> (\exists int[] f; f.length == n + 1 && f[0] == 0 && f[1] == 1 && (\forall int i; 2 <= i && i <= n; f[i] == f[i - 1] + f[i - 2]) && \result == f[n]);
	public static int fibCompute(int n) {
		if(n < 0) return -1;
		else if(n == 0) return 0;

		int[] fib = new int[n + 1];
		fib[0] = 0;
		fib[1] = 1;
		int index = 2;

		//@ maintaining 2 <= index && index <= fib.length;
		//@ maintaining fib.length == n + 1 && fib[0] == 0 && fib[1] == 1;
		//@ maintaining (\forall int i; 2 <= i && i < index; fib[i] == fib[i - 1] + fib[i - 2]);
		//@ decreases fib.length - index;
		while (index < fib.length) {
			fib[index] = fib[index - 2] + fib[index - 1];
			index++;         
		}

		return fib[n];
	}

}