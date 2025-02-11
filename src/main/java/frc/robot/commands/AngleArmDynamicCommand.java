// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AngleArmSubsystem;

public class AngleArmDynamicCommand extends Command {
  private final AngleArmSubsystem m_AngleArmSubsystem;
  Supplier <Double> leftSpeed;
  Supplier <Double> rightSpeed;

  public AngleArmDynamicCommand(AngleArmSubsystem angleArmSubsystem, Supplier <Double> m_LeftSpeed, Supplier <Double> m_RightSpeed) {
    m_AngleArmSubsystem = angleArmSubsystem;
    leftSpeed = m_LeftSpeed;
    rightSpeed = m_RightSpeed;

    addRequirements(angleArmSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_AngleArmSubsystem.setArmSpeedDynamic(leftSpeed.get(),rightSpeed.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
