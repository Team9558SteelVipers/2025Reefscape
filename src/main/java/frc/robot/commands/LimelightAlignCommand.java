package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.Constants;

public class LimelightAlignCommand extends Command {  //all valrables declared in LimelightAlignCommand costructor
    private final Drive drive;    //declares drive variable, must be initalized in constructor
    private final LimelightSubsystem limelight;  //declares limelight variable
    private final LimelightSubsystem m_LimelightSubsystem;  //   declared limelight subsystem variable 
    
    private static final double LIMELIGHT_X_KP = 0.02; // Tuning constant that scales correction speed of robot.  A proportional control constant that adjusts the rotation speed based on error
    private static final double MIN_COMMAND = 0.05; //Ensures correction of small errors (e.g. helps with precise movement).

    public LimelightAlignCommand(Drive drive, LimelightSubsystem limelightSubsystem) {  //takes 
        m_LimelightSubsystem = limelightSubsystem; //limelightsubsystem variable initalized here //save to class variable (m_Limelightsubsystem assigned to limelightsubsystem)  
        this.drive = drive;                        //drive, and limelight variables initalized here  //class level variable //drive (without .this) is the parameter of the constructor //Assigns the parameter drive (passed into the constructor) to the class-level variable drive.
        this.limelight = limelightSubsystem;
        addRequirements(drive, limelight);    //Ensures no other commands use drive or limelight while this command runs.
    }

    
    @Override
    public void execute() {
        double limelightX = m_LimelightSubsystem.getxOffset();    //gets location and axis values from limelightsubsystem
        double limelightY = m_LimelightSubsystem.getyOffset();
        double limelightArea = m_LimelightSubsystem.getArea();
        boolean validTarget = m_LimelightSubsystem.getvalidTarget();
        if (limelight.getvalidTarget()) {
            double xOffset = limelight.getxOffset();
            double turnCommand = -xOffset * LIMELIGHT_X_KP;

            // Apply a minimum power to ensure the robot moves
            if (Math.abs(turnCommand) < MIN_COMMAND) {    //if turn command is too small it is boosted here //also ensures correction of even small offset from center of apriltag
                turnCommand = Math.copySign(MIN_COMMAND, turnCommand);
            }

            // Rotate the robot while keeping it stationary in X and Y
            drive.runVelocity(new ChassisSpeeds(0, 0, turnCommand)); //makes sure robot only rotates, and doesnt drive foward or backward
        } else {
            drive.stop(); // No target? Stop movement.
        }
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop(); // Stop when command ends
    }

    @Override
    public boolean isFinished() {
        return limelight.getvalidTarget() && Math.abs(limelight.getxOffset()) < 1.0; // Stop when robot aligned
    }
}
