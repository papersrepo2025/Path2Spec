class ConvertToFahrenheit {
    //@ ensures \result == celsius * 1.80 + 32.00;
    /*@ spec_public @*/ public /*@ pure @*/ double convertTemperature(double celsius) {
        return celsius * 1.80 + 32.00;
    }
}