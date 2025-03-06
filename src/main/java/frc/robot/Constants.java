// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public class MotorConstants{
  public static final int ExampleMotorPort = 0;
  public static final int MotorPortFR = 1;
  public static final int MotorPortBR = 10;
  public static final int MotorPortFL = 6;
  public static final int MotorPortBL = 3;
  public static final int coralMotorPort = 2;
  }

  public class AngleArmConstants {
    public static final double kAngleArmP = 0.0;
    public static final double kAngleArmI = 0.0;
    public static final double kAngleArmD = 0.0;
    public static final double kAngleArmG = 0.0;
    public static final int angleArmMotorRightPort = 0;
    public static final double speedDampenerValue = 1.0;
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
}
