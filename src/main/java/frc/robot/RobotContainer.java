// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ArmAngleConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.ServoArmConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ServoArmCommand;
import frc.robot.commands.AngleArmDynamicCommand;
import frc.robot.commands.AngleArmStaticCommand;
import frc.robot.subsystems.AngleArmSubsystem;
import frc.robot.subsystems.ServoArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {
  
  private final AngleArmSubsystem m_angleArmSubsystem = new AngleArmSubsystem();
  private final ServoArmSubsystem m_servoArmSubsystem = new ServoArmSubsystem();
  
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final AngleArmStaticCommand m_positionFloor = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionFloor);
  private final AngleArmStaticCommand m_positionStage1 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionFloor);
  private final AngleArmStaticCommand m_positionStage2 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionStage2);
  private final AngleArmStaticCommand m_positionClimb = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionClimb);

  private  AngleArmDynamicCommand setAngleArmDynamic = new AngleArmDynamicCommand(m_angleArmSubsystem, m_operatorController ::getLeftY);

  private ServoArmCommand lockArmMotors = new ServoArmCommand(m_servoArmSubsystem, ServoArmConstants.angle180);
  private ServoArmCommand unlockArmMotors = new ServoArmCommand(m_servoArmSubsystem, ServoArmConstants.angle0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }


  private void configureBindings() {
    m_operatorController.a().onTrue(m_positionFloor);
    m_operatorController.x().onTrue(m_positionStage1);
    m_operatorController.y().onTrue(m_positionStage2);
    m_operatorController.b().onTrue(m_positionClimb);

    m_operatorController.leftTrigger().onTrue(lockArmMotors);
    m_operatorController.rightTrigger().onTrue(unlockArmMotors);

    m_angleArmSubsystem.setDefaultCommand(setAngleArmDynamic);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return null;
  }
}
