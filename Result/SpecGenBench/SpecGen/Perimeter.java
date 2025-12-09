public class Perimeter
{   

     //@ ensures \result == 4L * (long)x;
     long Perimeter(short x)
    {
	long squarePerimeter = 4 * (long)x;
	return squarePerimeter;
    }

     //@ ensures \result == 5L * (long)x;
     long Perimeter(int x)
    {
	long pentagonPerimeter = 5 * (long)x;
	return pentagonPerimeter;
    }

     //@ ensures \result == 6L * x;
     long Perimeter(long x)
    {
	long hexagonalPerimeter = 6 * x;
	return hexagonalPerimeter;
    }

     //@ ensures \result == 2L * ((long)x + (long)y);
     long Perimeter(int x, int y)
    {
	long perimeterRectangle = 2*((long)x + (long)y);
	return perimeterRectangle;
    }

     //@ ensures \result == (long)x + (long)y + (long)z;
     long Perimeter(int x, int y, int z)
    {
	long trianglePerimeter = (long)x + (long)y + (long)z;
	return trianglePerimeter;
    }

     //@ ensures \result == (long)w + (long)x + (long)y + (long)z;
     long Perimeter(int w, int x, int y, int z)
    {
	long trapeziumPerimeter = (long)w + (long)x + (long)y + (long)z;
	return trapeziumPerimeter;
    }
}