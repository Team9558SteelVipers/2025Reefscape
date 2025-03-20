// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AlgaeInCommand;
import frc.robot.commands.AlgaeOutCommand;
import frc.robot.commands.CoralInCommand;
import frc.robot.commands.CoralOutCommand;
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
import frc.robot.commands.setSpeedCommand;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.AngleArmSubsystem;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.JawsOfLifeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.subsystems.AlgaeInSubsystem;
import frc.robot.subsystems.CoralInSubsystem;
import frc.robot.subsystems.CoralOutSubsystem;
import frc.robot.subsystems.AlgaeOutSubsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  private final Drive drive;
  public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

  private final JawsOfLifeSubsystem m_JoLsubsystem = new JawsOfLifeSubsystem();
  private final AngleArmSubsystem m_angleArmSubsystem = new AngleArmSubsystem();
  private final ServoArmSubsystem m_servoArmSubsystem = new ServoArmSubsystem();
  
  private final CommandXboxController m_operatorController =
      new CommandXboxController(0);

  // Jol Section
  private final JawsofLifeCommand m_JawsOfLifeOpen = new JawsofLifeCommand(m_JoLsubsystem, 1);
  private final JawsofLifeCommand m_JawsOfLifeClose = new JawsofLifeCommand(m_JoLsubsystem, 0);

  private final AngleArmStaticCommand m_positionFloor = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionFloor);
  private final AngleArmStaticCommand m_positionStage1 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionFloor);
  private final AngleArmStaticCommand m_positionStage2 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionStage2);
  private final AngleArmStaticCommand m_positionClimb = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionClimb);

  private  AngleArmDynamicCommand setAngleArmDynamic = new AngleArmDynamicCommand(m_angleArmSubsystem, m_operatorController ::getLeftY);

  final CoralInSubsystem m_CoralInSubsystem = new CoralInSubsystem();
  final CoralOutSubsystem m_CoralOutSubsystem = new CoralOutSubsystem();
  final AlgaeInSubsystem m_AlgaeInNoutSubsystem = new AlgaeInSubsystem();
  final AlgaeOutSubsystem m_AlgaeOutSubsystem = new AlgaeOutSubsystem();

  CoralInCommand m_CoralEatCommand = new CoralInCommand(m_CoralInSubsystem, 0);
  CoralOutCommand m_CoralSpitCommand = new CoralOutCommand(m_CoralOutSubsystem, 0);
  AlgaeInCommand m_AlgaeEatCommand = new AlgaeInCommand(m_AlgaeInNoutSubsystem, 0);
  AlgaeOutCommand m_AlgaeSpitCommand = new AlgaeOutCommand(m_AlgaeOutSubsystem, 0);

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
    configureBindings();
  }

  private void configureBindings() {
 
    m_operatorController.rightBumper().whileTrue(m_AlgaeEatCommand);
    m_operatorController.rightTrigger().whileTrue(m_CoralEatCommand);
    m_operatorController.leftBumper().whileTrue(m_AlgaeSpitCommand);
    m_operatorController.leftTrigger().whileTrue(m_CoralSpitCommand);

    m_operatorController.a().onTrue(m_positionFloor);
    m_operatorController.x().onTrue(m_positionStage1);
    m_operatorController.y().onTrue(m_positionStage2);
    m_operatorController.b().onTrue(m_positionClimb);

    m_operatorController.leftTrigger().onTrue(Commands.parallel(m_JawsOfLifeOpen, lockArmMotors));
    m_operatorController.rightTrigger().onTrue(Commands.parallel(m_JawsOfLifeClose, unlockArmMotors));

    m_operatorController.leftBumper().whileTrue(m_ReverseSpeed);
    m_operatorController.rightBumper().whileTrue(m_SpeedCommand);

    m_operatorController.back().onTrue(new InstantCommand(this::displayLimelightData));

    m_angleArmSubsystem.setDefaultCommand(setAngleArmDynamic);

    drive.setDefaultCommand(
            DriveCommands.joystickDrive(
                drive,
                () -> m_operatorController.getLeftY(),
                () -> m_operatorController.getLeftX(),
                () -> -m_operatorController.getRightX()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(InSubsystem);
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
