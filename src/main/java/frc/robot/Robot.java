package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  private XboxController driverController; // Xbox controller for driver input
  private NetworkTable limelightTable; // NetworkTable instance for Limelight data

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  @Override
    public void robotInit() {
        // Initialize the Xbox controller on the specified port
        driverController = new XboxController(Constants.kDriverControllerPort);
        
        // Connect to the Limelight NetworkTable
        limelightTable = NetworkTableInstance.getDefault().getTable(Constants.LIMELIGHT_TABLE);
    }
  
  @Override
  public void robotPeriodic() {
      // Run the scheduler for command-based programming
      CommandScheduler.getInstance().run();
      
      // Continuously update Limelight data on the SmartDashboard
      updateLimelightData();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
      // Check if the 'A' button is pressed on the controller
      if (driverController.getAButton()) {
          // Retrieve Limelight data
          double targetOffsetX = getLimelightValue(Constants.LIMELIGHT_X_KEY);
          double targetOffsetY = getLimelightValue(Constants.LIMELIGHT_Y_KEY);
          double targetArea = getLimelightValue(Constants.LIMELIGHT_AREA_KEY);
          double targetValid = getLimelightValue(Constants.LIMELIGHT_VALID_TARGET_KEY);
          
          // Display Limelight data on the SmartDashboard
          SmartDashboard.putNumber("Target Offset X", targetOffsetX);
          SmartDashboard.putNumber("Target Offset Y", targetOffsetY);
          SmartDashboard.putNumber("Target Area", targetArea);
          SmartDashboard.putNumber("Target Valid", targetValid);
      }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

    private void updateLimelightData() {
        // Fetch and display Limelight values on the SmartDashboard
        SmartDashboard.putNumber("Limelight X", getLimelightValue(Constants.LIMELIGHT_X_KEY)); //displays x value data 
        SmartDashboard.putNumber("Limelight Y", getLimelightValue(Constants.LIMELIGHT_Y_KEY));  //displays y value data 
        SmartDashboard.putNumber("Limelight Area", getLimelightValue(Constants.LIMELIGHT_AREA_KEY));  
        SmartDashboard.putNumber("Limelight Valid Target", getLimelightValue(Constants.LIMELIGHT_VALID_TARGET_KEY)); //displays wether camera has a valid target value on smartdashboard
    }

    private double getLimelightValue(String key) {
        // Retrieve a value from the Limelight NetworkTable using the given key
        NetworkTableEntry entry = limelightTable.getEntry(key);
        return entry.getDouble(0.0); // Return the value, or 0.0 if not found
    }
}
