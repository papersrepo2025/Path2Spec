
public class TwoSum {
    //@ requires nums != null;
    //@ ensures \result.length == 2 || \result.length == 0;
    //@ ensures \forall int k; 0 <= k && k < \result.length; 0 <= \result[k] && \result[k] < nums.length;
    //@ ensures \result.length == 2 ==> nums[\result[0]] + nums[\result[1]] == target;
    //@ also
    //@ requires nums != null;
    //@ ensures nums.length < 2 ==> \result.length == 0;
    //@ ensures nums.length >= 2 ==> (\result.length == 0 || (0 <= \result[0] && \result[0] < nums.length && 0 <= \result[1] && \result[1] < nums.length && nums[\result[0]] + nums[\result[1]] == target));
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        //@ loop_invariant 0 <= i && i <= n;
        //@ decreases n - i;
        for (int i = 0; i < n; ++i) {
            //@ loop_invariant i + 1 <= j && j <= n;
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
