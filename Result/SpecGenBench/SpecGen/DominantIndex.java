class DominantIndex {
    
    //@ requires nums != null && nums.length > 0;
    //@ assignable \nothing;
 
    //@ ensures \result != -1 ==> (0 <= \result && \result < nums.length
    //@                           && (\forall int k; 0 <= k && k < nums.length; nums[\result] >= nums[k])
    //@                           && (\forall int i; 0 <= i && i < nums.length && i != \result; 2 * nums[i] <= nums[\result]));
    public static int dominantIndex(int[] nums) {
        int biggest_index = 0;

        //@ maintaining 0 <= i && i <= nums.length;
        //@ maintaining 0 <= biggest_index && biggest_index < nums.length;
        //@ maintaining (\forall int k; 0 <= k && k < i; nums[biggest_index] >= nums[k]);
        //@ decreases nums.length - i;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[biggest_index])
                biggest_index = i;
        }

        //@ maintaining 0 <= i && i <= nums.length;
        //@ maintaining 0 <= biggest_index && biggest_index < nums.length;
        //@ maintaining (\forall int k; 0 <= k && k < i && k != biggest_index; 2 * nums[k] <= nums[biggest_index]);
        //@ decreases nums.length - i;
        for (int i = 0; i < nums.length; i++) {
            if (i != biggest_index && 2 * nums[i] > nums[biggest_index])
                return -1;
        }

        return biggest_index;
    }
}
