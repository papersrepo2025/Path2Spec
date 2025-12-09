public class BellmanFord {

  static final int INFINITY = Integer.MAX_VALUE;

  //@ requires N >= 0;
  //@ requires D != null;
  //@ requires 0 <= src && src < N;
  //@ requires N <= 46340;
  //@ requires D.length == N * N;
  //@ ensures \result != null;
  //@ ensures \result.length == N;
  //@ ensures \fresh(\result);
  static int[] runBellmanFord(int N, int D[], int src) {
    // Initialize distances.
    int dist[] = new int[N];
    boolean infinite[] = new boolean[N];
    //@ maintaining 0 <= i && i <= N;
    //@ maintaining dist != null && dist.length == N;
    //@ maintaining infinite != null && infinite.length == N;
    //@ maintaining INFINITY == Integer.MAX_VALUE;
    //@ maintaining (\forall int t; 0 <= t && t < i; dist[t] == INFINITY && infinite[t]);
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
    //@ maintaining 0 <= k && k <= N;
    //@ maintaining dist != null && dist.length == N;
    //@ maintaining infinite != null && infinite.length == N;
    //@ maintaining D != null && D.length == N * N;
    //@ maintaining N >= 0 && N <= 46340;
    //@ decreases N - k;
    for (k = 0; k < N; k++) { // V+1 branches
      boolean relaxed = false;
      //@ maintaining 0 <= i && i <= N;
      //@ maintaining dist != null && dist.length == N;
      //@ maintaining infinite != null && infinite.length == N;
      //@ maintaining D != null && D.length == N * N;
      //@ maintaining N >= 0 && N <= 46340;
      //@ decreases N - i;
      for (int i = 0; i < N; i++) { // V(V+1) branches
        //@ maintaining 0 <= j && j <= N;
        //@ maintaining 0 <= i && i < N;
        //@ maintaining dist != null && dist.length == N;
        //@ maintaining infinite != null && infinite.length == N;
        //@ maintaining D != null && D.length == N * N;
        //@ maintaining N >= 0 && N <= 46340;
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