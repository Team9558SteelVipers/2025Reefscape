
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.CoralOuttake;
import frc.robot.commands.DriveDynamic;
import frc.robot.commands.DriveStatic;
import frc.robot.commands.PIDExamplePositionCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PIDExample;
import frc.robot.subsystems.CoralSubsystem;
import java.time.Clock;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // initializing subsystems
  private final DriveSubsystem m_Subsystem = new DriveSubsystem();
  private final CoralSubsystem m_CoralSubsystem = new CoralSubsystem();
  private final PIDExample m_PIDSubsystem = new PIDExample();
  // initializing the controller
  CommandXboxController xcontroller = new CommandXboxController(0); 

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
}
