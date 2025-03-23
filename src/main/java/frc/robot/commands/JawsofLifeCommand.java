package frc.robot.commands;

import frc.robot.subsystems.JawsOfLifeSubsystem;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class JawsofLifeCommand extends Command {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final JawsOfLifeSubsystem m_JoLsubsystem;
  private final double speed;
  private final Consumer<Boolean> m_engagedFeedbackConsumer;

  public JawsofLifeCommand(JawsOfLifeSubsystem m_subsystem, double speed, Consumer<Boolean> engagedFeedbackConsumer) {
    m_JoLsubsystem = m_subsystem;
    this.speed = speed;
    m_engagedFeedbackConsumer = engagedFeedbackConsumer;
    // 
    // **Use addRequirements() here to declare subsystem dependencies.**
    //   Eventually add a requirement relating to arm angle to ensure the arm angle is in the right spot
    addRequirements(m_subsystem);
  }
  //Command for Jaws of Life, with parameters subsystem and num (position of jaws of life)

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   m_JoLsubsystem.JawsOfLifeSpeed(speed);
  }
  // activates the command on initialization
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_engagedFeedbackConsumer.accept(m_JoLsubsystem.isEngaged());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_JoLsubsystem.JawsOfLifeSpeed(0);
    m_engagedFeedbackConsumer.accept(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}


