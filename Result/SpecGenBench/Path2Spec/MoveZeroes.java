
class MoveZeroes {
    //@ requires nums != null;
    //@ requires nums.length == 0;
    //@ ensures nums.length == 0;
    //@ also
    //@ requires nums != null && nums.length >= 1 && (\forall int i; 0 <= i && i < nums.length; nums[i] != 0);
    //@ ensures (\forall int i; 0 <= i && i < nums.length; nums[i] == \old(nums[i]));
    //@ also
    //@ requires nums != null && nums.length >= 1 && (\forall int i; 0 <= i && i < nums.length; nums[i] == 0);
    //@ ensures (\forall int i; 0 <= i && i < nums.length; nums[i] == 0);
    //@ also
    //@ requires nums != null && nums.length >= 1;
    //@ requires (\exists int i; 0 <= i && i < nums.length; nums[i] == 0);
    //@ requires (\exists int j; 0 <= j && j < nums.length; nums[j] != 0);
    //@ ensures (\forall int i; 0 <= i && i < nums.length; \old(nums[i]) != 0 ==> nums[i] != 0);
    //@ ensures (\forall int i, j; 0 <= i && i < j && j < nums.length && \old(nums[i]) != 0 && \old(nums[j]) != 0; nums[i] == \old(nums[i]) && nums[j] == \old(nums[j]));
    //@ ensures (\forall int i; 0 <= i && i < nums.length; nums[i] == 0 ==> (\forall int j; i < j && j < nums.length; nums[j] == 0));
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        //@ maintaining 0 <= right && right <= n;
        //@ maintaining 0 <= left && left <= right;
        //@ maintaining (\forall int i; 0 <= i && i < left; nums[i] != 0);
        //@ maintaining (\forall int i; left <= i && i < right; nums[i] == 0);
        //@ decreases n - right;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }
    //@ requires nums != null;
    //@ requires 0 <= left && left < nums.length;
    //@ requires 0 <= right && right < nums.length;
    //@ ensures nums[left] == \old(nums[right]) && nums[right] == \old(nums[left]);
    //@ also
    //@ requires nums != null && 0 <= left && left < nums.length && 0 <= right && right < nums.length;
    //@ ensures nums[left] == \old(nums[right]) && nums[right] == \old(nums[left]);
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
