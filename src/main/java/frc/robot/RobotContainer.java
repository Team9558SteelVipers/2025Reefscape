// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ArmAngleConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.ServoArmConstants;
import frc.robot.commands.ServoArmCommand;
import frc.robot.subsystems.ServoArmSubsystem;
import frc.robot.commands.AngleArmDynamicCommand;
import frc.robot.commands.AngleArmStaticCommand;
import frc.robot.commands.JawsofLifeCommand;
import frc.robot.commands.setSpeedCommand;
import frc.robot.subsystems.AngleArmSubsystem;
import frc.robot.subsystems.InTakeOutTakesubsystem;
import frc.robot.subsystems.JawsOfLifeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotContainer {
  
  private final JawsOfLifeSubsystem m_JoLsubsystem = new JawsOfLifeSubsystem();
  private final InTakeOutTakesubsystem m_InOuttakeSubsystem = new InTakeOutTakesubsystem();
  private final AngleArmSubsystem m_angleArmSubsystem = new AngleArmSubsystem();
  private final ServoArmSubsystem m_servoArmSubsystem = new ServoArmSubsystem();
  
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  // Jol Section
  private final JawsofLifeCommand m_JawsOfLifeOpen = new JawsofLifeCommand(m_JoLsubsystem, -1);
  private final JawsofLifeCommand m_JawsOfLifeClose = new JawsofLifeCommand(m_JoLsubsystem, 0);

  private final AngleArmStaticCommand m_positionFloor = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionFloor);
  private final AngleArmStaticCommand m_positionStage1 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionFloor);
  private final AngleArmStaticCommand m_positionStage2 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionStage2);
  private final AngleArmStaticCommand m_positionClimb = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionClimb);

  public setSpeedCommand m_SpeedCommand = new setSpeedCommand(0, m_InOuttakeSubsystem);
  public setSpeedCommand m_ReverseSpeed = new setSpeedCommand(-0.5, m_InOuttakeSubsystem);

  private  AngleArmDynamicCommand setAngleArmDynamic = new AngleArmDynamicCommand(m_angleArmSubsystem, m_operatorController ::getLeftY);

  private ServoArmCommand lockArmMotors = new ServoArmCommand(m_servoArmSubsystem, ServoArmConstants.angle180);
  private ServoArmCommand unlockArmMotors = new ServoArmCommand(m_servoArmSubsystem, ServoArmConstants.angle0);

  private final NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {

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
