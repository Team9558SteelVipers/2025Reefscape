package frc.robot.subsystems;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.InNoutConstants;


public class AlgaeOutSubsystem extends SubsystemBase {

    public AlgaeOutSubsystem() {}
     
    TalonFX motorLFORTOP = new TalonFX(InNoutConstants.motorLFORTOP);
    
    public void AlgaeOutSpeed(double Leftspeed, double Rightspeed) {

        motorLFORTOP.set(Leftspeed);
       
    }
    public void setPosition(double position){
        
         motorLFORTOP.setControl(new PositionVoltage(position));

        }
  }