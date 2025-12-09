
class JewelsInStones {
    //@ requires jewels != null && stones != null;
//@ requires jewels.length() == 0;
//@ ensures \result == 0;
    public int numJewelsInStones(String jewels, String stones) {
        int jewelsCount = 0;
        int jewelsLength = jewels.length(), stonesLength = stones.length();
        //@ maintaining 0 <= i && i <= stonesLength;
//@ maintaining jewelsCount == 0;
//@ decreases stonesLength - i;
        for (int i = 0; i < stonesLength; i++) {
            char stone = stones.charAt(i);
            //@ maintaining 0 <= j && j <= jewelsLength;
//@ maintaining jewelsCount == 0;
//@ decreases jewelsLength - j;
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
