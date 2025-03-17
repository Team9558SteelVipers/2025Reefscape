// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.InNoutCommand;
import frc.robot.subsystems.InSubsystem;
import frc.robot.subsystems.OutSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  public CommandXboxController InNoutController = new CommandXboxController(0);
  final InSubsystem m_InSubsystem = new InSubsystem();
  final OutSubsystem m_OutSubsystem = new OutSubsystem();

    InNoutCommand m_EatCommand = new InNoutCommand(m_InSubsystem, 0);
    InNoutCommand m_SpitCommand = new InNoutCommand(m_OutSubsystem, 0);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
 
      InNoutController.leftBumper().whileTrue(m_EatCommand);
      InNoutController.rightBumper().whileTrue(m_SpitCommand);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_InSubsystem);
  }
}
