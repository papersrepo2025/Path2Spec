class PrimeCheck {
   
	/*@ ensures \result == (\forall int d; 2 <= d && d <= a/2; a % d != 0); @*/
	public /*@ spec_public @*/ boolean isPrime(int a) {
		int i = 2;
		int mid = a/2;

		/*@
 
		  @ maintaining (\forall int d; 2 <= d && d < i; a % d != 0);
		  @ decreases mid - i + 1;
		  @*/
		while (i <= mid) {
			if (a%i == 0)
				return false;
			i++;
		}
		return true;
	}
}
