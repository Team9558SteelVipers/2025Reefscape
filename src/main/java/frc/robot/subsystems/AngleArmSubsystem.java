// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmAngleConstants;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class AngleArmSubsystem extends SubsystemBase {
  TalonFX rightArmMotor;
  TalonFX leftArmMotor;
  CANcoder armCANcoder;

  public AngleArmSubsystem() {
    armCANcoder = new CANcoder(ArmAngleConstants.armCANcoderPort);

    leftArmMotor = new TalonFX(ArmAngleConstants.leftArmMotorPort);
    rightArmMotor = new TalonFX(ArmAngleConstants.rightArmMotorPort);
    
    final Slot0Configs slot0Configs = new Slot0Configs()
      .withKP(ArmAngleConstants.kArmP)
      .withKI(ArmAngleConstants.kArmI)
      .withKD(ArmAngleConstants.kArmD)
      .withKG(ArmAngleConstants.kArmG)
      .withGravityType(GravityTypeValue.Arm_Cosine);

    final FeedbackConfigs feedbackConfigs = new FeedbackConfigs()
      .withRemoteCANcoder(armCANcoder);

    final MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs()
      .withInverted(InvertedValue.Clockwise_Positive)
      .withNeutralMode(NeutralModeValue.Brake);

    final TalonFXConfiguration configuration = new TalonFXConfiguration()
      .withSlot0(slot0Configs)
      .withFeedback(feedbackConfigs)
      .withMotorOutput(motorOutputConfigs);

    leftArmMotor.getConfigurator().apply(configuration);

    rightArmMotor.setControl(new Follower(ArmAngleConstants.leftArmMotorPort, true));
  }

  public void setArmRotationStatic(double rotation){
    
  }

  public double getArmEncoderRotation(){
    return armCANcoder.getAbsolutePosition().getValueAsDouble();
  }

  public void setArmSpeedDynamic (double speed){
    leftArmMotor.set(ArmAngleConstants.damperSpeedValue*(speed));
  }
  

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Arm CANcoder", armCANcoder.getPosition().getValueAsDouble());
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}