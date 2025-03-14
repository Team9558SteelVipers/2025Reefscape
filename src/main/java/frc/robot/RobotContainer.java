// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ArmAngleConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AngleArmDynamicCommand;
import frc.robot.commands.AngleArmStaticCommand;
import frc.robot.commands.setSpeedCommand;
import frc.robot.subsystems.AngleArmSubsystem;
import frc.robot.subsystems.InTakeOutTakesubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  
    final InTakeOutTakesubsystem m_InOuttakeSubsystem = new InTakeOutTakesubsystem();
    final AngleArmSubsystem m_angleArmSubsystem = new AngleArmSubsystem();

    
    public CommandXboxController inOutController = new CommandXboxController(0);
    public CommandXboxController m_operatorController = new CommandXboxController(0);
    public setSpeedCommand m_SpeedCommand = new setSpeedCommand(0, m_InOuttakeSubsystem);
    public setSpeedCommand m_ReverseSpeed = new setSpeedCommand(-0.5, m_InOuttakeSubsystem);
    


    public final AngleArmStaticCommand m_positionFloor = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionFloor);
    public final AngleArmStaticCommand m_positionStage1 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionFloor);
    public final AngleArmStaticCommand m_positionStage2 = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionStage2);
    public final AngleArmStaticCommand m_positionClimb = new AngleArmStaticCommand(m_angleArmSubsystem, ArmAngleConstants.positionClimb);

    private  AngleArmDynamicCommand setAngleArmDynamicCommand = new AngleArmDynamicCommand(m_angleArmSubsystem, m_operatorController ::getLeftY);
  // The robot's subsystems and commands are defined here...
  

  // Replace with CommandPS4Controller or CommandJoystick if needed
  
    private final CommandXboxController controllerx =  new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    inOutController.rightBumper().whileTrue(m_SpeedCommand);
    inOutController.leftBumper().whileTrue(m_ReverseSpeed);

    m_operatorController.a().whileTrue(m_positionFloor);
    m_operatorController.x().whileTrue(m_positionStage1);
    m_operatorController.y().whileTrue(m_positionStage2);
    m_operatorController.b().whileTrue(m_positionClimb);

    // Schedule `ExampleColmmand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    
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
