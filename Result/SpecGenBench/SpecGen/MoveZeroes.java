class MoveZeroes {
    //@ requires nums != null;
    //@ assignable nums[*];
    //@ ensures (\forall int v; (\num_of int i; 0 <= i && i < nums.length; nums[i] == v) == (\num_of int i; 0 <= i && i < nums.length; \old(nums[i]) == v));
 
    //@                   && (\forall int i; 0 <= i && i < k; nums[i] != 0)
    //@                   && (\forall int i; k <= i && i < nums.length; nums[i] == 0)
    //@                   && k == (\num_of int i; 0 <= i && i < nums.length; \old(nums[i]) != 0));
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        //@ maintaining 0 <= left && left <= right && right <= n;
        //@ maintaining (\forall int i; 0 <= i && i < left; nums[i] != 0);
        //@ maintaining (\forall int i; left <= i && i < right; nums[i] == 0);
        //@ maintaining (\forall int i; right < i && i < n; nums[i] == \old(nums[i]));
        //@ decreases n - right;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    //@ requires nums != null && 0 <= left && left < nums.length && 0 <= right && right < nums.length;
    //@ assignable nums[left], nums[right];
    //@ ensures nums[left] == \old(nums[right]) && nums[right] == \old(nums[left]);
    //@ ensures (\forall int i; 0 <= i && i < nums.length && i != left && i != right; nums[i] == \old(nums[i]));
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
