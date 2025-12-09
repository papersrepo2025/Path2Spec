public class BellmanFord {

  static final int INFINITY = Integer.MAX_VALUE;
  //@ requires 0 <= N && N <= 2000;
  //@ requires D != null;
  //@ requires D.length == N * N;
  //@ requires 0 <= src && src < N;
  //@ requires (\forall int t; 0 <= t && t < D.length; -1000000 <= D[t] && D[t] <= 1000000);
  //@ ensures \result != null;
  //@ ensures \result.length == N;
  //@ also
  //@ requires N >= 0 && N <= 46340;
  //@ requires D != null;
  //@ requires D.length == N * N;
  //@ requires 0 <= src && src < N;
  //@ ensures \result != null;
  //@ ensures \result.length == N;
  static int[] runBellmanFord(int N, int D[], int src) {
    // Initialize distances.
    int dist[] = new int[N];
    boolean infinite[] = new boolean[N];
    //@ loop_invariant 0 <= i && i <= N;
    //@ loop_invariant dist != null && infinite != null;
    //@ loop_invariant dist.length == N && infinite.length == N;
    //@ loop_invariant (\forall int t; 0 <= t && t < i; dist[t] == INFINITY && infinite[t]);
    //@ decreases N - i;
    for (int i = 0; i < N; i++) { // V+1 branches
      dist[i] = INFINITY;
      infinite[i] = true;
    }
    dist[src] = 0;
    infinite[src] = false;

    // Keep relaxing edges until either:
    //  (1) No more edges need to be updated.
    //  (2) We have passed through the edges N times.
    int k;
    //@ loop_invariant 0 <= k && k <= N;
    //@ loop_invariant dist != null && infinite != null;
    //@ loop_invariant dist.length == N && infinite.length == N;
    //@ loop_invariant 0 <= src && src < N;
    //@ loop_invariant !infinite[src];
    //@ decreases N - k;
    for (k = 0; k < N; k++) { // V+1 branches
      boolean relaxed = false;
      //@ loop_invariant 0 <= i && i <= N;
      //@ loop_invariant dist.length == N && infinite.length == N;
      //@ loop_invariant 0 <= src && src < N;
      //@ loop_invariant !infinite[src];
      //@ loop_invariant D != null && D.length == N * N;
      //@ decreases N - i;
      for (int i = 0; i < N; i++) { // V(V+1) branches
        //@ loop_invariant 0 <= j && j <= N;
        //@ loop_invariant 0 <= i && i < N;
        //@ loop_invariant dist.length == N && infinite.length == N;
        //@ loop_invariant 0 <= src && src < N;
        //@ loop_invariant !infinite[src];
        //@ loop_invariant D != null && D.length == N * N;
        //@ decreases N - j;
        for (int j = 0; j < N; j++) { // V^2(V+1) branches
          if (i == j) continue; // V^3 branches
          if (!infinite[i]) { // V^2(V-1) branches
            if (dist[j] > dist[i] + D[i * N + j]) { // V^2(V-1) branches
              dist[j] = dist[i] + D[i * N + j];
              infinite[j] = false;
              relaxed = true;
            }
          }
        }
      }
      if (!relaxed) // V branches
      break;
    }

    // Check for negative-weight egdes.
    if (k == N) { // 1 branch
      // We relaxed during the N-th iteration, so there must be
      // a negative-weight cycle.
    }

    // Return the computed distances.
    return dist;
  }
}
