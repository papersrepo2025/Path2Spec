class FindClosestNum {
    
    /*@ 
      @ requires nums != null;
      @ requires nums.length > 0;
      @ // avoid overflow when taking "absolute value"
      @ requires (\forall int j; 0 <= j && j < nums.length; nums[j] != Integer.MIN_VALUE);
      @ assignable \nothing;
      @
 
      @ // closest to zero by absolute value
      @ ensures (\forall int j; 0 <= j && j < nums.length;
      @            ((\result < 0 ? -\result : \result) <= (nums[j] < 0 ? -nums[j] : nums[j])));
      @ // tie-breaker: prefer the larger value when absolute values are equal
      @ ensures (\forall int j; 0 <= j && j < nums.length;
      @            ((\result < 0 ? -\result : \result) == (nums[j] < 0 ? -nums[j] : nums[j]))
      @            ==> \result >= nums[j]);
      @ // if there is a zero, it must be the answer
      @ ensures ((\exists int k; 0 <= k && k < nums.length; nums[k] == 0) ==> \result == 0);
 
      @*/
    public int findClosestNumber(int[] nums) {
        int ans = Integer.MAX_VALUE;

        /*@ 
          @ maintaining 0 <= i && i <= nums.length;
          @ maintaining (i == 0 ==> ans == Integer.MAX_VALUE);
          @ // after the first iteration, ans is always one of the examined elements
 
          @ // ans is closest to zero among the examined prefix
          @ maintaining (\forall int j; 0 <= j && j < i;
          @                ((ans < 0 ? -ans : ans) <= (nums[j] < 0 ? -nums[j] : nums[j])));
          @ // tie-breaker on the examined prefix
          @ maintaining (\forall int j; 0 <= j && j < i;
          @                ((ans < 0 ? -ans : ans) == (nums[j] < 0 ? -nums[j] : nums[j]))
          @                ==> ans >= nums[j]);
          @ decreases nums.length - i;
          @*/
        for(int i = 0; i < nums.length; i++) {
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
