// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.JawsOfLifeSubsystem;

public class JawsofLifePositionCommand extends Command {
  private final JawsOfLifeSubsystem m_JoLsubsystem;
  private final double position;
  private final Consumer<Boolean> m_engagedFeedbackConsumer;

  public JawsofLifePositionCommand(JawsOfLifeSubsystem m_subsystem, double position, Consumer<Boolean> engagedFeedbackConsumer) {
    m_JoLsubsystem = m_subsystem;
    this.position = position;
    m_engagedFeedbackConsumer = engagedFeedbackConsumer;
    // 
    // **Use addRequirements() here to declare subsystem dependencies.**
    //   Eventually add a requirement relating to arm angle to ensure the arm angle is in the right spot
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_JoLsubsystem.JawsOfLifePosition(position);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_engagedFeedbackConsumer.accept(m_JoLsubsystem.isEngaged());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_engagedFeedbackConsumer.accept(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
