package frc.robot;

import java.sql.Driver;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.shooter.*;
import frc.robot.commands.climber.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.transportsystem.*;
import frc.robot.subsystems.*;

public class LaunchPadManager {

    double watchdogTimerSeconds = 1.0;
    final String tableName = "Launchpad";
    boolean btns[][] = new boolean[9][9];
    boolean presses[][] = new boolean[9][9];
    boolean lastBtns[][] = new boolean[9][9];

    boolean isConnected = false;
    double lastReceivedPing = 0;
    boolean lastPingValue = false;

    NetworkTableInstance nt;
    NetworkTable table;
    NetworkTableEntry pingEntryRio;
    NetworkTableEntry pingEntryLaunchpad;

    double setSpeed = 0.4;
    double setVoltage = 0.4 * 12;

    private static LaunchPadManager INSTANCE = new LaunchPadManager();

    LaunchPadManager() {
        nt = NetworkTableInstance.getDefault();
        table = nt.getTable(tableName);
        pingEntryRio = table.getEntry("pingValueRio");
        pingEntryLaunchpad = table.getEntry("pingValueLaunchpad");
    }

    public void periodicLoop() {
        // get ping value posted by launchpad
        boolean launchpadPingValue = pingEntryLaunchpad.getBoolean(false);

        // check for ping
        if(lastPingValue != launchpadPingValue) {
            lastPingValue = launchpadPingValue;
            lastReceivedPing = Timer.getFPGATimestamp();
        }
        
        // decide if connected or not
        if(Timer.getFPGATimestamp() - lastReceivedPing > watchdogTimerSeconds) {
            isConnected = false;
        }
        else isConnected = true;
        SmartDashboard.putBoolean("Launchpad Connection", isConnected);

        // send ping
        pingEntryRio.setBoolean(!pingEntryRio.getBoolean(false));

        // get buttons
        for(int r = 0; r < 9; r++) {
            for(int c = 0; c < 9; c++) {
                String key = r + ":" + c;
                lastBtns[r][c] = btns[r][c];
                btns[r][c] = isConnected && table.getEntry(key).getBoolean(false);
                presses[r][c] = false;
                if(btns[r][c] && !lastBtns[r][c]) presses[r][c] = true;
            }
        }

        // connection button test
        if(btns[8][8]) SmartDashboard.putBoolean("Launchpad Button Test", true);
        else SmartDashboard.putBoolean("Launchpad Button Test", false);
    }

    public void disabledLoop() {        
    }

