import java.util.Random;

final class Verifier {
  /*@ public normal_behavior
    @   ensures true;
    @   assignable \nothing;
    @*/
  /*@ pure @*/ public static int nondetInt() {
    return new Random().nextInt();
  }
}

public class AlarmOutputs {
  public /*@ spec_public @*/ int isAudioDisabled = Verifier.nondetInt();
  public /*@ spec_public @*/ int notificationMessage = Verifier.nondetInt();
  public /*@ spec_public @*/ int audioNotificationCommand = Verifier.nondetInt();
  public /*@ spec_public @*/ int highestLevelAlarm = Verifier.nondetInt();
  public /*@ spec_public @*/ int logMessageId = Verifier.nondetInt();
}