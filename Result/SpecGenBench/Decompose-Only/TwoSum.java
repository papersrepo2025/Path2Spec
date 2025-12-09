

class TwoSum {
    //@ requires nums == null;
    //@ signals (NullPointerException e) true;
    //@ also
    //@ requires nums != null;
    //@ ensures (\exists int i, j; 0 <= i && i < j && j < nums.length && nums[i] + nums[j] == target)
    //@     ==> (\result != null && \result.length == 2
    //@          && 0 <= \result[0] && \result[0] < \result[1] && \result[1] < nums.length
    //@          && nums[\result[0]] + nums[\result[1]] == target);
    //@ ensures !(\exists int i, j; 0 <= i && i < j && j < nums.length && nums[i] + nums[j] == target)
    //@     ==> (\result != null && \result.length == 0);
    public int[] twoSum(int[] nums, int target) {
        //@ assume nums != null;
        int n = nums.length;
        //@ loop_invariant 0 <= i && i <= n;
        //@ loop_invariant (\forall int a; 0 <= a && a < i; (\forall int b; a < b && b < n; nums[a] + nums[b] != target));
        //@ decreases n - i;
        for (int i = 0; i < n; ++i) {
            //@ assume i != 0;
            //@ loop_invariant i + 1 <= j && j <= n;
            //@ loop_invariant 0 <= i && i < n;
            //@ loop_invariant (\forall int b; i < b && b < j; nums[i] + nums[b] != target);
            //@ decreases n - j;
            for (int j = i + 1; j < n; ++j) {
                //@ assume i != 0;
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}