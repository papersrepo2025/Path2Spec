public class Perimeter
{   
    /* 
     - Analyze each method's computational behavior and outputs.
     - Add precise postconditions relating inputs to returned values.
     - Specify frame conditions to indicate no state changes (assignable \nothing).
     - Add necessary preconditions to prevent arithmetic overflow where relevant.
     - Ensure all JML annotations are correctly formatted and terminated.
    */

    /*@ normal_behavior
      @   assignable \nothing;
      @   ensures \result == 4L * (long)x;
      @*/
     long Perimeter(short x)
    {
	long squarePerimeter = 4 * (long)x;
	return squarePerimeter;
    }

    /*@ normal_behavior
      @   assignable \nothing;
      @   ensures \result == 5L * (long)x;
      @*/
     long Perimeter(int x)
    {
	long pentagonPerimeter = 5 * (long)x;
	return pentagonPerimeter;
    }

    /*@ normal_behavior
      @   requires x <= Long.MAX_VALUE / 6L && x >= Long.MIN_VALUE / 6L;
      @   assignable \nothing;
      @   ensures \result == 6L * x;
      @*/
     long Perimeter(long x)
    {
	long hexagonalPerimeter = 6 * x;
	return hexagonalPerimeter;
    }

    /*@ normal_behavior
      @   assignable \nothing;
      @   ensures \result == 2L * ((long)x + (long)y);
      @*/
     long Perimeter(int x, int y)
    {
	long perimeterRectangle = 2*((long)x + (long)y);
	return perimeterRectangle;
    }

    /*@ normal_behavior
      @   assignable \nothing;
      @   ensures \result == (long)x + (long)y + (long)z;
      @*/
     long Perimeter(int x, int y, int z)
    {
	long trianglePerimeter = (long)x + (long)y + (long)z;
	return trianglePerimeter;
    }

    /*@ normal_behavior
      @   assignable \nothing;
      @   ensures \result == (long)w + (long)x + (long)y + (long)z;
      @*/
     long Perimeter(int w, int x, int y, int z)
    {
	long trapeziumPerimeter = (long)w + (long)x + (long)y + (long)z;
	return trapeziumPerimeter;
    }
}