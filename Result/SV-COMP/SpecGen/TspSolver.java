public class TspSolver {
  /*@ spec_public @*/ private final int N;
  /*@ spec_public @*/ private int D[][];
  /*@ spec_public @*/ private boolean visited[];
  /*@ spec_public @*/ private int best;

  /*@ spec_public @*/ public int nCalls;

  //@ requires N > 0;
  //@ requires D != null && D.length >= N && (\forall int i; 0 <= i && i < N; D[i] != null && D[i].length >= N);
  //@ ensures this.N == N && this.D == D;
  //@ ensures visited != null && visited.length == N;
  //@ ensures (\forall int i; 0 <= i && i < N; visited[i] == false);
  public TspSolver(int N, int D[][]) {
    this.N = N;
    this.D = D;
    this.visited = new boolean[N];
    //@ assume visited != null && visited.length == N;
    //@ assume (\forall int i; 0 <= i && i < N; visited[i] == false);
    this.nCalls = 0;
  }

  //@ requires N > 0;
  //@ requires D != null && D.length >= N && (\forall int i; 0 <= i && i < N; D[i] != null && D[i].length >= N);
  //@ requires visited != null && visited.length == N;
  //@ ensures \result == best;
  //@ ensures visited != null && visited.length == N;
  //@ ensures visited[0] == true && (\forall int i; 1 <= i && i < N; visited[i] == false);
 
  public int solve() {
    best = Integer.MAX_VALUE;

    //@ maintaining 0 <= i && i <= N;
    //@ maintaining (\forall int j; 0 <= j && j < i; visited[j] == false);
    //@ decreases N - i;
    for (int i = 0; i < N; i++) visited[i] = false;

    //@ assume visited != null && 0 <= 0 && 0 < visited.length;
    visited[0] = true;
    search(0, 0, N - 1);

    //@ assume visited != null && visited.length == N;
    return best;
  }

  //@ requires 0 <= src && src < N;
  //@ requires 0 <= nLeft && nLeft <= N;
  //@ ensures \result == length;
  /*@ pure @*/ private int bound(int src, int length, int nLeft) {
    return length;
  }

  //@ requires 0 <= src && src < N;
  //@ requires 0 <= nLeft && nLeft <= N;
  //@ requires D != null && D.length >= N && (\forall int i; 0 <= i && i < N; D[i] != null && D[i].length >= N);
  //@ requires visited != null && visited.length == N;
  //@ ensures (\forall int i; 0 <= i && i < N; visited[i] == \old(visited[i]));
  //@ ensures visited == \old(visited);
  //@ ensures nCalls >= \old(nCalls) + 1;
  //@ ensures best <= \old(best);
  //@ assignable visited[*], best, nCalls;
  private void search(int src, int length, int nLeft) {
    nCalls++;

    if (nLeft == 0) {
      //@ assume D != null && 0 <= src && src < D.length && D[src] != null && 0 < D[src].length;
      if (length + D[src][0] < best) best = length + D[src][0];
      return;
    }

    if (bound(src, length, nLeft) >= best) return;

    //@ assume visited != null && visited.length == N;
    //@ maintaining 0 <= i && i <= N;
    //@ maintaining (\forall int j; 0 <= j && j < N; visited[j] == \old(visited[j]));
    //@ decreases N - i;
    for (int i = 0; i < N; i++) {
      //@ assume 0 <= i && i < visited.length;
      if (visited[i]) continue;

      visited[i] = true;
      //@ assume D != null && 0 <= src && src < D.length && D[src] != null && 0 <= i && i < D[src].length;
      search(i, length + D[src][i], nLeft - 1);
      //@ assume 0 <= i && i < visited.length;
      visited[i] = false;
    }
  }
}
