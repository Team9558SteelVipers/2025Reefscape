// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class ServoArmConstants{
    public static final int armServoPort = 0;

    public static final double angle0 = 0;
    public static final double angle180 = 180;
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


}
