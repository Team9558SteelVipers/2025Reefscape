package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
//imports

public class JawsOfLifeSubsystem extends SubsystemBase {
 
  TalonSRX JoLMotor = new TalonSRX(Constants.JoLMotorConstants.JoLMotorPort);
  //initialize and set value of motor
  // TalonFXConfiguration pidconfig = new TalonFXConfiguration().withSlot0(new Slot0Configs().withKP(1).withKI(0).withKD(0));
  //configures the PID
  public JawsOfLifeSubsystem() {
    // JoLMotor.getConfigurator().apply(pidconfig);
    //apply PID to motor
    JoLMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  // public Command JawsOfLifeMethodCommand(double num) {
  //   JoLMotor.setControl(new PositionVoltage(num));
  //   //determines number of rotations of the motor


  //   return runOnce(
  //       () -> {

  //       });
  // }
public void JawsOfLifeSpeed(double speed){
  JoLMotor.set(ControlMode.PercentOutput,-speed);
}

public boolean isEngaged() {

  final double angle = getJawsOfLifeAngle();
  final boolean atEngagedAngle = MathUtil.isNear(Constants.JoLMotorConstants.JoLEngagedAngle, angle, Constants.JoLMotorConstants.JoLAngleTolerance);
  
  final double currentDraw = JoLMotor.getStatorCurrent();
  final boolean motorIsFacingResistance = (currentDraw > Constants.JoLMotorConstants.JoLResistanceCurrentThreshold);

  final boolean isOpening = JoLMotor.getMotorOutputPercent() < 0;

  System.out.println("JoL: angle is " + angle + ", current draw is " + currentDraw + ", is opening? " + isOpening);

  return atEngagedAngle && motorIsFacingResistance && isOpening;
}

public double getJawsOfLifeAngle() {
  return JoLMotor.getSelectedSensorPosition();
}

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
   
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

