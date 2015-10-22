package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by aldas_000 on 9/13/2015.
 */
public class TeleOp extends OpMode {

   // DcMotor leftArm;
   // DcMotor rightArm;

    DcMotor leftDrive;
    DcMotor rightDrive;

    TouchSensor touchSensor;
    UltrasonicSensor ultrasonicSensor;

    @Override
    public void init() {

        //Gets references for motors from hardware map
       // leftArm = hardwareMap.dcMotor.get("left_arm");
       // rightArm = hardwareMap.dcMotor.get("right_arm");

        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");

        touchSensor = hardwareMap.touchSensor.get("touch");
        ultrasonicSensor = hardwareMap.ultrasonicSensor.get("sonic");

        //Reverse the right motor arm, left drive
       // rightArm.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
    }



    double speed;
    double sonicVal;

    @Override
    public void loop() {


        telemetry.addData("Chosen Speed: ", speed);
        telemetry.addData("Ultrasonic Value: ", sonicVal);


        //Get the values from the gamepads.
        //Note: pushing the stick all the way up returns -1,
        //so we need to reverse the values.
        float stickL1 = gamepad1.left_stick_y;
        float stickR1 = gamepad1.right_stick_y;

        float stickL2 = -gamepad2.left_stick_y;
        float stickR2 = -gamepad2.right_stick_y;


            //set the power of the motor with the gamepad values
       // leftArm.setPower(stickL2);
       // rightArm.setPower(stickR2);



        //Code for speed control
        // Choosing a speed with a button (x,a,b,y)
        if (gamepad1.x) {
            speed = 0.10;
        } else if (gamepad1.a) {
            speed = 0.30;
        } else if (gamepad1.b) {
            speed = 0.50;
        } else if (gamepad1.y) {
            speed = 1;
        }
        //Code for moving left drive wheel with given speed chosen below below
        if (stickL1 < 0) {
            leftDrive.setPower(-speed);
        } else if (stickL1 > 0) {
            leftDrive.setPower(speed);
        } else {
            leftDrive.setPower(0);
        }
        //Code for moving right drive wheel with given speed chosen below
        if (stickR1 < 0) {
            rightDrive.setPower(-speed);
        } else if (stickR1 > 0) {
            rightDrive.setPower(speed);
        } else {
            rightDrive.setPower(0);
        }

        if(touchSensor.isPressed()) {
            sonicVal = ultrasonicSensor.getUltrasonicLevel();
        }
        //end of loop
    }
}

