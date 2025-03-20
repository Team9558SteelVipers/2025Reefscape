// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmAngleConstants;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.GravityTypeValue;

public class AngleArmSubsystem extends SubsystemBase {
  TalonFX rightArmMotor;
  TalonFX leftArmMotor;

  TalonFXConfiguration pidConfiguration = new TalonFXConfiguration().withSlot0(new Slot0Configs()
    .withKP(ArmAngleConstants.kArmP)
    .withKI(ArmAngleConstants.kArmI)
    .withKD(ArmAngleConstants.kArmD)
    .withKG(ArmAngleConstants.kArmG)
      .withGravityType(GravityTypeValue.Arm_Cosine));

  public AngleArmSubsystem() {
    rightArmMotor = new TalonFX(ArmAngleConstants.rightArmMotorPort);
    leftArmMotor = new TalonFX(ArmAngleConstants.leftArmMotorPort);

    rightArmMotor.getConfigurator().apply(pidConfiguration);
    leftArmMotor.getConfigurator().apply(pidConfiguration);
  }

  public void setArmPositionStatic(double position){
    rightArmMotor.setControl(new PositionVoltage(-position));
    leftArmMotor.setControl(new PositionVoltage(position));
  }

  public void setArmSpeedDynamic (double speed){
    rightArmMotor.set(ArmAngleConstants.damperSpeedValue*(-speed));
    leftArmMotor.set(ArmAngleConstants.damperSpeedValue*(speed));
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}