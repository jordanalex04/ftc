package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by aldas_000 on 9/19/2015.
 */
public class AutonomousOp extends OpMode {
//Declare the motors being used and a timer called "time"
    DcMotor leftDrive;
    DcMotor rightDrive;
    ElapsedTime time;

    static final double forwardTime = 1.0;
    static final double turnTime = 1.0;
    int count = 0;


    //State is like a warehouse and in it are 3 actions
    enum State {drivingStraight, turning, done}
//Gives the Enum State a name that we can use in the code to reference the 3 actions
    State state;

    public void init() {
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        time = new ElapsedTime();
        state = State.drivingStraight; //State variable, set to what it will do initially
    }

    public void loop() {
        double currentTime = time.time();
        switch(state) {
            //Cases are where you describe what the actions do that are stored in the warehouse
            case drivingStraight:
                leftDrive.setPower(0.2);
                rightDrive.setPower(0.2);
                if (currentTime > forwardTime) {
                    state = State.turning;
                    time.reset();
                }
                break;
            case turning:
                leftDrive.setPower(0.2);
                rightDrive.setPower(-0.2);
                if (currentTime > turnTime) {
                    count++;
                    if (count < 4) {
                        state = State.drivingStraight;
                    } else {
                        state = State.done;
                    }
                    time.reset();
                }
                break;
            case done:
                leftDrive.setPower(0);
                rightDrive.setPower(0);
        }
    }
}
