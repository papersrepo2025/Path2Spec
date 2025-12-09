
public class LeapYear {

    /*@ public normal_behavior
      @   requires year % 4 == 0 && year % 100 == 0 && year % 400 == 0;
      @   assignable \nothing;
      @   ensures \result;
      @ also
      @ public normal_behavior
      @   requires year % 4 == 0 && year % 100 == 0 && year % 400 != 0;
      @   assignable \nothing;
      @   ensures !\result;
      @ also
      @ public normal_behavior
      @   requires year % 4 == 0 && year % 100 != 0;
      @   assignable \nothing;
      @   ensures \result;
      @ also
      @ public normal_behavior
      @   requires year % 4 != 0;
      @   assignable \nothing;
      @   ensures !\result;
      @*/
    public /*@ pure @*/ boolean isLeapYear(int year) {
        boolean leap = false;
         
        if (year % 4 == 0)
        {
            if ( year % 100 == 0)
            {
                if ( year % 400 == 0)
                    //@ assert year % 4 == 0 && year % 100 == 0 && year % 400 == 0;
                    leap = true;
                else
                    //@ assert year % 4 == 0 && year % 100 == 0 && year % 400 != 0;
                    leap = false;
            }
            else
                //@ assert year % 4 == 0 && year % 100 != 0;
                leap = true;
        }
        else
            //@ assert year % 4 != 0;
            leap = false;
	
        //@ assert leap <==> ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
	return leap;
   }
}