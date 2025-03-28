// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ServoArmConstants;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoArmSubsystem extends SubsystemBase {

  public static Servo armServo;
  public boolean on = false;
  public SuppliedValueWidget<String> onOffWidget;

  public ServoArmSubsystem() {
    armServo = new Servo(ServoArmConstants.armServoPort);
    SmartDashboard.putBoolean("Servo", false);
  }

  public void setServoAngle(double angle){
    armServo.setAngle(angle);
    on = angle < 180;
    SmartDashboard.putBoolean("Servo", on);
  }

  public void setServoAngleZero(){
    armServo.setAngle(0);
    on = true;
    SmartDashboard.putBoolean("Servo", on);
  }

  @Override
  public void periodic() {

  }
}
