class JewelsInStones {
    /*@ public normal_behavior
      @   requires jewels != null && stones != null;
      @   assignable \nothing;
      @   ensures \result == (\num_of int i; 0 <= i && i < stones.length();
      @                         (\exists int j; 0 <= j && j < jewels.length();
      @                              stones.charAt(i) == jewels.charAt(j)));
      @*/
    public int numJewelsInStones(String jewels, String stones) {
        int jewelsCount = 0;
        int jewelsLength = jewels.length(), stonesLength = stones.length();
 
 
 
          @                                   (\exists int j0; 0 <= j0 && j0 < jewelsLength;
          @                                             stones.charAt(k) == jewels.charAt(j0)));
 
          @*/
        for (int i = 0; i < stonesLength; i++) {
            char stone = stones.charAt(i);
            /*@ maintaining 0 <= j && j <= jewelsLength;
              @ maintaining (\forall int t; 0 <= t && t < j; jewels.charAt(t) != stone);
              @ decreases jewelsLength - j;
              @*/
            for (int j = 0; j < jewelsLength; j++) {
                char jewel = jewels.charAt(j);
                if (stone == jewel) {
                    jewelsCount++;
                    break;
                }
            }
        }
        return jewelsCount;
    }
}
