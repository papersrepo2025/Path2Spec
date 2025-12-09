
import java.util.Arrays;

public class ContainsDuplicate {
    //@ requires nums != null;
    //@ requires nums.length == 1;
    //@ ensures \result == false;
    //@ also
    //@ requires nums != null;
    //@ requires nums.length >= 2;
    //@ ensures (\forall int i; 0 <= i < nums.length-1; nums[i] != nums[i+1]) ==> \result == false;
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        //@ loop_invariant 0 <= i && i <= n - 1;
        //@ decreases n - 1 - i;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
