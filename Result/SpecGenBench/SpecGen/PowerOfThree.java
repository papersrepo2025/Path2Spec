class PowerOfThree {
    //@ ensures (n == 1) ==> \result;
    //@ ensures (n < 0) ==> !\result;  
    //@ ensures \result ==> (n >= 0);  
    public boolean isPowerOfThree(int n) {
        int temp = n;
        //@ maintaining temp != 0 ==> n % temp == 0;
        //@ decreases (temp > 0 ? temp : -temp);
        while (temp != 0 && temp % 3 == 0) {
            temp /= 3;
        }
        return temp == 1;
    }
}
