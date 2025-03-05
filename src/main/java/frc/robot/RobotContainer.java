package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CoralOuttake;
import frc.robot.commands.DriveDynamic;
import frc.robot.commands.DriveStatic;
import frc.robot.commands.PIDExamplePositionCommand;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PIDExample;roller;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CoralOuttake;
import frc.robot.commands.DriveDynamic;
import frc.robot.commands.DriveStatic;
import frc.robot.commands.PIDExamplePositionCommand;
import frc.robot.subsystems.CoralSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PIDExample;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
//import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class RobotContainer {
      // initializing subsystems
  private final DriveSubsystem m_Subsystem = new DriveSubsystem();
  private final CoralSubsystem m_CoralSubsystem = new CoralSubsystem();
  private final PIDExample m_PIDSubsystem = new PIDExample();
  // initializing the controller
  CommandXboxController xcontroller = new CommandXboxController(0); 

  private final XboxController driverController = new XboxController(Constants.kDriverControllerPort);
  private final NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  // initializing commands
  private DriveDynamic m_dynCommand = new DriveDynamic(m_Subsystem, xcontroller :: getRightY, xcontroller :: getLeftY);
  private DriveStatic m_StaticCommandSpeed25 = new DriveStatic(m_Subsystem,0.25);
  private DriveStatic m_StaticCommandSpeed50 = new DriveStatic(m_Subsystem,0.5);
  private CoralOuttake m_CoralOuttake = new CoralOuttake(m_CoralSubsystem);
  private PIDExamplePositionCommand m_Position = new PIDExamplePositionCommand(m_PIDSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

    // Setting controller bindings to start commands
  private void configureBindings() {
    xcontroller.a().whileTrue(m_StaticCommandSpeed25);
    xcontroller.x().toggleOnTrue(m_CoralOuttake);
    xcontroller.b().whileTrue(m_Position);
    xcontroller.rightTrigger().whileTrue(m_StaticCommandSpeed50);
    m_Subsystem.setDefaultCommand(m_dynCommand);

    new JoystickButton(driverController, XboxController.Button.kA.value)
    .onTrue(new InstantCommand(this::displayLimelightData));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }

    private void displayLimelightData() {
        SmartDashboard.putNumber("Limelight X", getLimelightValue(Constants.LIMELIGHT_X_KEY));
        SmartDashboard.putNumber("Limelight Y", getLimelightValue(Constants.LIMELIGHT_Y_KEY));
        SmartDashboard.putNumber("Limelight Area", getLimelightValue(Constants.LIMELIGHT_AREA_KEY));
        SmartDashboard.putNumber("Limelight Valid Target", getLimelightValue(Constants.LIMELIGHT_VALID_TARGET_KEY));
    }

    private double getLimelightValue(String key) {
        return limelightTable.getEntry(key).getDouble(0.0);
    }
}
