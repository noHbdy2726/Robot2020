package frc.lib.motors;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.SpeedController;

public class Motors {

  public static final MotorConfiguration Neo = new Neo();
  public static final MotorConfiguration Neo550 = new Neo550();
  public static final MotorConfiguration Snowblower = new Snowblower();
  public static final MotorConfiguration Bag = new Bag();
  public static final MotorConfiguration MiniCIM = new MiniCIM();
}
