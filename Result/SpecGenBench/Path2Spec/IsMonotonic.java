
class IsMonotonic {
    //@ requires nums != null;
//@ requires nums.length >= 2;
//@ requires (\forall int i; 0 <= i && i < nums.length - 1; nums[i] <= nums[i + 1]);
//@ ensures \result == true;
    public boolean isMonotonic(int[] nums) {
        boolean inc = true, dec = true;
        int n = nums.length;
        //@ loop_invariant 0 <= i && i <= n - 1;
//@ loop_invariant inc == true;
//@ loop_invariant (\forall int j; 0 <= j && j < i; nums[j] <= nums[j + 1]);
//@ decreases n - 1 - i;
        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                inc = false;
            }
            if (nums[i] < nums[i + 1]) {
                dec = false;
            }
        }
        return inc || dec;
    }
}
