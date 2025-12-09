class ConvertToKelvin {
 
    //@ ensures !Double.isNaN(celsius) ==> \result == celsius + 273.15;
    public /*@ pure @*/ double convertTemperature(double celsius) {
        return celsius + 273.15;
    }
}
