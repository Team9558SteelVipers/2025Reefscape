package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class JawsOfLifeSubsystem extends SubsystemBase {
 
  TalonSRX JoLMotor = new TalonSRX(Constants.JoLMotorConstants.JoLMotorPort);
  //initialize and set value of motor
  // TalonFXConfiguration pidconfig = new TalonFXConfiguration().withSlot0(new Slot0Configs().withKP(1).withKI(0).withKD(0));
  //configures the PID
  public JawsOfLifeSubsystem() {
    // JoLMotor.getConfigurator().apply(pidconfig);
    //apply PID to motor
    JoLMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    
    JoLMotor.config_kP(0, Constants.JoLMotorConstants.JoLkP);
    JoLMotor.config_kI(0, Constants.JoLMotorConstants.JoLkI);
    JoLMotor.config_kD(0, Constants.JoLMotorConstants.JoLkD);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  
  // public Command JawsOfLifeMethodCommand(double num) {
  //   JoLMotor.setControl(new PositionVoltage(num));
  //   //determines number of rotations of the motor

  public void JawsOfLifePosition(double num) {
    JoLMotor.set(ControlMode.Position, num);
  }

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

  //final boolean isOpening = JoLMotor.getMotorOutputPercent() > 0;

  System.out.println("JoL: angle is " + angle + ", current draw is " + currentDraw);

  return atEngagedAngle && motorIsFacingResistance;
}

public double getJawsOfLifeAngle() {
  return JoLMotor.getSelectedSensorPosition() / Constants.JoLMotorConstants.JoLTicksPerRevolution;
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

