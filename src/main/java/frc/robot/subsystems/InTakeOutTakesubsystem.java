// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class InTakeOutTakesubsystem extends SubsystemBase {
  TalonSRX motorTop;
  TalonSRX motorBot;
  /** Creates a new ExampleSubsystem. */
  public InTakeOutTakesubsystem() {
  motorTop = new TalonSRX(Constants.kTopIntakePort);
  motorBot = new TalonSRX(Constants.kBottomIntakePort);
  }
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public void setMotorSpeeds(double speedTop, double speedBottom) {
      motorTop.set(ControlMode.PercentOutput, speedTop );
      motorBot.set(ControlMode.PercentOutput, speedBottom );

      
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.

          /* one-time action goes here */
        
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
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
