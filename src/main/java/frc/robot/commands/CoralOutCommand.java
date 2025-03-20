package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralOutSubsystem;

public class CoralOutCommand extends Command {

    CoralOutSubsystem CORALOUTTAKESubsystem;
    double CORALOUTSpeed;

public CoralOutCommand(CoralOutSubsystem m_CORALOUTTAKESubsystem, double m_CORALOUTspeed) {
    
    CORALOUTTAKESubsystem = m_CORALOUTTAKESubsystem;
    CORALOUTSpeed = m_CORALOUTspeed;

    addRequirements(CORALOUTTAKESubsystem);
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