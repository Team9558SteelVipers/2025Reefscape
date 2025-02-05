package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.Supplier;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;



import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class CoralSubsystem extends SubsystemBase{
    TalonFX CoralMotor;
    public CoralSubsystem() {
        CoralMotor = new TalonFX(Constants.coralMotorPort);
    }

    public void outtakeCoral(){
      CoralMotor.set(-0.3);
    }



}