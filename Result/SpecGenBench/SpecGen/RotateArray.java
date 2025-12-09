class RotateArray {
    //@ requires nums != null && nums.length > 0 && k >= 0;
    //@ assignable nums[*];
    //@ ensures nums.length == \old(nums.length);
    //@ ensures (\forall int i; 0 <= i && i < nums.length; nums[(i + (k % nums.length)) % nums.length] == \old(nums[i]));
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining n == nums.length && n > 0 && k >= 0;
        //@ maintaining (\forall int j; 0 <= j && j < i; newArr[(j + (k % n)) % n] == \old(nums[j]));
        //@ decreases n - i;
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }
}