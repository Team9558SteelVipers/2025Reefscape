package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.Constants.DriveMotorConstants;
import frc.robot.subsystems.drive.Drive;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class LimelightSubsystem extends SubsystemBase {  //declares LimelightSubsystem as a class, and Public makes it accesible throughout the project
    private final Drive swerveDrive;  //references robots swerve drive system abnd allows limelight to provide movement commands
    private final PIDController turnController = new PIDController(0.02, 0, 0); //adjusts the robot rotation speed to minimize the horizontal offset (tx) between the target and robot’s center.
    private final PIDController driveController = new PIDController(0.1, 0, 0);    //controls back and forth movement of robot based on apriltag dstance, to ensure correct distance from target(apriltag)
    
    private final NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable(Constants.LIMELIGHT_TABLE);
    private final NetworkTableEntry tx = limelightTable.getEntry(Constants.LIMELIGHT_X_KEY);     //imports all variables from network table
    private final NetworkTableEntry ty = limelightTable.getEntry(Constants.LIMELIGHT_Y_KEY);
    private final NetworkTableEntry ta = limelightTable.getEntry(Constants.LIMELIGHT_AREA_KEY);
    private final NetworkTableEntry tv = limelightTable.getEntry(Constants.LIMELIGHT_VALID_TARGET_KEY);  

    public LimelightSubsystem(Drive swerveDrive) {   //takes 'Drive' object as an input and stores it
        this.swerveDrive = swerveDrive;   //alllows limelight to send movement commands to limelight
    }
        public double getxOffset() {          //exposes xOffset for command use.         //methods for limelightcommand location and axis values
            return tx.getDouble(0);
        }
        public double getyOffset() {         
            return ty.getDouble(0);
        }
        public double getArea() {         
            return ta.getDouble(0);
        }
        public boolean getvalidTarget() {      //dffferent because cant be number either true or false
            return tv.getDouble(0) > 0;
        }
        
    public void trackAprilTag() {             //this method reads tx, ty, ta and tv values from limelight and stores them locally
        double xOffset = tx.getDouble(0.0);   //gets x value from limelight (horizontal offset)
        double yOffset = ty.getDouble(0.0);     //gets y value from limelight (vertical offset)
        double area = ta.getDouble(0.0);        //gets distance value from limelight (deteines how close or far the apriltag is)
        boolean hasTarget = tv.getDouble(0.0) > 0;    //checks if theres is a valid target(apriltag) in the liemlight's view
    }
      
    @Override
    public void periodic() {  //automatically calls trackAprilTag() every cycle. @Override, overrides method from parent class
        trackAprilTag();
    }
}
