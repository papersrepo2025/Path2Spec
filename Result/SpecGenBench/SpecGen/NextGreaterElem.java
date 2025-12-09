class NextGreaterElem {
    /*@ requires nums1 != null && nums2 != null;
      @ requires (\forall int i; 0 <= i && i < nums1.length;
      @              (\exists int j; 0 <= j && j < nums2.length; nums2[j] == nums1[i]));
      @ ensures \result != null && \result.length == nums1.length;
      @ ensures (\forall int i; 0 <= i && i < nums1.length;
      @            (\exists int j; 0 <= j && j < nums2.length
      @                          && nums2[j] == nums1[i]
      @                          && (\forall int t; 0 <= t && t < j; nums2[t] != nums1[i])
      @                          && (
      @                               (\exists int k; j + 1 <= k && k < nums2.length
      @                                             && nums2[k] >= nums2[j]
      @                                             && (\forall int t2; j + 1 <= t2 && t2 < k; nums2[t2] < nums2[j])
      @                                             && \result[i] == nums2[k])
      @                               ||
      @                               ((\forall int t3; j + 1 <= t3 && t3 < nums2.length; nums2[t3] < nums2[j])
      @                                 && \result[i] == -1)
      @                             )
      @            ));
      @*/
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] res = new int[m];
        /*@ maintaining 0 <= i && i <= m;
          @ maintaining res != null && res.length == m;
          @ maintaining (\forall int p; 0 <= p && p < i;
          @               (\exists int j0; 0 <= j0 && j0 < n
          @                             && nums2[j0] == nums1[p]
          @                             && (\forall int t0; 0 <= t0 && t0 < j0; nums2[t0] != nums1[p])
          @                             && (
          @                                  (\exists int k0; j0 + 1 <= k0 && k0 < n
          @                                                && nums2[k0] >= nums2[j0]
          @                                                && (\forall int u0; j0 + 1 <= u0 && u0 < k0; nums2[u0] < nums2[j0])
          @                                                && res[p] == nums2[k0])
          @                                  ||
          @                                  ((\forall int v0; j0 + 1 <= v0 && v0 < n; nums2[v0] < nums2[j0])
          @                                    && res[p] == -1)
          @                                )));
          @ decreasing m - i;
          @*/
        for (int i = 0; i < m; ++i) {
            int j = 0;
            /*@ maintaining 0 <= j && j <= n;
              @ maintaining (\forall int t; 0 <= t && t < j; nums2[t] != nums1[i]);
              @ decreasing n - j;
              @*/
            while (j < n && nums2[j] != nums1[i]) {
                ++j;
            }
            //@ assert j < n;
            int k = j + 1;
            /*@ maintaining j + 1 <= k && k <= n;
              @ maintaining (\forall int t; j + 1 <= t && t < k; nums2[t] < nums2[j]);
              @ decreasing n - k;
              @*/
            while (k < n && nums2[k] < nums2[j]) {
                ++k;
            }
            res[i] = k < n ? nums2[k] : -1;
        }
        return res;
    }
}