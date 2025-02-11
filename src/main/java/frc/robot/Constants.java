// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

  public static class OperatorConstants {
    public static final int kOperatorControllerPort = 0;
  }

  public static class AngleArmConstants {
    public static final int angleArmMotorRightPort = 1;
    public static final int angleArmMotorLeftPort = 1;

    public static final double positionFloor = 1;
    public static final double positionClimb = 1;
    public static final double positionStage1 = 1;
    public static final double positionStage2 = 1;

    public static final double kAngleArmP = 1.0;
    public static final double kAngleArmI = 0.0;
    public static final double kAngleArmD = 0.0;
    public static final double kAngleArmG = 0.3;

    public static final double speedDampenerValue = 0.25;
  }

}
