class MyPower {
    //@ requires n >= 0;
    //@ assignable \nothing;
 
    public static int power(int x, int n) {
        int res = 1;
        //@ maintaining 0 <= i && i <= n;
 
        //@ maintaining x == \old(x) && n == \old(n);
        //@ decreases n - i;
        for(int i = 0; i < n; i++)
	    res = res * x;
        return res;
    }
}
