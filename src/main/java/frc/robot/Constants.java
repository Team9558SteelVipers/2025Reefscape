// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

public final class Constants {
  public static final Mode simMode = Mode.SIM;
  public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;
  
  public static final double intakeSpeed = -1.0;
  public static final double outtakeSpeed = 0.85;
  public static final double intakeAlgae = 0.9;
  public static final double outtakeAlgae = -0.9;
  public static final double outtakeSpeedAuto = 0.5;

  public static final double pieceDetectCurrentThreshold = 15.0;
  public static final double pieceDetectMinimumRunTime = 0.25;
  public static final double pieceDetectMaximumRunTime = 1.5;

  // public static final double intakeIdleSpeed = -0.1;

  public static class ServoArmConstants{
    public static final int armServoPort = 0;

    public static final double angle0 = 0;
    public static final double angle180 = 180;
  }

  public static final int kTopIntakePort = 2;
  public static final int kBottomIntakePort = 5;
  
  

  public final class DriveMotorConstants{
    public static final int MotorPortBL = 0;
    public static final int MotorPortBR = 0;
    public static final int MotorPortFL = 0;
    public static final int MotorPortFR = 0;

    public static final double damperSpeedValue = 0.25;
    public static final double amplifySpeedValue = 1.0;
    public static final double defaultSpeedValue = 0.75;
  }


  public final class JoLMotorConstants{
    public static final int JoLMotorPort = 14;
    public static final double JoLSpeed = 0.2;
    public static final double JoLEngagedAngle = 2870;
    public static final double JoLDisengagedAngle = 2262;
    public static final double JolRestAngle = 3350;
    public static final double JoLAngleTolerance = 200;
    public static final double JoLResistanceCurrentThreshold = 8.0;

    public static final double JoLkP = 0.75;
    public static final double JoLkI = 0.0;
    public static final double JoLkD = 0.0;
  }

  public static class ArmAngleConstants {
    public static final int leftArmMotorPort = 22;
    public static final int rightArmMotorPort = 21;

    public static final double armRotationSpeed = 0.6;

    public static final int armCANcoderPort = 0;

    public static final double armRotationTolerance = 0.05;

    public static final double armRotationIntakeCoral = 0.774170; //-0.226562;
    public static final double armRotationOuttakeCoral = 0.673828; //-0.320068;
    public static final double armRotationRemoveAlgae = 0.480225; //-0.527100;
    public static final double armRotationProcessAlgae = 0.705; //-0.291748;
    public static final double armRotationClimb = 0.580078; //-0.419189;
    public static final double armRotationHang = 0.674072;
    public static final double armRotationRemoveStand = 0.58;
    public static final double armRotationStart = 0.622803;
    public static final double armRotationStation = 0.61;
    public static final double armRotationL2 = 0.63;



    public static final double kArmP = 30.0;
    public static final double kArmI = 0;
    public static final double kArmD = 2.0;
    public static final double kArmG = 0;
    
    public static final double kArmClimbP = 40.0;
    public static final double kArmClimbI = 0;
    public static final double kArmClimbD = 0;
    public static final double kArmClimbG = 0;

    public static final double damperSpeedValue = 0.25;
  }

  public static final int LIMELIGHT_PIPELINE = 0;
  public static final int LIMELIGHT_CAM_MODE = 0;
  public static final int LIMELIGHT_LED_MODE = 3;
  public static final int kDriverControllerPort = 0;
  public static final String LIMELIGHT_X_KEY = "tx"; // Horizontal offset from crosshair
  public static final String LIMELIGHT_TABLE = "limelight";
  public static final String LIMELIGHT_Y_KEY = "ty"; // Vertical offset from crosshair
  public static final String LIMELIGHT_AREA_KEY = "ta"; // Target area (0 to 100%)
  public static final String LIMELIGHT_VALID_TARGET_KEY = "tv"; // Whether a target is detected (0 or 1)
  public static final String LIMELIGHT_LATENCY_KEY = "tl"; // Latency of camera
  public static final double LIMELIGHT_HEIGHT = 24.0; // Height of the Limelight camera from the floor (in inches)
  public static final double TARGET_HEIGHT = 98.0; // Height of the target from the floor (in inches)
  public static final double LIMELIGHT_DISTANCE_CONSTANT = 0.25;
  public static final double LIMELIGHT_MOUNT_ANGLE = 30.0; // Mounting angle of Limelight (degrees)
  public static final double LIMELIGHT_FWD_SCALER = 1;  //multiplier for DrivePID controller
  public static final double LIMELIGHT_STRAFE_SCALER = 1;  //ultiplier for StrafrPID controller
  public static final double LIMELIGHT_DISTANCE_TOLERANCE =0.1;  //
  public static enum Mode {
    /** Running on a real robot. */
    REAL,

    /** Running a physics simulator. */
    SIM,

    /** Replaying from a log file. */
    REPLAY
  }
}
