class Environment {

  public enum WaterLevelEnum {
    low,
    normal,
    high
  }

  /*@ spec_public @*/ private WaterLevelEnum waterLevel = WaterLevelEnum.normal;

  /*@ spec_public @*/ private boolean methaneLevelCritical = false;

  //@ ensures (\old(waterLevel) == WaterLevelEnum.high ==> waterLevel == WaterLevelEnum.normal)
  //@      && (\old(waterLevel) == WaterLevelEnum.normal ==> waterLevel == WaterLevelEnum.low)
  //@      && (\old(waterLevel) == WaterLevelEnum.low ==> waterLevel == \old(waterLevel));
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

  //@ ensures (\old(waterLevel) == WaterLevelEnum.low ==> waterLevel == WaterLevelEnum.normal)
  //@      && (\old(waterLevel) == WaterLevelEnum.normal ==> waterLevel == WaterLevelEnum.high)
  //@      && (\old(waterLevel) == WaterLevelEnum.high ==> waterLevel == \old(waterLevel));
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
  public void changeMethaneLevel() {
    methaneLevelCritical = !methaneLevelCritical;
  }

  //@ ensures \result == methaneLevelCritical;
  public boolean isMethaneLevelCritical() {
    return methaneLevelCritical;
  }

  //@ ensures \result != null;
  @Override
  public String toString() {
    return "Env(Water:" + waterLevel + ",Meth:" + (methaneLevelCritical ? "CRIT" : "OK") + ")";
  }

  //@ ensures \result == waterLevel;
  public WaterLevelEnum getWaterLevel() {
    return waterLevel;
  }
}

public class MinePump {

  /*@ spec_public @*/ boolean pumpRunning = false;

  /*@ spec_public @*/ boolean systemActive = true;

  /*@ spec_public @*/ Environment env;

  //@ ensures this.env == env && pumpRunning == false && systemActive == true;
  public MinePump(Environment env) {
    super();
    this.env = env;
  }

  //@ ensures env == \old(env);
  //@ ensures \old(pumpRunning) && \old(env.waterLevel) == Environment.WaterLevelEnum.high ==> env.waterLevel == Environment.WaterLevelEnum.normal;
  //@ ensures \old(pumpRunning) && \old(env.waterLevel) == Environment.WaterLevelEnum.normal ==> env.waterLevel == Environment.WaterLevelEnum.low;
  //@ ensures \old(pumpRunning) && \old(env.waterLevel) == Environment.WaterLevelEnum.low ==> env.waterLevel == Environment.WaterLevelEnum.low;
  //@ ensures !\old(pumpRunning) ==> env.waterLevel == \old(env.waterLevel);
  //@ ensures \old(pumpRunning) && \old(env.methaneLevelCritical) ==> pumpRunning == false;
  //@ ensures !(\old(pumpRunning) && \old(env.methaneLevelCritical)) ==> pumpRunning == \old(pumpRunning);
  public void timeShift() {
    if (pumpRunning) env.lowerWaterLevel();
    if (systemActive) processEnvironment();
  }

  //@ ensures pumpRunning == \old(pumpRunning) && systemActive == \old(systemActive) && env == \old(env);
  private void processEnvironment__wrappee__base() {}

  //@ ensures \old(pumpRunning) && \old(env.methaneLevelCritical) ==> pumpRunning == false;
  //@ ensures !(\old(pumpRunning) && \old(env.methaneLevelCritical)) ==> pumpRunning == \old(pumpRunning);
  //@ ensures env == \old(env) && systemActive == \old(systemActive);
  public void processEnvironment() {
    if (pumpRunning && isMethaneAlarm()) {
      deactivatePump();
    } else {
      processEnvironment__wrappee__base();
    }
  }

  //@ ensures pumpRunning == true;
  void activatePump() {
    pumpRunning = true;
  }

  //@ ensures \result == pumpRunning;
  public boolean isPumpRunning() {
    return pumpRunning;
  }

  //@ ensures pumpRunning == false;
  void deactivatePump() {
    pumpRunning = false;
  }

  //@ ensures \result == env.methaneLevelCritical;
  boolean isMethaneAlarm() {
    return env.isMethaneLevelCritical();
  }

  //@ ensures \result != null;
  @Override
  public String toString() {
    return "Pump(System:"
        + (systemActive ? "On" : "Off")
        + ",Pump:"
        + (pumpRunning ? "On" : "Off")
        + ") "
        + env.toString();
  }

  //@ ensures \result == env;
  public Environment getEnv() {
    return env;
  }

  //@ ensures systemActive == false && pumpRunning == false;
  public void stopSystem() {
    if (pumpRunning) {
      deactivatePump();
    }
    assert !pumpRunning;
    systemActive = false;
  }

  //@ ensures systemActive == true && pumpRunning == \old(pumpRunning);
  public void startSystem() {
    assert !pumpRunning;
    systemActive = true;
  }

  //@ ensures \result == systemActive;
  public boolean isSystemActive() {
    return systemActive;
  }

}