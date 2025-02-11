// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.AngleArmConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AngleArmDynamicCommand;
import frc.robot.commands.AngleArmStaticCommand;
import frc.robot.commands.Autos;
import frc.robot.subsystems.AngleArmSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here
  private final AngleArmSubsystem m_AngleArmSubsystem = new AngleArmSubsystem();

  // The robot's controllers are defined here
  private final CommandXboxController m_OperatorController =
  new CommandXboxController(OperatorConstants.kOperatorControllerPort);
  
  // The robot's commands are defined here
  private final AngleArmStaticCommand m_FloorPosition = new AngleArmStaticCommand(m_AngleArmSubsystem, AngleArmConstants.positionFloor);
  private final AngleArmStaticCommand m_Stage1Position = new AngleArmStaticCommand(m_AngleArmSubsystem, AngleArmConstants.positionStage1);
  private final AngleArmStaticCommand m_Stage2Position = new AngleArmStaticCommand(m_AngleArmSubsystem, AngleArmConstants.positionStage2);
  private final AngleArmStaticCommand m_ClimbPosition = new AngleArmStaticCommand(m_AngleArmSubsystem, AngleArmConstants.positionClimb);

  public AngleArmDynamicCommand setArmSpeedMotorDyanmic = new AngleArmDynamicCommand(m_AngleArmSubsystem, m_OperatorController :: getLeftY, m_OperatorController ::getRightY);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //Confiure the trigger bindings
    configureBindings();
  }


  private void configureBindings() {
    m_OperatorController.b().whileTrue(m_FloorPosition);
    m_OperatorController.a().whileTrue(m_Stage1Position);
    m_OperatorController.x().whileTrue(m_Stage2Position);
    m_OperatorController.y().whileTrue(m_ClimbPosition);

    m_AngleArmSubsystem.setDefaultCommand(setArmSpeedMotorDyanmic);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_AngleArmSubsystem);
  }
}
