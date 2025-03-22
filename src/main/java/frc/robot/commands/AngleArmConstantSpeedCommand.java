// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.ArmAngleConstants;
import frc.robot.subsystems.AngleArmSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;


public class AngleArmConstantSpeedCommand extends Command {
  private final AngleArmSubsystem m_anglearmsubsystem;
  private final double m_speed;

  public AngleArmConstantSpeedCommand(AngleArmSubsystem anglearmsubsystem, double speed) {
    m_anglearmsubsystem = anglearmsubsystem;
    m_speed = speed;

    addRequirements(anglearmsubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_anglearmsubsystem.setArmSpeedDynamic(m_speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_anglearmsubsystem.setArmSpeedDynamic(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
