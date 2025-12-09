
class PrimeCheck {
    //@ requires a >= 2;
//@ ensures \result == true;
    public boolean isPrime(int a) {
        int i = 2;
        int mid = a / 2;
        
        //@ maintaining 2 <= i && i <= mid + 1;
//@ decreases mid - i + 1;
        while (i <= mid) {
            //@ requires a % i != 0; // Implicit check to ensure no divisor is found
            if (a % i == 0)
                return false;
            i++;
        }
        return true;
    }
}
