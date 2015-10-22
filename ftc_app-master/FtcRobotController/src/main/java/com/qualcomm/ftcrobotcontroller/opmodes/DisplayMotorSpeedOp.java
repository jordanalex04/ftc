package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp2 Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class DisplayMotorSpeedOp extends OpMode {

    DcMotorController.DeviceMode devMode;
    DcMotorController wheelController;
    DcMotor rightDrive;
    DcMotor leftDrive;

    int numOpLoops = 1;

    @Override
    public void init() {

        rightDrive = hardwareMap.dcMotor.get("right_drive");
        leftDrive = hardwareMap.dcMotor.get("left_drive");

        wheelController = hardwareMap.dcMotorController.get("wheelController");
        devMode = DcMotorController.DeviceMode.WRITE_ONLY;

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        //motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // set the mode
        // HiTechnic devices start up in "write" mode by default, so no need to switch device modes here.
        leftDrive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightDrive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {

        // The op mode should only use "write" methods (setPower, setChannelMode, etc) while in
        // WRITE_ONLY mode or SWITCHING_TO_WRITE_MODE
        if (allowedToWrite()) {


            if (gamepad1.dpad_left) {
                // Nxt devices start up in "write" mode by default, so no need to switch modes here.
                leftDrive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
                rightDrive.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            }
            if (gamepad1.dpad_right) {
                // Nxt devices start up in "write" mode by default, so no need to switch modes here.
                leftDrive.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                rightDrive.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            }

            float stickL1 = gamepad1.left_stick_y;
            float stickR1 = gamepad1.right_stick_y;


            // write the values to the motors
            rightDrive.setPower(stickR1);
            leftDrive.setPower(stickL1);


        }



        // To read any values from the NXT controllers, we need to switch into READ_ONLY mode.
        // It takes time for the hardware to switch, so you can't switch modes within one loop of the
        // op mode. Every 17th loop, this op mode switches to READ_ONLY mode, and gets the current power.
        if (numOpLoops % 17 == 0){
            // Note: If you are using the NxtDcMotorController, you need to switch into "read" mode
            // before doing a read, and into "write" mode before doing a write. This is because
            // the NxtDcMotorController is on the I2C interface, and can only do one at a time. If you are
            // using the USBDcMotorController, there is no need to switch, because USB can handle reads
            // and writes without changing modes. The NxtDcMotorControllers start up in "write" mode.
            // This method does nothing on USB devices, but is needed on Nxt devices.
            wheelController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        }

        // Every 17 loops, switch to read mode so we can read data from the NXT device.
        // Only necessary on NXT devices.
        if (wheelController.getMotorControllerDeviceMode() == DcMotorController.DeviceMode.READ_ONLY) {

            // Update the reads after some loops, when the command has successfully propagated through.
            telemetry.addData("Text", "free flow text");
            telemetry.addData("left motor", leftDrive.getPower());
            telemetry.addData("right motor", rightDrive.getPower());
            telemetry.addData("RunMode: ", leftDrive.getChannelMode().toString());

            // Only needed on Nxt devices, but not on USB devices
            wheelController.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

            // Reset the loop
            numOpLoops = 0;
        }

        // Update the current devMode
        devMode = wheelController.getMotorControllerDeviceMode();
        numOpLoops++;
    }

    // If the device is in either of these two modes, the op mode is allowed to write to the HW.
    private boolean allowedToWrite(){
        return (devMode == DcMotorController.DeviceMode.WRITE_ONLY);
    }
}