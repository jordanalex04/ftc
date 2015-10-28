package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Max on 10/27/2015.
 */
public class TestMaxwell extends LinearOpMode {
/*Comment for testing purposes */

    DcMotor leftMotor;
    DcMotor rightMotor;



    /* code for teleop
    @Override
    public void init() {
         //Gets references to motors from the hardware map
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        //reverses the left motor
       rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }
    @Override
    public void loop() {
        //Get the values from the game pad
        //note:pushing the stick all the way forward gives a value of -1
        //We need to reverse the values
        float Stick1LeftY = -gamepad1.left_stick_y;
        float Stick1rightY = -gamepad1.right_stick_y;

        //sets the power of the motors with the gamepad value
        leftMotor.setPower(Stick1LeftY);
        rightMotor.setPower(Stick1rightY);

    }
    */

    //Code for forward, turn  around , forward
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        //sets the motor power to .5 then waits for 3 sec
        rightMotor.setPower(0.5);
        leftMotor.setPower(0.5);

        sleep(3000);

        //Turns off left motor and keeps right motor on for 1 sec
        leftMotor.setPower(0);

        sleep(1000);

        //Turn completed now to add power back to left motor
        leftMotor.setPower(.5);

        sleep(3000);

        //stops the robot
        rightMotor.setPower(0);
        leftMotor.setPower(0);

    }


}
