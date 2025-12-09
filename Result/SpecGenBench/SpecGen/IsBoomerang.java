class IsBoomerang {
    /*@ public normal_behavior
      @   requires points != null
      @         && points.length >= 3
      @         && (\forall int i; 0 <= i && i < 3; points[i] != null && points[i].length >= 2);
 
      @       (((points[1][0] - points[0][0]) * (points[2][1] - points[0][1]))
      @        - ((points[1][1] - points[0][1]) * (points[2][0] - points[0][0]))) != 0;
      @*/
    public /*@ spec_public @*/ boolean isBoomerang(int[][] points) {
        int[] v1 = {points[1][0] - points[0][0], points[1][1] - points[0][1]};
        int[] v2 = {points[2][0] - points[0][0], points[2][1] - points[0][1]};
        return v1[0] * v2[1] - v1[1] * v2[0] != 0;
    }
}
