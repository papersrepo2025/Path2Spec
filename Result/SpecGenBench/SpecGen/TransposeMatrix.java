public class TransposeMatrix {

    /*@
      @ requires matrix != null;
      @ requires (\forall int r; 0 <= r && r < matrix.length; matrix[r] != null);
      @ requires (\forall int r; 0 <= r && r < matrix.length;
      @                     matrix[r].length == (matrix.length == 0 ? 0 : matrix[0].length));
      @ ensures \result != null;
      @ ensures \result.length == (matrix.length == 0 ? 0 : matrix[0].length);
      @ ensures (\forall int u; 0 <= u && u < \result.length; \result[u] != null && \result[u].length == matrix.length);
      @ ensures (\forall int u; 0 <= u && u < (matrix.length == 0 ? 0 : matrix[0].length);
      @            (\forall int v; 0 <= v && v < matrix.length; \result[u][v] == matrix[v][u]));
      @ ensures \fresh(\result) && (\forall int u; 0 <= u && u < \result.length; \fresh(\result[u]));
      @*/
    public /*@ spec_public @*/ int[][] transposeMat(int[][] matrix) {
        int rows = matrix.length;
        int cols = (rows == 0 ? 0 : matrix[0].length);
        int[][] transpose = new int[cols][rows];

        int idxCol = 0;
        while (idxCol < cols) {
            int idxRow = 0;
            while (idxRow < rows) {
                transpose[idxCol][idxRow] = matrix[idxRow][idxCol];
                idxRow++;
            }
            idxCol++;
        }
        return transpose;
    }
}