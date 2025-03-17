// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ServoArmConstants;
import edu.wpi.first.wpilibj.Servo;

public class ServoArmSubsystem extends SubsystemBase {

  public static Servo armServo;

  public ServoArmSubsystem() {
    armServo = new Servo(ServoArmConstants.armServoPort);
  }

  public void setServoAngle(double angle){
    armServo.setAngle(angle);
  }

  public void setServoAngleZero(){
    armServo.setAngle(0);
  }

  @Override
  public void periodic() {
    
  }
}
