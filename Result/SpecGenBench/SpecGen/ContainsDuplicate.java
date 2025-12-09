import java.util.Arrays;

public class ContainsDuplicate {

    //@ requires nums != null;
    //@ assignable nums[*];
    //@ ensures \result <==> (\exists int i, j; 0 <= i && i < j && j < \old(nums.length); \old(nums[i]) == \old(nums[j]));
    //@ ensures nums.length == \old(nums.length);
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        //@ maintaining 0 <= i && i <= n;
        //@ maintaining n == nums.length;
        //@ maintaining (\forall int k; 0 <= k && k < i; nums[k] != nums[k+1]);
        //@ decreases n - i;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}