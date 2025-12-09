
class FindClosestNum {
    
    /*@ 
      @ requires nums == null;
      @ signals (NullPointerException e) true;
      @ also
      @ requires nums != null && nums.length == 0;
      @ ensures \result == Integer.MAX_VALUE;
      @ also
      @ requires nums != null && nums.length > 0;
      @ ensures (\exists int i; 0 <= i && i < nums.length; \result == nums[i]);
      @ ensures (\forall int j; 0 <= j && j < nums.length; 
      @            ((\result < 0 ? -\result : \result) < (nums[j] < 0 ? -nums[j] : nums[j]))
      @            || 
      @            ((\result < 0 ? -\result : \result) == (nums[j] < 0 ? -nums[j] : nums[j]) && \result >= nums[j])
      @         );
      @*/
    public int findClosestNumber(int[] nums) {
        int ans = Integer.MAX_VALUE;

        /*@ 
          @ loop_invariant 0 <= i && i <= nums.length;
          @ loop_invariant i == 0 ==> ans == Integer.MAX_VALUE;
          @ loop_invariant i > 0 ==> (\exists int t; 0 <= t && t < i; ans == nums[t]);
          @ loop_invariant (\forall int j; 0 <= j && j < i; 
          @                    ((ans < 0 ? -ans : ans) < (nums[j] < 0 ? -nums[j] : nums[j]))
          @                    ||
          @                    ((ans < 0 ? -ans : ans) == (nums[j] < 0 ? -nums[j] : nums[j]) && ans >= nums[j])
          @                 );
          @ decreases nums.length - i;
          @*/
        for(int i = 0; i < nums.length; i++) {
            //@ assume i != 0;
            int num = nums[i];
            int absNum = (num<0?-num:num);
            int absAns = (ans<0?-ans:ans);
            if(absNum < absAns || (absNum == absAns && num > ans)) {
                ans = num;
            }
        }

        return ans;
    }
}