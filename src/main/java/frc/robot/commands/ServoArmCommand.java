// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ServoArmSubsystem;

public class ServoArmCommand extends Command {

  private final double m_angle;
  private final ServoArmSubsystem m_servoArmSubsystem;

  public ServoArmCommand(ServoArmSubsystem servoArmSubsystem, double angle) {
    m_servoArmSubsystem = servoArmSubsystem;
    m_angle = angle;

    addRequirements(servoArmSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_servoArmSubsystem.setServoAngle(m_angle);
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
    return true;
  }
}
