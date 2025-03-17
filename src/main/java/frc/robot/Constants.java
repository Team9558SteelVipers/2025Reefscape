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
  public static final int kTopIntakePort = 0;
  public static final int kBottomIntakePort = 1;
  
  public final class CoralMotorConstants{
    public static final int coralMotorPort = 0;
  }

  public final class DriveMotorConstants{
    public static final int MotorPortBL = 0;
    public static final int MotorPortBR = 0;
    public static final int MotorPortFL = 0;
    public static final int MotorPortFR = 0;
  }

  public final class PIDMotorConstants{
    public static final int ExampleMotorPort = 0;
  }

  public final class JoLMotorConstants{
    public static final int JoLMotorPort = 0;
  }

  public static class ArmAngleConstants {
    public static final int leftArmMotorPort = 6;
    public static final int rightArmMotorPort = 2;

    public static final double positionFloor = 0.5;
    public static final double positionStage1 = 0.75; 
    public static final double positionStage2 = 0.85;
    public static final double positionClimb = 1.0;

    public static final double kArmP = 1.0;
    public static final double kArmI = 0;
    public static final double kArmD = 0;
    public static final double kArmG = 0;

    public static final double damperSpeedValue = 0.5;
  }
  
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
