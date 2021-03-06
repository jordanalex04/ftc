
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by FentonVideo on 10/27/2015.
 */
public class TestAlexander extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    ColorSensor colorSensor;

    @Override
    public void init() {
        /* Test to drive forward and turn and drive forward
        //Setup the left and right motors from config file
        leftMotor = hardwareMap.dcMotor.get("motor_1");
        rightMotor = hardwareMap.dcMotor.get("motor_2");
        //reverse the right side motor
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        //wait for the start button to be pressed
        waitForStart ();

        //Set the motors to drive the robot forward
        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);

        //Wait for 2 seconds
        sleep(2000);

        //Stop the robot
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        //Set the motors to turn the robot right
        leftMotor.setPower(0.5);
        rightMotor.setPower(0);

        //Wait for 1 second
        sleep(1000);

        //Stop robot
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        //Go straight again
        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);

        //Wait for 1.5 seconds
        sleep(1500);

        //Stop robot
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        */

    }


    @Override
    public void loop() {
        colorSensor = hardwareMap.colorSensor.get("color_1");
        int redValue;
        int blueValue;
        int greenValue;

        String Color;

        redValue = colorSensor.red();
        blueValue = colorSensor.blue();
        greenValue = colorSensor.green();

        if(redValue > blueValue & redValue > greenValue)
        {
            Color = "red";
        }
        if(blueValue > redValue & blueValue > greenValue)
        {
            Color = "blue";
        }
        telemetry.addData("Color: ", Color);
    //Comment to see if this works
    }
}
