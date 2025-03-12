// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;

import frc.robot.Constants.ArmAngleConstants;

public class AngleArmSubsystem extends SubsystemBase {
  TalonFX angleArmMotorRight;
  //TalonFX angleArmMotorLeft;

  TalonFXConfiguration pidConfiguration = new TalonFXConfiguration().withSlot0(new Slot0Configs()
    .withKP(ArmAngleConstants.kArmP)
    .withKI(ArmAngleConstants.kArmI)
    .withKD(ArmAngleConstants.kArmD)
    .withKG(ArmAngleConstants.kArmG)
      .withGravityType(GravityTypeValue.Arm_Cosine));

  public AngleArmSubsystem() {
    angleArmMotorRight = new TalonFX(ArmAngleConstants.rightArmMotorPort);
    //angleArmMotorLeft = new TalonFX(ArmAngleConstants.angleArmMotorLeftPort);

    angleArmMotorRight.getConfigurator().apply(pidConfiguration);
    //angleArmMotorLeft.getConfigurator().apply(pidConfiguration);
  }

  public void setArmPositionStatic(double position){
    angleArmMotorRight.setControl(new PositionVoltage(-position));
    //angleArmMotorLeft.setControl(new PositionVoltage(position));
  }

  public void setArmSpeedDynamic (double setSpeedRight, double setSpeedLeft){
    angleArmMotorRight.set(ArmAngleConstants.damperSpeedValue * (-setSpeedRight));
    // angleArmMotorLeft.set(ArmAngleConstants.speedDampenerValue*(setSpeedLeft));
  }
  

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }
}
