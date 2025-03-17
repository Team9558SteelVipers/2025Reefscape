// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.InNoutConstants;

public class OutSubsystem extends SubsystemBase {

  public OutSubsystem() {}

  TalonFX motorONLEFT = new TalonFX(InNoutConstants.motorONLEFT);
  TalonFX motorONRIGHT = new TalonFX(InNoutConstants.motorONRIGHT);

  public void getInNoutSpeed(double Leftspeed, double Rightspeed) {
    motorONLEFT.set(Leftspeed);
    motorONRIGHT.set(-Rightspeed);
  }
    public void setPosition(double position){
      motorONLEFT.setControl(new PositionVoltage(position));
      motorONRIGHT.setControl(new PositionVoltage(position));
    }



  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
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
