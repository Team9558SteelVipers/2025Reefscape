// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.InSubsystem;
import frc.robot.subsystems.OutSubsystem;


public class InNoutCommand extends Command {

  InSubsystem INTAKESubsystem;
  OutSubsystem OUTTAKESubsystem;
  double INSpeed;
  double OUTSpeed;
  
    public InNoutCommand(InSubsystem m_INTAKEsystem, double m_INSpeed) {

      INTAKESubsystem = m_INTAKEsystem;
      INSpeed = m_INSpeed;

      addRequirements(INTAKESubsystem);
    }
    public InNoutCommand(OutSubsystem m_OUTTAKESubsystem, double m_OUTspeed) {

      OUTTAKESubsystem = m_OUTTAKESubsystem;
      OUTSpeed = m_OUTspeed;

      addRequirements(OUTTAKESubsystem);
   }
    

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end
  @Override
  public boolean isFinished() {
    return false;
  }
}
