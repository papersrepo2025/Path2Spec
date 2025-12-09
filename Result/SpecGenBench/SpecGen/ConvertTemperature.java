class ConvertTemperature {
    /*@ public normal_behavior
      @   ensures \result != null
      @        && \fresh(\result)
      @        && \result.length == 2
      @        && (Double.isNaN(\result[0]) <==> Double.isNaN(celsius + 273.15))
      @        && (Double.isNaN(\result[1]) <==> Double.isNaN(celsius * 1.80 + 32.00))
      @        && (!Double.isNaN(\result[0]) ==> \result[0] == celsius + 273.15)
      @        && (!Double.isNaN(\result[1]) ==> \result[1] == celsius * 1.80 + 32.00);
      @   assignable \nothing;
      @*/
    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15, celsius * 1.80 + 32.00};
    }
}