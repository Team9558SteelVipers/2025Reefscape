package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;



import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase{
    
    // Initializing the Motors
    TalonFX FrontR;
    TalonFX BackR;
    TalonFX FrontL;
    TalonFX BackL;
 
    // TalonFXConfiguration pidconfig = new TalonFXConfiguration().withSlot0(new Slot0Configs().withKP(1).withKI(0).withKD(0));

    // Initializing the susbystem and setting motor variables to ports
    public DriveSubsystem() {
        FrontR = new TalonFX(Constants.MotorConstants.MotorPortFR);
        BackR = new TalonFX(Constants.MotorConstants.MotorPortBR);
        FrontL = new TalonFX(Constants.MotorConstants.MotorPortFL);
        BackL = new TalonFX(Constants.MotorConstants.MotorPortBL);

        // Motor1.getConfigurator().apply(pidconfig);
        // Motor2.getConfigurator().apply(pidconfig);
        // Motor3.getConfigurator().apply(pidconfig);
        // Motor4.getConfigurator().apply(pidconfig);
    }

    public void setMotorSpeed(double speed) {
        FrontR.set(speed);
        BackR.set(speed);
        FrontL.set(-speed);
        BackL.set(-speed);
    }


    public void setMotorSpeedDyn(double speedright, double speedleft) {
        FrontR.set(speedright);
        BackR.set(speedright);
        FrontL.set(-speedleft);
        BackL.set(-speedleft);
    }
    

}