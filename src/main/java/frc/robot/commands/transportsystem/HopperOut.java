package frc.robot.commands.transportsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallHopper;


public class HopperOut extends CommandBase {
  private final BallHopper ballTransport;

  public HopperOut(BallHopper ballTransport) {
    this.ballTransport = ballTransport;
    addRequirements(ballTransport);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    ballTransport.outHopper(0.8);
  }

  @Override
  public boolean isFinished() {
    // TODO: Make this return true when this Command no longer needs to run execute()
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    ballTransport.setHopperMotor(0);
  }
}
