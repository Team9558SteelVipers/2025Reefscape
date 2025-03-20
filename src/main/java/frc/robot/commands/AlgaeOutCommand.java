package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeOutSubsystem;

public class AlgaeOutCommand extends Command {

    AlgaeOutSubsystem ALGAEOUTTAKESubsystem;
    double ALGAEOUTSpeed;

public AlgaeOutCommand(AlgaeOutSubsystem m_AlgaeOutSubsystem, double m_ALGAEOUTSpeed) {

    ALGAEOUTTAKESubsystem = m_AlgaeOutSubsystem;
    ALGAEOUTSpeed = m_ALGAEOUTSpeed;

    addRequirements(ALGAEOUTTAKESubsystem);
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