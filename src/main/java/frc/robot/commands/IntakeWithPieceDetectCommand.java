// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.InTakeOutTakesubsystem;

public class IntakeWithPieceDetectCommand extends Command {

  private final InTakeOutTakesubsystem intakeOuttakeSubsystem;
  private final double speed;
  private final double minimumRunTime;
  private final double maximumRunTime;
  private final Timer timer;

  public IntakeWithPieceDetectCommand(final InTakeOutTakesubsystem intakeOuttakeSubsystem, final double speed, final double minimumRunTime, final double maximumRunTime) {
    this.intakeOuttakeSubsystem = intakeOuttakeSubsystem;
    this.speed = speed;
    this.minimumRunTime = minimumRunTime;
    this.maximumRunTime = maximumRunTime;
    this.timer = new Timer();

    addRequirements(this.intakeOuttakeSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    intakeOuttakeSubsystem.setMotorSpeeds(-speed, -speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeOuttakeSubsystem.setMotorSpeeds(0, 0);
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > maximumRunTime || (timer.get() > minimumRunTime && intakeOuttakeSubsystem.pieceIsIn());
  }
}
