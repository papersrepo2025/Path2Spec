public class PerimeterDriver {
	
	 /*@ public normal_behavior
	   @   requires true;
	   @   assignable \everything;
	   @   ensures true;
	   @*/
	 public  long driver(int select, int w, int x, int y, int z, short shortNum, long longNum) {

		Perimeter p = new Perimeter();
		long result = 0;

		switch (select) {
		case 0:
			result = p.Perimeter(shortNum);
			break;
		case 1:
			result = p.Perimeter(w);
			break;
		case 2:
			result = p.Perimeter(longNum);
			break;
		case 3:	
			result = p.Perimeter(w, x);
			break;
		case 4:
			result = p.Perimeter(w, x, y);
			break;
		case 5:
			result = p.Perimeter(w, x, y, z);
			break;
		}
		return result;
	}
	    /*@ public normal_behavior
	      @   requires true;
	      @   assignable \everything;
	      @   ensures true;
	      @*/
	    public static void main(String[] args) {
        PerimeterDriver driver = new PerimeterDriver();

        System.out.println("Rectangle Perimeter with w=5 and x=7: " + driver.driver(3, 5, 7, 0, 0, (short)0, 0));
        System.out.println("Square Perimeter with w=4: " + driver.driver(1, 4, 0, 0, 0, (short)0, 0));
    }
}