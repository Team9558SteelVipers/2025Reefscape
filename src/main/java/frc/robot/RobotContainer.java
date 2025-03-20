// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AlgaeInCommand;
import frc.robot.commands.AlgaeOutCommand;
import frc.robot.commands.CoralInCommand;
import frc.robot.commands.CoralOutCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.AlgaeInSubsystem;
import frc.robot.subsystems.CoralInSubsystem;
import frc.robot.subsystems.CoralOutSubsystem;
import frc.robot.subsystems.AlgaeOutSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {


  public CommandXboxController InNoutController = new CommandXboxController(0);
  final CoralInSubsystem m_CoralInSubsystem = new CoralInSubsystem();
  final CoralOutSubsystem m_CoralOutSubsystem = new CoralOutSubsystem();
  final AlgaeInSubsystem m_AlgaeInNoutSubsystem = new AlgaeInSubsystem();
  final AlgaeOutSubsystem m_AlgaeOutSubsystem = new AlgaeOutSubsystem();

    CoralInCommand m_CoralEatCommand = new CoralInCommand(m_CoralInSubsystem, 0);
    CoralOutCommand m_CoralSpitCommand = new CoralOutCommand(m_CoralOutSubsystem, 0);
    AlgaeInCommand m_AlgaeEatCommand = new AlgaeInCommand(m_AlgaeInNoutSubsystem, 0);
    AlgaeOutCommand m_AlgaeSpitCommand = new AlgaeOutCommand(m_AlgaeOutSubsystem, 0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
 
      InNoutController.rightBumper().whileTrue(m_AlgaeEatCommand);
      InNoutController.rightTrigger().whileTrue(m_CoralEatCommand);
      InNoutController.leftBumper().whileTrue(m_AlgaeSpitCommand);
      InNoutController.leftTrigger().whileTrue(m_CoralSpitCommand);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(InSubsystem);
        return m_CoralEatCommand;
  }
}
