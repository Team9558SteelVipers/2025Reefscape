// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.AngleUnit;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ArmAngleConstants;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.Slot1Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

public class AngleArmSubsystem extends SubsystemBase {
  TalonFX rightArmMotor;
  TalonFX leftArmMotor;
  CANcoder armCANcoder;
  double offset = .223877;
  
  final PIDController armPID = new PIDController(1, 0, 0);
  public AngleArmSubsystem() {

    
    armCANcoder = new CANcoder(ArmAngleConstants.armCANcoderPort);

    final MagnetSensorConfigs cancoderConfigs = new MagnetSensorConfigs()
      .withAbsoluteSensorDiscontinuityPoint(0.5)
      .withSensorDirection(SensorDirectionValue.Clockwise_Positive)
      .withMagnetOffset(-0.223145);

    armCANcoder.getConfigurator().apply(cancoderConfigs);

    armCANcoder.setPosition(armCANcoder.getAbsolutePosition().getValue());

    leftArmMotor = new TalonFX(ArmAngleConstants.leftArmMotorPort);
    rightArmMotor = new TalonFX(ArmAngleConstants.rightArmMotorPort);
    
    final Slot0Configs slot0Configs = new Slot0Configs()
      .withKP(ArmAngleConstants.kArmP)
      .withKI(ArmAngleConstants.kArmI)
      .withKD(ArmAngleConstants.kArmD)
      .withKG(ArmAngleConstants.kArmG)
      .withGravityType(GravityTypeValue.Arm_Cosine);

    final Slot1Configs slot1Configs = new Slot1Configs()
      .withKP(ArmAngleConstants.kArmClimbP)
      .withKI(ArmAngleConstants.kArmClimbI)
      .withKD(ArmAngleConstants.kArmClimbD)
      .withKG(ArmAngleConstants.kArmClimbG)
      .withGravityType(GravityTypeValue.Arm_Cosine);

    final FeedbackConfigs feedbackConfigs = new FeedbackConfigs()
      .withRemoteCANcoder(armCANcoder);

    final MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs()
      .withInverted(InvertedValue.CounterClockwise_Positive)
      .withNeutralMode(NeutralModeValue.Brake);

    final TalonFXConfiguration configuration = new TalonFXConfiguration()
      .withSlot0(slot0Configs)
      .withSlot1(slot1Configs)
      .withFeedback(feedbackConfigs)
      .withMotorOutput(motorOutputConfigs);

    leftArmMotor.getConfigurator().apply(configuration);

    rightArmMotor.setControl(new Follower(ArmAngleConstants.leftArmMotorPort, true));
  }

  public void setArmRotationStatic(double rotation){
    //double speed = armPID.calculate(armCANcoder.getPosition().getValueAsDouble(),offset+rotation);
    //leftArmMotor.setControl(new VoltageOut(speed * 1.0));
    //rightArmMotor.setControl(new Follower(ArmAngleConstants.leftArmMotorPort, true));
    leftArmMotor.setControl(new PositionVoltage(rotation).withSlot(0));
  }

  public void hang() {
    leftArmMotor.setControl(new PositionVoltage(ArmAngleConstants.armRotationHang).withSlot(1));
  }

  public double getArmEncoderRotation(){
    return armCANcoder.getAbsolutePosition().getValueAsDouble();
  }

  public void setArmSpeedDynamic(double speed) {
    leftArmMotor.set(ArmAngleConstants.damperSpeedValue*speed);
  }
  

  @Override
  public void periodic() {
    // SmartDashboard.putNumber("Arm CANcoder", armCANcoder.getPosition().getValueAsDouble());
    SmartDashboard.putNumber("Arm CANcoder", armCANcoder.getAbsolutePosition().getValueAsDouble());
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}