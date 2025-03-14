// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.AngleArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.Supplier;

public class AngleArmDynamicCommand extends Command {
  private final AngleArmSubsystem m_AngleArmSubsystem;
  Supplier <Double> setSpeedLeft;

  public AngleArmDynamicCommand(AngleArmSubsystem angleArmSubsystem, Supplier <Double> new_SetSpeedLeft) {
    m_AngleArmSubsystem = angleArmSubsystem;
    setSpeedLeft = new_SetSpeedLeft;

    setSpeedLeft.get();

    addRequirements(angleArmSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_AngleArmSubsystem.setArmSpeedDynamic(setSpeedLeft.get());
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
