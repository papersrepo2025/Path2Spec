public class WBS {

  //@ public invariant 0 <= Nor_Pressure && Nor_Pressure <= 5;
  //@ public invariant 0 <= Alt_Pressure && Alt_Pressure <= 5;
  //@ public invariant Sys_Mode == 0 || Sys_Mode == 1;

  // Internal state
  private int WBS_Node_WBS_BSCU_SystemModeSelCmd_rlt_PRE;
  private int WBS_Node_WBS_BSCU_rlt_PRE1;
  private int WBS_Node_WBS_rlt_PRE2;

  // Outputs
  public int Nor_Pressure;
  public int Alt_Pressure;
  public int Sys_Mode;
  //@ ensures Nor_Pressure == 0 && Alt_Pressure == 0 && Sys_Mode == 0;
  public WBS() {
    WBS_Node_WBS_BSCU_SystemModeSelCmd_rlt_PRE = 0;
    WBS_Node_WBS_BSCU_rlt_PRE1 = 0;
    WBS_Node_WBS_rlt_PRE2 = 100;
    Nor_Pressure = 0;
    Alt_Pressure = 0;
    Sys_Mode = 0;
  }
  //@ requires true;
  //@ ensures 0 <= Nor_Pressure && Nor_Pressure <= 5;
  //@ ensures 0 <= Alt_Pressure && Alt_Pressure <= 5;
  //@ ensures Sys_Mode == 0 || Sys_Mode == 1;
  //@ also
  //@ ensures Skid ==> (Nor_Pressure == 0 && Alt_Pressure == 0);
  //@ also
  //@ requires true;
  //@ ensures (Skid && !AutoBrake && PedalPos != 0 && PedalPos != 1 && PedalPos != 2 && PedalPos != 3 && PedalPos != 4) ==> (Nor_Pressure == 0 && Alt_Pressure == 0);
  //@ ensures Sys_Mode == 0 || Sys_Mode == 1;
  //@ also
  //@ ensures Sys_Mode == 0 || Sys_Mode == 1;
  //@ ensures 0 <= Nor_Pressure && Nor_Pressure <= 5;
  //@ ensures 0 <= Alt_Pressure && Alt_Pressure <= 5;
  //@ ensures Skid ==> (Nor_Pressure == 0 && Alt_Pressure == 0);
  //@ ensures PedalPos == 0 ==> Alt_Pressure == 0;
  //@ also
  //@ requires AutoBrake == false;
  //@ requires Skid == false;
  //@ requires PedalPos < 0 || PedalPos > 4;
  //@ ensures Nor_Pressure == 0;
  //@ ensures Alt_Pressure == 0;
  //@ ensures 0 <= Sys_Mode && Sys_Mode <= 1;
  //@ also
  //@ ensures (Skid && AutoBrake && PedalPos == 2) ==> (Nor_Pressure == 0 && Alt_Pressure == 0);
  //@ ensures Sys_Mode == 0 || Sys_Mode == 1;
  public void update(int PedalPos, boolean AutoBrake, boolean Skid) {
    int WBS_Node_WBS_AS_MeterValve_Switch; // 4
    int WBS_Node_WBS_AccumulatorValve_Switch; // 5
    int WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch; // 6
    boolean WBS_Node_WBS_BSCU_Command_Is_Normal_Relational_Operator; // 7
    int WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1; // 8
    int WBS_Node_WBS_BSCU_Command_Switch; // 9
    boolean WBS_Node_WBS_BSCU_SystemModeSelCmd_Logical_Operator6; // 10
    int WBS_Node_WBS_BSCU_SystemModeSelCmd_Unit_Delay; // 11
    int WBS_Node_WBS_BSCU_Switch2; // 12
    int WBS_Node_WBS_BSCU_Switch3; // 13
    int WBS_Node_WBS_BSCU_Unit_Delay1; // 14
    int WBS_Node_WBS_Green_Pump_IsolationValve_Switch; // 15
    int WBS_Node_WBS_SelectorValve_Switch; // 16
    int WBS_Node_WBS_SelectorValve_Switch1; // 17
    int WBS_Node_WBS_Unit_Delay2; // 18

    WBS_Node_WBS_Unit_Delay2 = WBS_Node_WBS_rlt_PRE2;
    WBS_Node_WBS_BSCU_Unit_Delay1 = WBS_Node_WBS_BSCU_rlt_PRE1;
    WBS_Node_WBS_BSCU_SystemModeSelCmd_Unit_Delay = WBS_Node_WBS_BSCU_SystemModeSelCmd_rlt_PRE;

    WBS_Node_WBS_BSCU_Command_Is_Normal_Relational_Operator =
        (WBS_Node_WBS_BSCU_SystemModeSelCmd_Unit_Delay == 0);

    if ((PedalPos == 0)) {
      WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 = 0;
    } else {
      if ((PedalPos == 1)) {
        WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 = 1;
      } else {
        if ((PedalPos == 2)) {
          WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 = 2;
        } else {
          if ((PedalPos == 3)) {
            WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 = 3;
          } else {
            if ((PedalPos == 4)) {
              WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 = 4;
            } else {
              WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 = 0;
            }
          }
        }
      }
    }

    if ((AutoBrake && WBS_Node_WBS_BSCU_Command_Is_Normal_Relational_Operator)) {
      WBS_Node_WBS_BSCU_Command_Switch = 1;
    } else {
      WBS_Node_WBS_BSCU_Command_Switch = 0;
    }

    WBS_Node_WBS_BSCU_SystemModeSelCmd_Logical_Operator6 =
        ((((!(WBS_Node_WBS_BSCU_Unit_Delay1 == 0)) && (WBS_Node_WBS_Unit_Delay2 <= 0))
                && WBS_Node_WBS_BSCU_Command_Is_Normal_Relational_Operator)
            || (!WBS_Node_WBS_BSCU_Command_Is_Normal_Relational_Operator));

    if (WBS_Node_WBS_BSCU_SystemModeSelCmd_Logical_Operator6) {
      if (Skid) WBS_Node_WBS_BSCU_Switch3 = 0;
      else WBS_Node_WBS_BSCU_Switch3 = 4;
    } else {
      WBS_Node_WBS_BSCU_Switch3 = 4;
    }

    if (WBS_Node_WBS_BSCU_SystemModeSelCmd_Logical_Operator6) {
      WBS_Node_WBS_Green_Pump_IsolationValve_Switch = 0;
    } else {
      WBS_Node_WBS_Green_Pump_IsolationValve_Switch = 5;
    }

    if ((WBS_Node_WBS_Green_Pump_IsolationValve_Switch >= 1)) {
      WBS_Node_WBS_SelectorValve_Switch1 = 0;
    } else {
      WBS_Node_WBS_SelectorValve_Switch1 = 5;
    }

    if ((!WBS_Node_WBS_BSCU_SystemModeSelCmd_Logical_Operator6)) {
      WBS_Node_WBS_AccumulatorValve_Switch = 0;
    } else {
      if ((WBS_Node_WBS_SelectorValve_Switch1 >= 1)) {
        WBS_Node_WBS_AccumulatorValve_Switch = WBS_Node_WBS_SelectorValve_Switch1;
      } else {
        WBS_Node_WBS_AccumulatorValve_Switch = 5;
      }
    }

    if ((WBS_Node_WBS_BSCU_Switch3 == 0)) {
      WBS_Node_WBS_AS_MeterValve_Switch = 0;
    } else {
      if ((WBS_Node_WBS_BSCU_Switch3 == 1)) {
        WBS_Node_WBS_AS_MeterValve_Switch = (WBS_Node_WBS_AccumulatorValve_Switch / 4);
      } else {
        if ((WBS_Node_WBS_BSCU_Switch3 == 2)) {
          WBS_Node_WBS_AS_MeterValve_Switch = (WBS_Node_WBS_AccumulatorValve_Switch / 2);
        } else {
          if ((WBS_Node_WBS_BSCU_Switch3 == 3)) {
            WBS_Node_WBS_AS_MeterValve_Switch = ((WBS_Node_WBS_AccumulatorValve_Switch / 4) * 3);
          } else {
            if ((WBS_Node_WBS_BSCU_Switch3 == 4)) {
              WBS_Node_WBS_AS_MeterValve_Switch = WBS_Node_WBS_AccumulatorValve_Switch;
            } else {
              WBS_Node_WBS_AS_MeterValve_Switch = 0;
            }
          }
        }
      }
    }

    if (Skid) {
      WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch = 0;
    } else {
      WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch =
          (WBS_Node_WBS_BSCU_Command_Switch + WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1);
    }

    if (WBS_Node_WBS_BSCU_SystemModeSelCmd_Logical_Operator6) {
      Sys_Mode = 1;
    } else {
      Sys_Mode = 0;
    }

    if (WBS_Node_WBS_BSCU_SystemModeSelCmd_Logical_Operator6) {
      WBS_Node_WBS_BSCU_Switch2 = 0;
    } else {
      if (((WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch >= 0)
          && (WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch < 1))) {
        WBS_Node_WBS_BSCU_Switch2 = 0;
      } else {
        if (((WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch >= 1)
            && (WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch < 2))) {
          WBS_Node_WBS_BSCU_Switch2 = 1;
        } else {
          if (((WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch >= 2)
              && (WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch < 3))) {
            WBS_Node_WBS_BSCU_Switch2 = 2;
          } else {
            if (((WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch >= 3)
                && (WBS_Node_WBS_BSCU_Command_AntiSkidCommand_Normal_Switch < 4))) {
              WBS_Node_WBS_BSCU_Switch2 = 3;
            } else {
              WBS_Node_WBS_BSCU_Switch2 = 4;
            }
          }
        }
      }
    }

    if ((WBS_Node_WBS_Green_Pump_IsolationValve_Switch >= 1)) {
      WBS_Node_WBS_SelectorValve_Switch = WBS_Node_WBS_Green_Pump_IsolationValve_Switch;
    } else {
      WBS_Node_WBS_SelectorValve_Switch = 0;
    }

    if ((WBS_Node_WBS_BSCU_Switch2 == 0)) {
      Nor_Pressure = 0;
    } else {
      if ((WBS_Node_WBS_BSCU_Switch2 == 1)) {
        Nor_Pressure = (WBS_Node_WBS_SelectorValve_Switch / 4);
      } else {
        if ((WBS_Node_WBS_BSCU_Switch2 == 2)) {
          Nor_Pressure = (WBS_Node_WBS_SelectorValve_Switch / 2);
        } else {
          if ((WBS_Node_WBS_BSCU_Switch2 == 3)) {
            Nor_Pressure = ((WBS_Node_WBS_SelectorValve_Switch / 4) * 3);
          } else {
            if ((WBS_Node_WBS_BSCU_Switch2 == 4)) {
              Nor_Pressure = WBS_Node_WBS_SelectorValve_Switch;
            } else {
              Nor_Pressure = 0;
            }
          }
        }
      }
    }

    if ((WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 == 0)) {
      Alt_Pressure = 0;
    } else {
      if ((WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 == 1)) {
        Alt_Pressure = (WBS_Node_WBS_AS_MeterValve_Switch / 4);
      } else {
        if ((WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 == 2)) {
          Alt_Pressure = (WBS_Node_WBS_AS_MeterValve_Switch / 2);
        } else {
          if ((WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 == 3)) {
            Alt_Pressure = ((WBS_Node_WBS_AS_MeterValve_Switch / 4) * 3);
          } else {
            if ((WBS_Node_WBS_BSCU_Command_PedalCommand_Switch1 == 4)) {
              Alt_Pressure = WBS_Node_WBS_AS_MeterValve_Switch;
            } else {
              Alt_Pressure = 0;
            }
          }
        }
      }
    }

    WBS_Node_WBS_rlt_PRE2 = Nor_Pressure;

    WBS_Node_WBS_BSCU_rlt_PRE1 = WBS_Node_WBS_BSCU_Switch2;

    WBS_Node_WBS_BSCU_SystemModeSelCmd_rlt_PRE = Sys_Mode;
  }
}
