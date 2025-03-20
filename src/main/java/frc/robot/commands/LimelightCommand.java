package frc.robot.commands;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class LimelightCommand extends Command {
    private final NetworkTable limelightTable;

    // Constructor to initialize the NetworkTable
    public LimelightCommand() {
        limelightTable = NetworkTableInstance.getDefault().getTable(Constants.LIMELIGHT_TABLE);

        // No subsystem is required for this command, so we don't call addRequirements()
    }

    // This method is called once when the command starts
    @Override
    public void initialize() {
        System.out.println("Limelight Command Initialized");
    }

    // This method is called repeatedly when the command is scheduled to run
    @Override
    public void execute() {
        // Fetch values from the Limelight NetworkTable
        double limelightX = getLimelightValue(Constants.LIMELIGHT_X_KEY);      
        double limelightY = getLimelightValue(Constants.LIMELIGHT_Y_KEY);
        double limelightArea = getLimelightValue(Constants.LIMELIGHT_AREA_KEY);
        double validTarget = getLimelightValue(Constants.LIMELIGHT_VALID_TARGET_KEY);

        // Update Shuffleboard with Limelight data
        Shuffleboard.getTab("Limelight").add("X", limelightX);
        Shuffleboard.getTab("Limelight").add("Y", limelightY);
        Shuffleboard.getTab("Limelight").add("Area", limelightArea);
        Shuffleboard.getTab("Limelight").add("Valid Target", validTarget);

        // Optionally, add behavior to control the robot based on the Limelight data
        if (validTarget > 0.5) {
            // Example: robot control logic based on Limelight data (turning)
            double turnSpeed = limelightX * 0.1; // Scale the value of limelightX for turning
            // You can add code here to control the robot’s movement (e.g., drive subsystem)
        }
    }

    // This method returns true when the command should finish
    @Override
    public boolean isFinished() {
        // This command runs continuously, so it will return false until interrupted
        return false;
    }

    // This method is called once when the command ends
    @Override
    public void end(boolean interrupted) {
        // Cleanup logic if needed, such as stopping the robot or resetting systems
        System.out.println("Limelight Command Ended");
    }

    // Helper method to get Limelight values from the NetworkTable
    private double getLimelightValue(String key) {
        NetworkTableEntry entry = limelightTable.getEntry(key);
        return entry.getDouble(0.0); // Return 0.0 if no value is found
    }
}
