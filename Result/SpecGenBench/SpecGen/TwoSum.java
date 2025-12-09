class TwoSum {
    //@ requires nums != null;
    //@ assignable \nothing;
    //@ ensures \result != null;
    //@ ensures (\result.length == 0) <==> (\forall int i; 0 <= i && i < nums.length; (\forall int j; i < j && j < nums.length; nums[i] + nums[j] != target));
    //@ ensures \result.length == 2 ==> 0 <= \result[0] && \result[0] < \result[1] && \result[1] < nums.length && nums[\result[0]] + nums[\result[1]] == target;
    //@ ensures \result.length == 0 || \result.length == 2;
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        //@ maintaining 0 <= i && i <= n;
        //@ maintaining n == nums.length;
        //@ maintaining (\forall int p; 0 <= p && p < i; (\forall int q; p < q && q < n; nums[p] + nums[q] != target));
        //@ decreases n - i;
        for (int i = 0; i < n; ++i) {
            //@ maintaining i >= 0 && i < n;
            //@ maintaining i + 1 <= j && j <= n;
            //@ maintaining n == nums.length;
            //@ maintaining (\forall int q; i < q && q < j; nums[i] + nums[q] != target);
            //@ decreases n - j;
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}