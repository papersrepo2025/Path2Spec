class ConvertToKelvin {
    //@ assignable \nothing;
    //@ requires celsius == celsius;
    //@ ensures \result == \old(celsius) + 273.15;
    /*@ pure @*/ 
    public double convertTemperature(double celsius) {
        return celsius + 273.15;
    }
}