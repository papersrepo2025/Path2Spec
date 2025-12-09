
public class OddEven {	
 
     //@ requires true; 
     //@ ensures \result == (x % 2 == 0);
     public boolean isEven(int x) { 
         	return x % 2 == 0;
       	 } 

     //@ requires true; 
     //@ ensures \result == (x % 2 != 0);
     public boolean isOdd(int x) { 
         	return x % 2 != 0;
       	 } 
}