    public void teleopLoop() {
        DriverStation.reportWarning("teleopLoop - begin",true);
        /*
        if (btns[0][0]) new InstantCommand(() -> {Drivetrain.getInstance().zeroGyro(); Drivetrain.getInstance().zeroEncoders();}, Drivetrain.getInstance()); //Reset Drivetrain

        if (btns[0][1]) new InstantCommand(() -> {Flywheel.getInstance().resetFlywheel();}, Flywheel.getInstance());// Reset Flywheel

        if (btns[0][2]) new InstantCommand(() -> {Intake.getInstance().zeroIntakePosition();}, Intake.getInstance()); // Reset Intake Encoder

        if (btns[1][0]) new IntakeHome(Intake.getInstance());// front left motor
        else if (!btns[1][0])  new InstantCommand(() -> { Intake.getInstance().setIntakeArm(0);}, Intake.getInstance());//

        if (btns[1][1]) new InstantCommand(() -> {Intake.getInstance().setIntakeArm(0);}, Intake.getInstance()); //Hard Stop Intake

        if (btns[1][2]) new InstantCommand(() -> {Flywheel.getInstance().setHoodFlyMotor(0);}, Flywheel.getInstance()); //Hard Stop Flywheel
        */
        try {
            if (btns[7][0]) new RunCommand(() -> {Drivetrain.getInstance().frontLeftDrive(setSpeed);}, Drivetrain.getInstance());// front left motor
            else if (!btns[7][0]) new InstantCommand(() -> {Drivetrain.getInstance().frontLeftDrive(0);}, Drivetrain.getInstance());// stop left right motor
        } catch (Exception e) {
            DriverStation.reportWarning("teleopLoop - 1",true);
        }

        if (btns[7][1]) new RunCommand(() -> {Drivetrain.getInstance().frontRightDrive(setSpeed);}, Drivetrain.getInstance());// back left motor
        else if(!btns[7][1]) new InstantCommand(() -> {Drivetrain.getInstance().frontRightDrive(0);}, Drivetrain.getInstance());// stop back left motor
        DriverStation.reportWarning("teleopLoop - 2",true);
        
        if (btns[7][4]) new RunCommand(() -> {Flywheel.getInstance().leftFlyDrive(setVoltage);}, Flywheel.getInstance());// left Flywheel motor
        else if(!btns[7][4]) new InstantCommand(() -> {Flywheel.getInstance().leftFlyDrive(0);}, Flywheel.getInstance());// stop left Flywheel motor
        DriverStation.reportWarning("teleopLoop - 3",true);
        
        if (btns[7][5]) new RunCommand(() -> {Flywheel.getInstance().rightFlyDrive(setVoltage);}, Flywheel.getInstance());// right Flywheel motor
        else if(!btns[7][5]) new InstantCommand(() -> {Flywheel.getInstance().rightFlyDrive(0);}, Flywheel.getInstance());// stop right Flywheel motor
        DriverStation.reportWarning("teleopLoop - 4",true);
        
        if (btns[7][6]) new RunCommand(() -> {Flywheel.getInstance().setHoodFlyMotor(setSpeed);}, Flywheel.getInstance());// hood Flywheel motor
        else if(!btns[7][6]) new InstantCommand(() -> {Flywheel.getInstance().setHoodFlyMotor(0);}, Flywheel.getInstance());// stop hood Flywheel motor
        DriverStation.reportWarning("teleopLoop - 5",true);

        if (btns[7][7]) new RunCommand(() -> {BallTower.getInstance().setTowerMotor(setSpeed);}, BallTower.getInstance());// Ball Tower motor
        else if(!btns[7][7]) new InstantCommand(() -> {BallTower.getInstance().setTowerMotor(0);}, BallTower.getInstance());// stop Ball Tower motor
        
        if (btns[8][0]) new RunCommand(() -> {
          BallHopper.getInstance().setTransportMotor(setSpeed);}, BallHopper.getInstance());// tower motor
        else if(!btns[8][0]) new InstantCommand(() -> {
          BallHopper.getInstance().setTransportMotor(0);}, BallHopper.getInstance());// stop tower motor
          DriverStation.reportWarning("teleopLoop - 6",true);

        if (btns[8][1]) new RunCommand(() -> {Intake.getInstance().topIntakeRoller(setSpeed);}, Intake.getInstance());// top Intake motor
        else if(!btns[8][1]) new InstantCommand(() -> {Intake.getInstance().topIntakeRoller(0);}, Intake.getInstance());// stop top Intake motor
        
        DriverStation.reportWarning("teleopLoop - 7",true);
        if (btns[8][2]) new RunCommand(() -> {Intake.getInstance().botIntakeRoller(setSpeed);}, Intake.getInstance());// bot Intake motor
        else if(!btns[8][2]) new InstantCommand(() -> {Intake.getInstance().botIntakeRoller(0);}, Intake.getInstance());// stop bot Intake motor
        
        DriverStation.reportWarning("teleopLoop - 8",true);
        if (btns[8][3]) new RunCommand(() -> {Intake.getInstance().setIntakeArm(setSpeed);}, Intake.getInstance());// arm intake motor
        else if(!btns[8][3]) new InstantCommand(() -> {Intake.getInstance().setIntakeArm(0);}, Intake.getInstance());// stop arm intake motor

        DriverStation.reportWarning("teleopLoop - 8a",true);
        if (btns[8][4]) new RunCommand(() -> {Climber.getInstance().setClimbMotor(setSpeed);}, Climber.getInstance());// climber motor
        else if(!btns[8][4]) new InstantCommand(() -> {Climber.getInstance().setClimbMotor(0);}, Climber.getInstance());// stop climber motor

        DriverStation.reportWarning("teleopLoop - 9",true);
        if (btns[8][5]) new RunCommand(() -> {Climber.getInstance().setTransverseMotor(setSpeed);}, Climber.getInstance());// transverse climber motor
        else if(!btns[8][5]) new InstantCommand(() -> {Climber.getInstance().setTransverseMotor(0);}, Climber.getInstance());// stop transverse climber motor

        // try {
            if (btns[8][8]) {
                DriverStation.reportWarning("Launchpad Button Works",true);
            }
        // } catch (Exception e) {
        //     DriverStation.reportError("", printTrace);
        // }
    }

    /*
    Mappings:
    0:0   --  while
    0:1   --  while
    0:2 arm reset zero  --  while
    0:4 cancel auto  --  function
    0:6 arm down group  --  when
    0:7 arm up group  --  when
    1:0 climber open  --  while
    1:1 climber close  --  while
    1:4 limelight auto  --  while
    1:5 limelight hold  --  while
    1:6 moth close  --  when
    1:7 moth open  --  when
    1:8 moth toggle  --  when
    2:4 outtake both  --  while
    2:5 intake both  --  while
    2:6 intake down  --  when
    2:7 intake up  --  when[]
    2:8 intake toggle  --  when
    3:5 arm set camera -- when
    3:6 arm manual down  --  while
    3:7 arm manual up  --  while
    3:8 arm set cargo  --  when
    4:8 arm set rocket  --  when
    5:8 arm set low  --  when
    7:0 front left motor
    7:1 back left motor
    7:2 front right motor
    7:3 back right motor
    7:4 left flywheel motor
    7:5 right flywheel motor
    7:6 hood flywheel motor
    7:7 tower motor
    8:0 transport motor
    8:1 top intake roller motor
    8:2 bot intake roller motor
    8:3 arm intake motor
    8:4 climber motor
    8:5 transversal motor
    */

    public static LaunchPadManager getInstance() {return INSTANCE;}
}