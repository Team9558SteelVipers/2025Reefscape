// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.AngleArmStaticCommand;
import frc.robot.subsystems.AngleArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.AngleArmConstants;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final AngleArmSubsystem m_AngleArmSubsystem = new AngleArmSubsystem();

  private AngleArmStaticCommand m_FloorPosition = new AngleArmStaticCommand(m_AngleArmSubsystem, AngleArmConstants.setPositionFloor);
  private AngleArmStaticCommand m_Stage1Position = new AngleArmStaticCommand(m_AngleArmSubsystem, AngleArmConstants.setPositionStage1);
  private AngleArmStaticCommand m_Stage2Position = new AngleArmStaticCommand(m_AngleArmSubsystem, AngleArmConstants.setPositionStage2);
  private AngleArmStaticCommand m_ClimbPosition = new AngleArmStaticCommand(m_AngleArmSubsystem, AngleArmConstants.setPositionClimb);

  private final CommandXboxController m_OperatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

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
