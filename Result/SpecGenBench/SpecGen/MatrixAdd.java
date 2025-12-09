public class MatrixAdd {

    /*@
      @ requires a != null && b != null;
      @ requires a.length == 2 && b.length == 2;
      @ requires a[0] != null && a[1] != null && b[0] != null && b[1] != null;
      @ requires a[0].length == 2 && a[1].length == 2 && b[0].length == 2 && b[1].length == 2;
      @ ensures \result != null && \result.length == 2;
      @ ensures \result[0] != null && \result[1] != null;
      @ ensures \result[0].length == 2 && \result[1].length == 2;
 
 
 
 
      @ ensures \fresh(\result) && \fresh(\result[0]) && \fresh(\result[1]);
      @*/
    public int[][] add(int[][] a, int[][] b) {
        int[][] c = new int[2][];
        c[0] = new int[2];
        c[1] = new int[2];
        c[0][0] = a[0][0] + b[0][0];
        c[0][1] = a[0][1] + b[0][1];
        c[1][0] = a[1][0] + b[1][0];
        c[1][1] = a[1][1] + b[1][1];
        return c;
    }

}
