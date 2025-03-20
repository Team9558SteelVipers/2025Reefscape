// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable limelightTable; //allows limelight to access data over network.

  /** Creates a new ExampleSubsystem. */
  public LimelightSubsystem() {
  
     limelightTable =NetworkTableInstance.getDefault().getTable(Constants.LIMELIGHT_TABLE);  //Network table instance

  }

  public double getTX() {
    return limelightTable.getEntry(Constants.LIMELIGHT_X_KEY).getDouble(0.0);  //gets horizontal offset (tx) from limelight
}

public double getTY() {
  return limelightTable.getEntry(Constants.LIMELIGHT_Y_KEY).getDouble(0.0);   //gets vert offset (ty) from limelight
}

public double getTA() {
  return limelightTable.getEntry(Constants.LIMELIGHT_AREA_KEY).getDouble(0.0); // gets target are (ta) from limelight
}

public boolean hasValidTarget() {
  return limelightTable.getEntry(Constants.LIMELIGHT_VALID_TARGET_KEY).getDouble(0) == 1.0; //checks if limelight has valid target
}

public double getLatency() {
  return limelightTable.getEntry(Constants.LIMELIGHT_LATENCY_KEY).getDouble(0.0);
}

public void setPipeline(int pipeline) {
  limelightTable.getEntry("pipeline").setNumber(pipeline);
}

public void setCameraMode(int mode) {
  limelightTable.getEntry("camMode").setNumber(mode);
}

public void setLEDMode(int mode) {
  limelightTable.getEntry("ledMode").setNumber(mode);
}

public double calculateDistanceToTarget() {
  double targetHeight = Constants.TARGET_HEIGHT;
  double limelightHeight = Constants.LIMELIGHT_HEIGHT;
  double limelightAngle = Constants.LIMELIGHT_MOUNT_ANGLE;
  double angleToTarget = limelightAngle + getTY();
  return (targetHeight - limelightHeight) / Math.tan(Math.toRadians(angleToTarget));
}
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
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

  public void resetLimelightSettings() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'resetLimelightSettings'");
  }
}