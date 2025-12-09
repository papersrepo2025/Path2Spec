
public class LeapYearSeq {
    
    //@ ensures \result <==> ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));
    //@ also
    //@ requires year % 400 == 0;
    //@ ensures \result;
    //@ also
    //@ requires year % 400 != 0 && year % 100 == 0;
    //@ ensures !\result;
    //@ also
    //@ requires year % 4 == 0 && year % 100 != 0;
    //@ ensures \result;
    //@ also
    //@ requires year % 4 != 0;
    //@ ensures !\result;
    public  boolean isLeapYear(int year) {
        return ((year % 4 == 0) ? ((year % 100 == 0) ? ((year % 400 == 0) ? true : false) : true) : false);
   }
}