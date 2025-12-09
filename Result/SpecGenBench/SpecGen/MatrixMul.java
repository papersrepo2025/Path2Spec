public class MatrixMul {
    
    //@ requires a != null && b != null;
    //@ requires a.length >= 2 && b.length >= 2;
    //@ requires a[0] != null && a[1] != null && b[0] != null && b[1] != null;
    //@ requires a[0].length >= 2 && a[1].length >= 2 && b[0].length >= 2 && b[1].length >= 2;
    //@ ensures \result != null && \result.length == 2 && (\forall int ii; 0 <= ii && ii < 2; \result[ii] != null && \result[ii].length == 2);
    //@ ensures (\forall int ii, jj; 0 <= ii && ii < 2 && 0 <= jj && jj < 2; \result[ii][jj] == a[ii][0] * b[0][jj] + a[ii][1] * b[1][jj]);
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        //@ loop_invariant 0 <= i && i <= 2;
        //@ decreases 2 - i;
        for (int i = 0; i < 2; i++) {
            //@ loop_invariant 0 <= j && j <= 2;
            //@ decreases 2 - j;
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

}