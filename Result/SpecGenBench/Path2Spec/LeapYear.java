
public class LeapYear {
    //@ requires Integer.MIN_VALUE < year && year <= Integer.MAX_VALUE; // Valid integer range for year;
    //@ requires year % 4 != 0; // Specific execution path;
    //@ ensures \result == false; // Expected output for this path;
    //@ also
    //@ requires year % 4 == 0 && year % 100 != 0;
    //@ ensures \result == true;
    //@ also
    //@ requires year % 4 == 0 && year % 100 == 0 && year % 400 != 0;
    //@ ensures \result == false;
    //@ also
    //@ requires year % 4 == 0 && year % 100 == 0 && year % 400 == 0;
    //@ ensures \result == true;
    public boolean isLeapYear(int year) {
        boolean leap = false;
         
        if (year % 4 == 0)
        {
            if ( year % 100 == 0)
            {
                if ( year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            }
            else
                leap = true;
        }
        else
            leap = false;
    
        return leap;
    }
}
