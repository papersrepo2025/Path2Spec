import java.util.Random;

final class Verifier {
  //@ public normal_behavior;
//@ requires true;
//@ assignable \nothing;
//@ ensures Integer.MIN_VALUE <= \result && \result <= Integer.MAX_VALUE;
  public static int nondetInt() {
    return new Random().nextInt();
  }
}

public class AlarmOutputs {
  public int isAudioDisabled = Verifier.nondetInt();
  public int notificationMessage = Verifier.nondetInt();
  public int audioNotificationCommand = Verifier.nondetInt();
  public int highestLevelAlarm = Verifier.nondetInt();
  public int logMessageId = Verifier.nondetInt();
}