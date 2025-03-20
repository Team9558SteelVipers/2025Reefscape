package frc.robot.subsystems;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.InNoutConstants;

public class CoralOutSubsystem extends SubsystemBase {

    public CoralOutSubsystem() {}
  
    TalonFX motorLFORTOP = new TalonFX(InNoutConstants.motorLFORTOP);
    TalonFX motorRFORBOTTOM = new TalonFX(InNoutConstants.motorRFORBOTTOM);
  
    public void getCoralOutSpeed(double Leftspeed, double Rightspeed) {
      motorLFORTOP.set(Leftspeed);
      motorRFORBOTTOM.set(-Rightspeed);
    }
      public void setPosition(double position){
        motorLFORTOP.setControl(new PositionVoltage(position));
        motorRFORBOTTOM.setControl(new PositionVoltage(position));
      }
}