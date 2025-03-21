// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ArmAngleConstants;
import frc.robot.Constants.ServoArmConstants;
import frc.robot.commands.ServoArmCommand;
import frc.robot.subsystems.ServoArmSubsystem;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIOPigeon2;
import frc.robot.subsystems.drive.ModuleIOTalonFX;
import frc.robot.commands.AngleArmDynamicCommand;
import frc.robot.commands.AngleArmStaticCommand;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.JawsofLifeCommand;
import frc.robot.commands.IntakeOuttakeCommand;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.AngleArmSubsystem;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.InTakeOutTakesubsystem;
import frc.robot.subsystems.JawsOfLifeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotContainer {
  private final Drive drive;
  public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

  // Initialized Controllers
  private final CommandXboxController m_operatorController =
      new CommandXboxController(0);
  private final CommandXboxController m_driveController =
      new CommandXboxController(1); 
  //private final SendableChooser<Command> autoChooser;

  // Initialized Subsystems
  private final JawsOfLifeSubsystem m_JoLsubsystem = new JawsOfLifeSubsystem();
  private final InTakeOutTakesubsystem m_InOuttakeSubsystem = new InTakeOutTakesubsystem();
  private final AngleArmSubsystem m_angleArmSubsystem = new AngleArmSubsystem();
  private final ServoArmSubsystem m_servoArmSubsystem = new ServoArmSubsystem();
  
// // Initialized Commands
  
  // Jol Section
  private final JawsofLifeCommand m_JawsOfLifeOpen = new JawsofLifeCommand(m_JoLsubsystem, Constants.JoLMotorConstants.JoLSpeed);
  private final JawsofLifeCommand m_JawsOfLifeClose = new JawsofLifeCommand(m_JoLsubsystem, -Constants.JoLMotorConstants.JoLSpeed);

  // Arm Section
  private final AngleArmStaticCommand m_positionFloor = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationIntakeCoral);
  private final AngleArmStaticCommand m_positionStage1 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationOuttakeCoral);
  private final AngleArmStaticCommand m_positionStage2 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationRemoveAlgae);
  private final AngleArmStaticCommand m_positionClimb = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationClimb);

  // private  AngleArmDynamicCommand setAngleArmDynamic = new AngleArmDynamicCommand(m_angleArmSubsystem, this::dpadVerticalControl);

  // Servo Section
  private ServoArmCommand unlockArmMotors = new ServoArmCommand(m_servoArmSubsystem, ServoArmConstants.angle180);
  private ServoArmCommand lockArmMotors = new ServoArmCommand(m_servoArmSubsystem, ServoArmConstants.angle0);

  // Intake Outtake Section
  public IntakeOuttakeCommand m_SpeedCommand = new IntakeOuttakeCommand(Constants.intakeSpeed, Constants.intakeSpeed, m_InOuttakeSubsystem);
  public IntakeOuttakeCommand m_ReverseSpeed = new IntakeOuttakeCommand(-Constants.intakeSpeed, -Constants.intakeSpeed, m_InOuttakeSubsystem);
  public IntakeOuttakeCommand m_AlgaeFloor = new IntakeOuttakeCommand(Constants.intakeSpeed,0, m_InOuttakeSubsystem);
  public IntakeOuttakeCommand m_AlgaeStage = new IntakeOuttakeCommand(Constants.intakeSpeed,0, m_InOuttakeSubsystem);
  

  private final NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    drive =
            new Drive(
                new GyroIOPigeon2(),
                new ModuleIOTalonFX(TunerConstants.FrontLeft),
                new ModuleIOTalonFX(TunerConstants.FrontRight),
                new ModuleIOTalonFX(TunerConstants.BackLeft),
                new ModuleIOTalonFX(TunerConstants.BackRight));
    // Configure the trigger bindings


    NamedCommands.registerCommand("coralOuttake", new IntakeOuttakeCommand(-0.5, -0.5,m_InOuttakeSubsystem));
    NamedCommands.registerCommand("angleArmStage1",new AngleArmStaticCommand(m_angleArmSubsystem,  ArmAngleConstants.armRotationOuttakeCoral));
// NAMED COMMANDS:
    // NamedCommands.registerCommand();

    // autoChooser = AutoBuilder.buildAutoChooser();

    //     // Set up SysId routines
    //     autoChooser.addOption(
    //         "Drive Wheel Radius Characterization", DriveCommands.wheelRadiusCharacterization(drive));
    //     autoChooser.addOption(
    //         "Drive Simple FF Characterization", DriveCommands.feedforwardCharacterization(drive));
    //     autoChooser.addOption(
    //         "Drive SysId (Quasistatic Forward)",
    //         drive.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
    //     autoChooser.addOption(
    //         "Drive SysId (Quasistatic Reverse)",
    //         drive.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
    //     autoChooser.addOption(
    //         "Drive SysId (Dynamic Forward)", drive.sysIdDynamic(SysIdRoutine.Direction.kForward));
    //     autoChooser.addOption(
    //         "Drive SysId (Dynamic Reverse)", drive.sysIdDynamic(SysIdRoutine.Direction.kReverse));
    configureBindings();
  }

  private void configureBindings() {

    m_operatorController.a().onTrue(m_positionFloor);
    m_operatorController.x().onTrue(m_positionStage1);
    m_operatorController.y().onTrue(m_positionStage2);
    // m_operatorController.b().onTrue(m_positionClimb);

    m_operatorController.leftBumper().whileTrue(m_ReverseSpeed);
    m_operatorController.rightBumper().whileTrue(m_SpeedCommand);

    // m_operatorController.back().onTrue(new InstantCommand(this::displayLimelightData));

    // m_operatorController.leftStick().onTrue(Commands.sequence(m_positionClimb, lockArmMotors));
    // m_operatorController.rightStick().onTrue(unlockArmMotors);

    // m_angleArmSubsystem.setDefaultCommand(setAngleArmDynamic);
    m_driveController.rightTrigger().whileTrue(m_JawsOfLifeOpen);
    m_driveController.leftTrigger().whileTrue(m_JawsOfLifeClose);

    m_driveController.b().onTrue(lockArmMotors);
    
    m_driveController.x().onTrue(unlockArmMotors);
    // m_driveController.y().onTrue(m_positionClimb);
    // m_driveController.b().onTrue(m_AlgaeFloor);

    drive.setDefaultCommand(
            DriveCommands.joystickDrive(
                drive,
                () -> m_driveController.getLeftY(),
                () -> m_driveController.getLeftX(),
                () -> -m_driveController.getRightX()));
    
    //m_InOuttakeSubsystem.setDefaultCommand(m_constantSpeed);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new PathPlannerAuto("start top - reef");
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
    
  private double dpadVerticalControl() {
    if (m_operatorController.povUp().getAsBoolean()) {
      return 1;
    } else if (m_operatorController.povDown().getAsBoolean()) {
      return -1;
    } else {
      return 0;
    }
  }

  
}
