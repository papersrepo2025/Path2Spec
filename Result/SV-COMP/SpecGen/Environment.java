public class Environment {

  public enum WaterLevelEnum {
    low,
    normal,
    high
  }

  /*@ spec_public @*/ private WaterLevelEnum waterLevel = WaterLevelEnum.normal;

  /*@ spec_public @*/ private boolean methaneLevelCritical = false;

  //@ invariant waterLevel != null;

  //@ ensures (\old(waterLevel) == WaterLevelEnum.high) ==> (waterLevel == WaterLevelEnum.normal);
  //@ ensures (\old(waterLevel) == WaterLevelEnum.normal) ==> (waterLevel == WaterLevelEnum.low);
  //@ ensures (\old(waterLevel) == WaterLevelEnum.low) ==> (waterLevel == WaterLevelEnum.low);
  //@ ensures methaneLevelCritical == \old(methaneLevelCritical);
  void lowerWaterLevel() {
    switch (waterLevel) {
      case high:
        waterLevel = WaterLevelEnum.normal;
        break;
      case normal:
        waterLevel = WaterLevelEnum.low;
        break;
    }
  }

  //@ ensures (\old(waterLevel) == WaterLevelEnum.low) ==> (waterLevel == WaterLevelEnum.normal);
  //@ ensures (\old(waterLevel) == WaterLevelEnum.normal) ==> (waterLevel == WaterLevelEnum.high);
  //@ ensures (\old(waterLevel) == WaterLevelEnum.high) ==> (waterLevel == WaterLevelEnum.high);
  //@ ensures methaneLevelCritical == \old(methaneLevelCritical);
  public void waterRise() {
    switch (waterLevel) {
      case low:
        waterLevel = WaterLevelEnum.normal;
        break;
      case normal:
        waterLevel = WaterLevelEnum.high;
        break;
    }
  }

  //@ ensures methaneLevelCritical == !\old(methaneLevelCritical);
  //@ ensures waterLevel == \old(waterLevel);
  public void changeMethaneLevel() {
    methaneLevelCritical = !methaneLevelCritical;
  }

  //@ ensures \result == methaneLevelCritical;
  //@ ensures waterLevel == \old(waterLevel) && methaneLevelCritical == \old(methaneLevelCritical);
  public boolean isMethaneLevelCritical() {
    return methaneLevelCritical;
  }

  //@ ensures \result != null;
  //@ ensures \result.equals("Env(Water:" + waterLevel + ",Meth:" + (methaneLevelCritical ? "CRIT" : "OK") + ")");
  //@ ensures waterLevel == \old(waterLevel) && methaneLevelCritical == \old(methaneLevelCritical);
  @Override
  public String toString() {
    return "Env(Water:" + waterLevel + ",Meth:" + (methaneLevelCritical ? "CRIT" : "OK") + ")";
  }

  //@ ensures \result == waterLevel;
  //@ ensures waterLevel == \old(waterLevel) && methaneLevelCritical == \old(methaneLevelCritical);
  public WaterLevelEnum getWaterLevel() {
    return waterLevel;
  }
}