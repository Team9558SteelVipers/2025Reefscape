// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.AngleArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;


public class AngleArmStaticCommand extends Command {
  private final double m_Position;
  private final AngleArmSubsystem m_AngleArmSubsystem;

  public AngleArmStaticCommand(AngleArmSubsystem angleArmSubsystem, double position) {
    m_AngleArmSubsystem = angleArmSubsystem;
    m_Position = position;

    addRequirements(angleArmSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_AngleArmSubsystem.setArmPositionStatic(m_Position);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
