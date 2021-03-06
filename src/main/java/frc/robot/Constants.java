package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;

public final class Constants {
    public static final class DrivetrainConstants {
        public static final int FRONT_RIGHT_MOTOR = 2;
        public static final int BACK_RIGHT_MOTOR = 3;

        public static final int FRONT_LEFT_MOTOR = 4;
        public static final int BACK_LEFT_MOTOR = 5;

        public static final double DRIVE_BASE_WIDTH = Units.inchesToMeters(29.5); // Meters
        public static final double WHEEL_DIAMETER = Units.inchesToMeters(6.0d); // Meters

        public static final double DISTANCE_PER_REVOLUTION = WHEEL_DIAMETER * Math.PI;
        public static final double PULSES_PER_REVOLUTION = 42 * 5.6;
        public static final double DISTANCE_PER_PULSE = DISTANCE_PER_REVOLUTION / PULSES_PER_REVOLUTION;
        public static final double SECONDS_PER_MINUTE = 60.0d;

        public static final DifferentialDriveKinematics KINEMATICS =
            new DifferentialDriveKinematics(DRIVE_BASE_WIDTH);

        public static final double THROTTLE_DEADBAND = 0.075d;
        public static final double TWIST_DEADBAND = 0.075d;
        public static final double MAX_OUTPUT = 1.0d;

        public static final double MAX_SPEED = 0.0d; // Meters per second
        public static final double MAX_ACCELERATION = 0.0d; // Meters per second^2
    }

    public static final class FlywheelConstants {
        public static final int LEFT_FLY_MOTOR = 7;
        public static final int RIGHT_FLY_MOTOR = 8;
        public static final int HOOD_FLY_MOTOR = 6;

        public static final double flykP = 0.0018;
        public static final double flykD = 0.004;
    }

    public static final class RamseteConstants {
        public static final double B = 0.0d;
        public static final double ZETA = 0.0d;

        public static final double KS = 0.0d; // Volts
    }

    public static final class MPConstants {
        public static final double DEFAULT_KV = 0.076; // 1/13
        public static final double DEFAULT_KA = 0.035;
        public static final double DEFAULT_KH = 0;
        public static final double DEFAULT_KP = 0.3;
        public static final double DEFAULT_KD = 0;
    }

    public static final class TowerConstants {
        public static final int TOWER_MOTOR = 12;
        public static final int LEVEL_ONE = 0;
        public static final int LEVEL_TWO = 1;
        public static final int LEVEL_THREE = 2;
    }

    public static final class ClimberConstants {
        public static final int CLIMB_MOTOR = 9;
        public static final int TRANSVERSE_CLIMB_MOTOR = 10;
    }

    public static final class IntakeConstants {
        public static final int ARM_INTAKE_MOTOR = 13;
        public static final int TOP_INTAKE_MOTOR = 14;
        public static final int BOT_INTAKE_MOTOR = 15;
    }

    public static final class TransportConstants {
        public static final int HOPPER_MOTOR = 11;
    }
}