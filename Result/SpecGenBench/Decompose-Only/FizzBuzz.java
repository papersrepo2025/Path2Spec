

class FizzBuzz {
    //@ public normal_behavior
    //@   requires n % 3 == 0 && n % 5 == 0;
    //@   assignable \nothing;
    //@   ensures \result == 8;
    //@ also
    //@ public normal_behavior
    //@   requires n % 3 == 0 && n % 5 != 0;
    //@   assignable \nothing;
    //@   ensures \result == 3;
    //@ also
    //@ public normal_behavior
    //@   requires n % 3 != 0 && n % 5 == 0;
    //@   assignable \nothing;
    //@   ensures \result == 5;
    //@ also
    //@ public normal_behavior
    //@   requires n % 3 != 0 && n % 5 != 0;
    //@   assignable \nothing;
    //@   ensures \result == 0;
    public int fizzBuzz(int n) {
        //@ assert true;
        int res = 0;
        if (n % 3 == 0) {
            //@ assert res == 0;
            res += 3;
        }
        if (n % 5 == 0) {
            //@ assert res == 0 || res == 3;
            res += 5;
        }
        return res;
    }
}