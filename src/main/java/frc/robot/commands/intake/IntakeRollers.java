/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeRollers extends CommandBase {
  /**
   * Creates a new IntakeRollers.
   */
  Intake intake;
  private double maxRotations = 1.5;
  private double minRotations = 0.0;
  public IntakeRollers(Intake intake) {
    this.intake = intake;
    addRequirements(intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if (intake.getIntakeAngle() >= minRotations) {
    //   intake.setIntakeRoller(0.0, 0.0);
    // }
    // else if (intake.getIntakeAngle() >= minRotations + 0.5 && intake.getIntakeAngle() <= maxRotations - 1) {
    //   intake.setIntakeRoller(0.75, 0.75);
    // }
    // else if (intake.getIntakeAngle() >= maxRotations) {
    //   intake.setIntakeRoller(0.75, 0.75);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakeRoller(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}