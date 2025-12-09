
class DominantIndex {
    //@ requires nums != null;
    //@ requires nums.length == 1;
    //@ ensures \result == 0;
    //@ also
    //@ requires nums != null && nums.length > 0;
    //@ ensures (\result >= 0 && \result < nums.length) || \result == -1;
    //@ also
    //@ requires nums != null && nums.length >= 2;
    //@ ensures \result == -1 || (0 <= \result && \result < nums.length);
    public static int dominantIndex(int[] nums) {
        int biggest_index = 0;

        //@ maintaining 0 <= i && i <= nums.length;
        //@ maintaining 0 <= biggest_index && biggest_index < nums.length;
        //@ decreases nums.length - i;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[biggest_index])
                biggest_index = i;
        }

        //@ maintaining 0 <= i && i <= nums.length;
        //@ maintaining 0 <= biggest_index && biggest_index < nums.length;
        //@ decreases nums.length - i;
        for (int i = 0; i < nums.length; i++) {
            if (i != biggest_index && 2 * nums[i] > nums[biggest_index])
                return -1;
        }

        return biggest_index;
    }
}
