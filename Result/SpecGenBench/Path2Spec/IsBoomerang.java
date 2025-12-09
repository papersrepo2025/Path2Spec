
class IsBoomerang {
    //@ requires points != null && points.length >= 3;
//@ requires points[0] != null && points[1] != null && points[2] != null;
//@ requires points[0].length == 2 && points[1].length == 2 && points[2].length == 2;
//@ requires (points[1][0] - points[0][0]) * (points[2][1] - points[0][1]) == (points[2][0] - points[0][0]) * (points[1][1] - points[0][1]);
//@ ensures \result == false;
    public boolean isBoomerang(int[][] points) {
        int[] v1 = {points[1][0] - points[0][0], points[1][1] - points[0][1]};
        int[] v2 = {points[2][0] - points[0][0], points[2][1] - points[0][1]};
        return v1[0] * v2[1] - v1[1] * v2[0] != 0;
    }
}
