package frc.robot.commands;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTable;     //Handles Limelight's data storage and sharing.
import edu.wpi.first.networktables.NetworkTableEntry;   //Represents a single value in the table.
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;    //Provides access to limelights data.
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.drive.Drive;

public class LimelightCommand extends Command {    //all variables in here must be declared in the public LimelightComannd() constructor.
    private final LimelightSubsystem m_LimelightSubsystem; //declares limelightsubsystem valiable, must be initalized in this constructor. LimlightCommand()
    private final Drive m_swerveDrive; //declares swervedrive variable, also has to be initalized in this constructor. LimelightCommand()

    private final PIDController driveController;    //declares drive contoller variable, must be initialized to have a value, done in this constructor.
    private final PIDController strafeController;    //declares turn Controller variable, also must be initalized in LimelightCommand() costructor.

    // Constructor to initialize the NetworkTable
    public LimelightCommand(LimelightSubsystem limelightSubsystem, Drive swerveDrive) { //limelight subsystem initalized here
        m_LimelightSubsystem = limelightSubsystem;  //save to class variable (m_Limelightsubsystem assigned to limelightsubsystem)   
        m_swerveDrive = swerveDrive;  //swerveDrvie initalized here  //save to class variable (m_swerveDrive assigned to swerveDrive)
        driveController = new PIDController(0,0,0); //driveController initalized here
        strafeController = new PIDController(0, 0, 0); //turnController initialzed here
        
        // No subsystem is required for this command, so we don't call addRequirements() because this command doesnt no require exclusive access to the drivetrain.
    }

    // This method is called(runs) once when the command starts
    @Override
    public void initialize() {
        System.out.println("Limelight Command Initialized");  //prints to indicate method activation 
    }

    // This method is called repeatedly when the command is scheduled to run 
    @Override
    public void execute() {
        double limelightX = m_LimelightSubsystem.getxOffset();    //gets location and axis values from limelightsubsystem //aka pulls data from limelightsubsystem.java
        double limelightY = m_LimelightSubsystem.getyOffset();
        double limelightArea = m_LimelightSubsystem.getArea();
        boolean validTarget = m_LimelightSubsystem.getvalidTarget();

        

        // Update Shuffleboard with Limelight data  //Sends all live data to Shuffleboard.
        Shuffleboard.getTab("Limelight").add("X", limelightX);
        Shuffleboard.getTab("Limelight").add("Y", limelightY);
        Shuffleboard.getTab("Limelight").add("Area", limelightArea);
        Shuffleboard.getTab("Limelight").add("Valid Target", validTarget);

        if (validTarget) {   //if apriltag is detected
            double forwardSpeed = Constants.LIMELIGHT_FWD_SCALER * -driveController.calculate(limelightArea, Constants.LIMELIGHT_DISTANCE_CONSTANT);  //uses PID controller (drivecontroller) to adjust robots foward movement. area(how big target appears) calculates how far robot is from apriltag. -(nevative sign) ensures correct direction(moving toward target if too far). LIMELIGHT_FWD_SCALER value multiplies by driveController. aka it scales speed appropriately. Same concept for strafeController.
            double strafeSpeed = Constants.LIMELIGHT_STRAFE_SCALER * -strafeController.calculate(limelightX, 0);  // uses another PID controller(strafecontroller) to adjust horizontal position. xOffset is limelights horizontal error(how far left/right target is) // adjusts the robot’s strafe movement to center the AprilTag within the limelights crosshair.

            m_swerveDrive.runVelocity(new ChassisSpeeds(forwardSpeed, strafeSpeed, 0)); //comands drivetrain to move.  ChassisSpeeds(forwardSpeed) is back and forth movement.  rotationSpeed: no rotation currently.
        } else {
            m_swerveDrive.stop(); //stops drivetrain if no target is detected (stops all movement)
        }
    }


    

    // This method returns true when the command should finish
    @Override
    public boolean isFinished() {
        // This command runs continuously, so it will return false until interrupted
        
        return MathUtil.isNear(m_LimelightSubsystem.getArea(),Constants.LIMELIGHT_DISTANCE_CONSTANT,Constants.LIMELIGHT_DISTANCE_TOLERANCE); //determining when command is finished and when criteria in this method are met. //Compares getArea() (distance to target) with LIMELIGHT_DISTANCE_CONSTANT (ideal distance). //Uses isNear() to check if it's within LIMELIGHT_DISTANCE_TOLERANCE
    }

    // This method is called once when the command ends
    @Override
    public void end(boolean interrupted) {
        // Cleanup logic if needed, such as stopping the robot or resetting systems
        System.out.println("Limelight Command Ended");
        
    }
    
}


    

