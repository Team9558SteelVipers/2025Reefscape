package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeInSubsystem;

public class AlgaeInCommand extends Command {

    AlgaeInSubsystem ALGAEINTAKESubsystem;
    double ALGAEINSpeed;

public AlgaeInCommand(AlgaeInSubsystem m_ALGAEINTAKESubsystem, double m_ALGAEINSpeed) {

    ALGAEINTAKESubsystem = m_ALGAEINTAKESubsystem;
    ALGAEINSpeed = m_ALGAEINSpeed;

    addRequirements(ALGAEINTAKESubsystem);
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