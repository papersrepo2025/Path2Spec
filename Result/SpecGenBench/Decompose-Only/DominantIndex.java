class DominantIndex {
    
    //@ requires nums != null;
    //@ requires nums.length == 0;
    //@ requires (\forall int k; 0 <= k && k < nums.length; Integer.MIN_VALUE/2 <= nums[k] && nums[k] <= Integer.MAX_VALUE/2);
    //@ ensures \result == 0;
    //@ also
    //@ requires nums != null;
    //@ requires nums.length > 0;
    //@ requires (\forall int k; 0 <= k && k < nums.length; Integer.MIN_VALUE/2 <= nums[k] && nums[k] <= Integer.MAX_VALUE/2);
    //@ requires (\exists int j; 0 <= j && j < nums.length && (\forall int t; 0 <= t && t < nums.length; nums[j] >= nums[t]) && (\forall int k; 0 <= k && k < j; nums[k] < nums[j]) && (\forall int i; 0 <= i && i < nums.length && i != j; 2*nums[i] <= nums[j]));
    //@ ensures (\exists int j; 0 <= j && j < nums.length && (\forall int t; 0 <= t && t < nums.length; nums[j] >= nums[t]) && (\forall int k; 0 <= k && k < j; nums[k] < nums[j]) && \result == j);
    //@ also
    //@ requires nums != null;
    //@ requires nums.length > 0;
    //@ requires (\forall int k; 0 <= k && k < nums.length; Integer.MIN_VALUE/2 <= nums[k] && nums[k] <= Integer.MAX_VALUE/2);
    //@ requires (\exists int j; 0 <= j && j < nums.length && (\forall int t; 0 <= t && t < nums.length; nums[j] >= nums[t]) && (\forall int k; 0 <= k && k < j; nums[k] < nums[j]) && (\exists int i; 0 <= i && i < nums.length && i != j; 2*nums[i] > nums[j]));
    //@ ensures \result == -1;
    public static int dominantIndex(int[] nums) {
        int biggest_index = 0;

        //@ maintaining 0 <= i && i <= nums.length;
        //@ maintaining nums.length == 0 || (0 <= biggest_index && biggest_index < nums.length);
        //@ maintaining (\forall int t; 0 <= t && t < i; nums[biggest_index] >= nums[t]);
        //@ maintaining (\forall int t; 0 <= t && t < i && t < biggest_index; nums[t] < nums[biggest_index]);
        //@ decreases nums.length - i;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[biggest_index])
                biggest_index = i;
        }

        //@ maintaining 0 <= i && i <= nums.length;
        //@ maintaining nums.length == 0 || (0 <= biggest_index && biggest_index < nums.length);
        //@ maintaining (\forall int t; 0 <= t && t < nums.length; nums[biggest_index] >= nums[t]);
        //@ maintaining (\forall int t; 0 <= t && t < i && t != biggest_index; 2*nums[t] <= nums[biggest_index]);
        //@ decreases nums.length - i;
        for (int i = 0; i < nums.length; i++) {
            if (i != biggest_index && 2 * nums[i] > nums[biggest_index])
                return -1;
        }

        return biggest_index;
    }
}