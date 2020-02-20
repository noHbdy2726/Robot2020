package frc.robot.commands.transportsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallTransport;


public class TransportIn extends CommandBase {
  private final BallTransport ballTransport;

  public TransportIn(BallTransport ballTransport) {
    this.ballTransport = ballTransport;
    addRequirements(ballTransport);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    ballTransport.setTransportMotor(0.5);
  }

  @Override
  public boolean isFinished() {
    // TODO: Make this return true when this Command no longer needs to run execute()
    return false;
  }

  @Override
  public void end(boolean interrupted) {

  }
}
