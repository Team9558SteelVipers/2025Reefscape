// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.InTakeOutTakesubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class IntakeOuttakeCommand extends Command {
  double setMotorSpeed;
  InTakeOutTakesubsystem subsystem;

  /** Creates a new setSpeedCommand. */
  public IntakeOuttakeCommand(double m_setMotorSpeed, InTakeOutTakesubsystem m_InOuttakeSubsystem) {
      setMotorSpeed = m_setMotorSpeed;
      subsystem = m_InOuttakeSubsystem;

      addRequirements(subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override

  public void initialize() {
    subsystem.setMotorSpeed(-setMotorSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    subsystem.setMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
