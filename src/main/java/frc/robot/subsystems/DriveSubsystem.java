package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
// import com.pathplanner.lib.auto.AutoBuilder;
// import com.pathplanner.lib.config.PIDConstants;
// import com.pathplanner.lib.config.RobotConfig;
// import com.pathplanner.lib.controllers.PPHolonomicDriveController;

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
        FrontR = new TalonFX(Constants.DriveMotorConstants.MotorPortFR);
        BackR = new TalonFX(Constants.DriveMotorConstants.MotorPortBR);
        FrontL = new TalonFX(Constants.DriveMotorConstants.MotorPortFL);
        BackL = new TalonFX(Constants.DriveMotorConstants.MotorPortBL);
    }

        // Motor1.getConfigurator().apply(pidconfig);
        // Motor2.getConfigurator().apply(pidconfig);
        // Motor3.getConfigurator().apply(pidconfig);
        // Motor4.getConfigurator().apply(pidconfig);

        // Load the RobotConfig from the GUI settings. You should probably
        // store this in your Constants file
    //     RobotConfig config;
    //     try{
    //         config = RobotConfig.fromGUISettings();
    //     } catch (Exception e) {
    //         throw new RuntimeException(e); //cease application
    //     }

    //     // Configure AutoBuilder last
    //     AutoBuilder.configure(
    //             this::getPose, // Robot pose supplier
    //             this::resetPose, // Method to reset odometry (will be called if your auto has a starting pose)
    //             this::getRobotRelativeSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
    //             (speeds, feedforwards) -> driveRobotRelative(speeds), // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds. Also optionally outputs individual module feedforwards
    //             new PPHolonomicDriveController( // PPHolonomicController is the built in path following controller for holonomic drive trains
    //                     new PIDConstants(5.0, 0.0, 0.0), // Translation PID constants
    //                     new PIDConstants(5.0, 0.0, 0.0) // Rotation PID constants
    //             ),
    //             config, // The robot configuration
    //             () -> {
    //                 // Boolean supplier that controls when the path will be mirrored for the red alliance
    //                 // This will flip the path being followed to the red side of the field.
    //                 // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

    //                 var alliance = DriverStation.getAlliance();
    //                 if (alliance.isPresent()) {
    //                     return alliance.get() == DriverStation.Alliance.Red;
    //                 }
    //                 return false;
    //             },
    //             this // Reference to this subsystem to set requirements
    //     );
    // }
    // public Pose2d getPose (){
    //     return null; // dummy value
    // }
    // public void resetPose (Pose2d currentpose){}
    // public ChassisSpeeds getRobotRelativeSpeeds (){
    //     return null; // dummy value
    // }
    // public void driveRobotRelative(ChassisSpeeds speeds){}

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