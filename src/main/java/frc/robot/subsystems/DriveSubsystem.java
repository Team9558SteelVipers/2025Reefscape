package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
// import com.pathplanner.lib.config.RobotConfig;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase{
    TalonFX FrontR;
    TalonFX BackR;
    TalonFX FrontL;
    TalonFX BackL;
 
    // TalonFXConfiguration pidconfig = new TalonFXConfiguration().withSlot0(new Slot0Configs().withKP(1).withKI(0).withKD(0));
    public DriveSubsystem() {
        FrontR = new TalonFX(Constants.MotorPortFR);
        BackR = new TalonFX(Constants.MotorPortBR);
        FrontL = new TalonFX(Constants.MotorPortFL);
        BackL = new TalonFX(Constants.MotorPortBL);

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
   
   
    // RobotConfig config;
    // try{ 
    //     config = RobotConfig.fromGUISettings();
    // } catch (Exception e) { 
    //     e.printStackTrace();
    // }

    // AutoBuilder.configure(
    //     this::getPose,
    //     this::resetPose,
    //     this::getRobotRelativeSpeeds,
    //     (speeds, feedforwards) -> driveRobotRelative(speeds),
    //     new PPHolonomicDriveController(
    //         new PIDConstants(5.0,0.0,0.0),
    //         new PIDConstants(5.0,0.0,0.0)
    //     ),
    //     config,
    //     () -> {



    //         var alliance = DriverStation.getAlliance();
    //         if(alliance.isPresent()){ 
    //             return alliance.get() == DriverStation.Alliance.red;
    //         }
    //         return false;
    //     },
    //     this 
    // );
    
}