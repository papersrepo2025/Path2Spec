
public class AbsSeq {
    
    /*@ 
      @ requires num < 0;
      @ ensures \result == -num;
      @ also
      @ requires num >= 0;
      @ ensures \result == num;
      @*/
    public int Abs(int num) {
        return ((num < 0) ? (-num) : (num));
    }

}