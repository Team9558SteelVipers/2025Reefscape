// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// let me save
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.CoralSubsystem;
public class CoralOuttake extends Command {

  // Creating subsystem variable
  CoralSubsystem m_CoralSubsystem;
  
  // Initializing subsystem
  public CoralOuttake(CoralSubsystem CoralSubsystem) {

    // Setting subsystem variable to the subsystem taken in
    m_CoralSubsystem = CoralSubsystem;
    addRequirements(CoralSubsystem);
    // Use addRequirements() here to declare subsystem dependencies (Makes sure two commands with the same subsystem don't run together).
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_CoralSubsystem.outtakeCoral();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
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

