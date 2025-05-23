// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ArmAngleConstants;
import frc.robot.Constants.DriveMotorConstants;
import frc.robot.Constants.ServoArmConstants;
import frc.robot.commands.ServoArmCommand;
import frc.robot.subsystems.ServoArmSubsystem;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIOPigeon2;
import frc.robot.subsystems.drive.ModuleIOTalonFX;
import frc.robot.commands.AngleArmConstantSpeedCommand;
import frc.robot.commands.AngleArmConstantSpeedCommand;
import frc.robot.commands.AngleArmDynamicCommand;
import frc.robot.commands.AngleArmPositionCommand;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.JawsofLifeCommand;
import frc.robot.commands.JawsofLifePositionCommand;
import frc.robot.commands.ResetPoseAngleCommand;
import frc.robot.commands.IntakeOuttakeCommand;
import frc.robot.commands.IntakeWithPieceDetectCommand;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.AngleArmSubsystem;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.InTakeOutTakesubsystem;
import frc.robot.subsystems.JawsOfLifeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotContainer {
  private final Drive drive;
  public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();
  public final Command resetPoseAngleCommand;

  // Initialized Controllers
  private final CommandXboxController m_operatorController =
      new CommandXboxController(0);
  private final CommandXboxController m_driveController =
      new CommandXboxController(1); 

  // Initialized Subsystems
  private final JawsOfLifeSubsystem m_JoLsubsystem = new JawsOfLifeSubsystem();
  private final InTakeOutTakesubsystem m_InOuttakeSubsystem = new InTakeOutTakesubsystem();
  private final AngleArmSubsystem m_angleArmSubsystem = new AngleArmSubsystem();
  private final ServoArmSubsystem m_servoArmSubsystem = new ServoArmSubsystem();
  

  private final SendableChooser<Command> autoChooser = new SendableChooser<Command>(); 
    
// Initialized Commands

  

  // Jol Section
  private final JawsofLifeCommand m_JawsOfLifeOpen = new JawsofLifeCommand(m_JoLsubsystem, Constants.JoLMotorConstants.JoLSpeed, this::rumbleOperatorControllerIfEngaged);
  private final JawsofLifeCommand m_JawsOfLifeClose = new JawsofLifeCommand(m_JoLsubsystem, -Constants.JoLMotorConstants.JoLSpeed, this::rumbleOperatorControllerIfEngaged);
  //private final JawsofLifePositionCommand m_JawsOfLifeClosePosition = new JawsofLifePositionCommand(m_JoLsubsystem, Constants.JoLMotorConstants.JoLDisengagedAngle, this::rumbleOperatorControllerIfEngaged);
  //private final JawsofLifePositionCommand m_JawsOfLifeOpenPosition = new JawsofLifePositionCommand(m_JoLsubsystem, Constants.JoLMotorConstants.JoLEngagedAngle, this::rumbleOperatorControllerIfEngaged);

  // Arm Section
  private final AngleArmPositionCommand m_positionFloor = new AngleArmPositionCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationIntakeCoral);
  private final AngleArmPositionCommand m_positionStage1 = new AngleArmPositionCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationOuttakeCoral);
  private final AngleArmPositionCommand m_positionRemoveAlgae = new AngleArmPositionCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationRemoveAlgae);
  private final AngleArmPositionCommand m_positionClimb = new AngleArmPositionCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationClimb);
  private final AngleArmPositionCommand m_positionHang = new AngleArmPositionCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationHang);
  private final AngleArmPositionCommand m_AlgaeProcess = new AngleArmPositionCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationProcessAlgae);
  private final AngleArmPositionCommand m_positionStarting = new AngleArmPositionCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationStart);
  private final AngleArmPositionCommand m_positionL2 = new AngleArmPositionCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationL2);
  private final AngleArmPositionCommand m_positionStation = new AngleArmPositionCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationStation);
  
  //private final AngleArmDynamicCommand setAngleArmDynamic = new AngleArmDynamicCommand(m_angleArmSubsystem, this::dpadVerticalControl);
  private final AngleArmConstantSpeedCommand armUp = new AngleArmConstantSpeedCommand(m_angleArmSubsystem, ArmAngleConstants.armRotationSpeed);
  private final AngleArmConstantSpeedCommand armDown = new AngleArmConstantSpeedCommand(m_angleArmSubsystem, -ArmAngleConstants.armRotationSpeed);
  private final AngleArmConstantSpeedCommand stop = new AngleArmConstantSpeedCommand(m_angleArmSubsystem, 0);

  // Servo Section
  private ServoArmCommand unlockArmMotors = new ServoArmCommand(m_servoArmSubsystem, ServoArmConstants.angle180);
  private ServoArmCommand lockArmMotors = new ServoArmCommand(m_servoArmSubsystem, ServoArmConstants.angle0);

  // Intake Outtake Section
  public IntakeOuttakeCommand m_OuttakeCommand = new IntakeOuttakeCommand(Constants.outtakeSpeed, Constants.outtakeSpeed, m_InOuttakeSubsystem);
  public IntakeOuttakeCommand m_IntakeCommand = new IntakeOuttakeCommand(Constants.intakeSpeed, Constants.intakeSpeed, m_InOuttakeSubsystem);
  public IntakeOuttakeCommand m_AlgaeFloorIntake = new IntakeOuttakeCommand(0, Constants.intakeAlgae, m_InOuttakeSubsystem);
  public IntakeOuttakeCommand m_AlgaeProcessorOuttake = new IntakeOuttakeCommand(0, Constants.outtakeAlgae, m_InOuttakeSubsystem);
  // public IntakeOuttakeCommand m_idleSpeed = new IntakeOuttakeCommand(Constants.intakeIdleSpeed, Constants.intakeIdleSpeed, m_InOuttakeSubsystem);
  public IntakeWithPieceDetectCommand m_IntakeWithPieceDetectCommand = new IntakeWithPieceDetectCommand(m_InOuttakeSubsystem, Constants.intakeSpeed, Constants.pieceDetectMinimumRunTime, Constants.pieceDetectMaximumRunTime);

  private final NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
    NamedCommands.registerCommand("angleArmremoveStand", new AngleArmPositionCommand(m_angleArmSubsystem,  ArmAngleConstants.armRotationRemoveStand));
    NamedCommands.registerCommand("angleArmStart", new AngleArmPositionCommand(m_angleArmSubsystem,  ArmAngleConstants.armRotationStart));
    NamedCommands.registerCommand("coralouttake", new IntakeOuttakeCommand(Constants.outtakeSpeedAuto,Constants.outtakeSpeedAuto,m_InOuttakeSubsystem).withTimeout(0.3));
    NamedCommands.registerCommand("outtakeremovealgae", new IntakeOuttakeCommand(Constants.removeAlgaeSpeed,Constants.removeAlgaeSpeed,m_InOuttakeSubsystem).withTimeout(1));
    // NamedCommands.registerCommand("coralintake", new IntakeOuttakeCommand(Constants.intakeSpeed,Constants.intakeSpeed,m_InOuttakeSubsystem).withTimeout(2));
    NamedCommands.registerCommand("coralintake", m_IntakeWithPieceDetectCommand);
    NamedCommands.registerCommand("angleArmStage1",new AngleArmPositionCommand(m_angleArmSubsystem,  ArmAngleConstants.armRotationOuttakeCoral));
    NamedCommands.registerCommand("angleArmL2",new AngleArmPositionCommand(m_angleArmSubsystem,  ArmAngleConstants.armRotationL2));
    NamedCommands.registerCommand("angleArmStation",new AngleArmPositionCommand(m_angleArmSubsystem,  ArmAngleConstants.armRotationStation));

    drive =
            new Drive(
                new GyroIOPigeon2(),
                new ModuleIOTalonFX(TunerConstants.FrontLeft),
                new ModuleIOTalonFX(TunerConstants.FrontRight),
                new ModuleIOTalonFX(TunerConstants.BackLeft),
                new ModuleIOTalonFX(TunerConstants.BackRight));
    // Configure the trigger bindings

    resetPoseAngleCommand = new ResetPoseAngleCommand(drive);

    autoChooser.addOption("middle auto", new PathPlannerAuto("middle auto"));
    autoChooser.addOption("Right 3 Piece", new PathPlannerAuto("Right 3 Piece"));
    autoChooser.addOption("Left 3 Piece", new PathPlannerAuto("Left 3 Piece"));
    autoChooser.addOption("Left L2 Auto", new PathPlannerAuto("Left L2 Auto"));    
    autoChooser.addOption("Right First Piece L2", new PathPlannerAuto("Right First Piece L2")); 
    autoChooser.addOption("Left First Piece L2", new PathPlannerAuto("Left First Piece L2")); 
    autoChooser.addOption("Middle Left Auto", new PathPlannerAuto("Middle Left Auto")); 
    autoChooser.addOption("Middle Right Auto", new PathPlannerAuto("Middle Right Auto")); 
    autoChooser.addOption("LTest", new PathPlannerAuto("Test Auto 2"));




    SmartDashboard.putData("Auto Chooser", autoChooser);

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

    // m_operatorController.start().onTrue(m_IntakeWithPieceDetectCommand.withTimeout(2));

    m_driveController.povUp().onTrue(resetPoseAngleCommand);

    m_operatorController.a().onTrue(m_positionFloor);
    m_operatorController.b().onTrue(m_positionStage1);
    m_operatorController.y().onTrue(m_positionRemoveAlgae);
    m_operatorController.x().onTrue(m_AlgaeProcess);
    m_operatorController.povLeft().onTrue(m_positionStation);
    m_operatorController.povRight().onTrue(m_positionL2);

     m_operatorController.leftBumper().whileTrue(m_OuttakeCommand); 
     m_operatorController.rightBumper().whileTrue(m_IntakeCommand); 

    m_operatorController.rightTrigger().whileTrue(m_AlgaeFloorIntake);
    m_operatorController.leftTrigger().whileTrue(m_AlgaeProcessorOuttake);

    // m_operatorController.back().onTrue(new InstantCommand(this::displayLimelightData));

    // m_operatorController.leftStick().onTrue(Commands.sequence(m_positionClimb, lockArmMotors));
    // m_operatorController.rightStick().onTrue(unlockArmMotors);

    //m_angleArmSubsystem.setDefaultCommand(setAngleArmDynamic);
    m_operatorController.povUp().whileTrue(armUp);
    m_operatorController.povDown().whileTrue(armDown);

    m_driveController.rightTrigger().whileTrue(m_JawsOfLifeOpen);
    m_driveController.leftTrigger().whileTrue(m_JawsOfLifeClose);

    //m_driveController.povLeft().onTrue(m_JawsOfLifeClosePosition);
    //m_driveController.povRight().onTrue(m_JawsOfLifeOpenPosition);


    m_driveController.b().onTrue(unlockArmMotors);
    m_driveController.x().onTrue(lockArmMotors);
    m_driveController.y().onTrue(m_positionClimb);
    // m_driveController.b().onTrue(m_AlgaeFloor);
    drive.setDefaultCommand(
            DriveCommands.joystickDrive(
                drive,
                () -> DriveMotorConstants.defaultSpeedValue*m_driveController.getLeftY(),
                () -> DriveMotorConstants.defaultSpeedValue*m_driveController.getLeftX(),
                () -> -DriveMotorConstants.defaultSpeedValue*m_driveController.getRightX()));
        // Slower Speed on Drive        
    m_driveController.rightBumper().whileTrue(
      DriveCommands.joystickDrive(
      drive,
      () -> DriveMotorConstants.damperSpeedValue*m_driveController.getLeftY(),
      () -> DriveMotorConstants.damperSpeedValue*m_driveController.getLeftX(),
      () -> -DriveMotorConstants.damperSpeedValue*m_driveController.getRightX()));

    
    // Higher Speed on Drive
    m_driveController.leftBumper().whileTrue(
      DriveCommands.joystickDrive(
      drive,
      () -> DriveMotorConstants.amplifySpeedValue*m_driveController.getLeftY(),
      () -> DriveMotorConstants.amplifySpeedValue*m_driveController.getLeftX(),
      () -> -DriveMotorConstants.amplifySpeedValue*m_driveController.getRightX()));

    // m_InOuttakeSubsystem.setDefaultCommand(m_idleSpeed);
    

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
     return autoChooser.getSelected();
  }

  public void stopAngleArm() {
    stop.schedule();
  }

  public void stopDrivetrain() {
    Commands.runOnce(() -> drive.runVelocity(new ChassisSpeeds(0, 0, 0)));
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
  
  private void rumbleOperatorControllerIfEngaged(final boolean engaged) {
      m_operatorController.setRumble(RumbleType.kBothRumble, engaged ? 1.0 : 0.0);
      m_driveController.setRumble(RumbleType.kBothRumble, engaged ? 1.0 : 0.0);
  }
}

