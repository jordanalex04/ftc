#pragma config(Hubs,  S1, HTMotor,  none,     none,     none)
#pragma config(Hubs,  S4, HTServo,  none,     none,     none)
#pragma config(Sensor, S1,     ,               sensorI2CMuxController)
#pragma config(Sensor, S2,     rightT,         sensorTouch)
#pragma config(Sensor, S3,     leftT,          sensorTouch)
#pragma config(Sensor, S4,     ,               sensorI2CMuxController)
#pragma config(Motor,  motorA,          Hand,          tmotorNXT, PIDControl, encoder)
#pragma config(Motor,  motorB,          Arm,           tmotorNXT, PIDControl, encoder)
#pragma config(Motor,  motorC,           ,             tmotorNXT, openLoop)
#pragma config(Motor,  mtr_S1_C1_1,     Right,         tmotorTetrix, PIDControl, reversed, encoder)
#pragma config(Motor,  mtr_S1_C1_2,     Left,          tmotorTetrix, PIDControl, encoder)
#pragma config(Servo,  srvo_S4_C1_1,    servo1,               tServoStandard)
#pragma config(Servo,  srvo_S4_C1_2,    servo2,               tServoNone)
#pragma config(Servo,  srvo_S4_C1_3,    servo3,               tServoNone)
#pragma config(Servo,  srvo_S4_C1_4,    servo4,               tServoNone)
#pragma config(Servo,  srvo_S4_C1_5,    servo5,               tServoNone)
#pragma config(Servo,  srvo_S4_C1_6,    servo6,               tServoNone)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

#include "JoystickDriver.c"



int fullRev = 1130;
int halfRev = 565;
int quartRev = 283; // rounded up

void Forward(int x, int y)
{
	nMotorEncoder[Left] = 0;
	nMotorEncoder[Right] = 0;

	nMotorEncoderTarget[Left] = x;
	nMotorEncoderTarget[Right] = x;



	motor[Left] = y;
	motor[Right] = y;

	while (nMotorRunState[Left] != runStateIdle || nMotorRunState[Right] != runStateIdle) //while the encoder wheel turns one revolution
	{

	}
	motor[Left] = 0;
	motor[Right] = 0;
}
//-------------------------------------------------------------------------- TURN CW
void turnCW(int y)
{
	nMotorEncoder[Left] = 0;
	nMotorEncoder[Right] = 0;

	nMotorEncoderTarget[Left] = y;
	nMotorEncoderTarget[Right] = y;

	motor[Left] = 0;
	motor[Right] = -30;

	while (nMotorRunState[Left] != runStateIdle || nMotorRunState[Right] != runStateIdle)
	{

	}
	motor[Left] = 0;
	motor[Right] = 0;
}
//------------------------------------------------------------------------- TURN CCW
void turnCCW(int x)
{
	nMotorEncoder[Left] = 0;
	nMotorEncoder[Right] = 0;

	nMotorEncoderTarget[Left] = x;
	nMotorEncoderTarget[Right] = x;

	motor[Left] = -30;
	motor[Right] = 0;

	while (nMotorRunState[Left] != runStateIdle || nMotorRunState[Right] != runStateIdle)
	{

	}
	motor[Left] = 0;
	motor[Right] = 0;
}
//------------------------------------------------------------------------ CHECK WALL
void checkWall()
{
	while(SensorValue(S2)+SensorValue(S3) != 2)
	{
		wait1Msec(10);
		if (SensorValue(S2)==1 && SensorValue(S3)==1)		//What detects when it is against the wall.
		{
			motor[Left] = 0;
			motor[Right] = 0;
		}
		else
		{
			motor[Left] = -25;
			motor[Right] = -25;
		}
	}
}
//------------------------------------------------------------------------- SPECIAL TURN
void specialTurn(int w, float x, int y, int z)
{
	nMotorEncoder[Left] = 0;
	nMotorEncoder[Right] = 0;

	nMotorEncoderTarget[Left] = w;
	nMotorEncoderTarget[Right] = x;

	motor[Left] = y;
	motor[Right] = z;

	while (nMotorRunState[Left] != runStateIdle || nMotorRunState[Right] != runStateIdle)
	{

	}
	motor[Left] = 0;
	motor[Right] = 0;
}
//------------------------------------------------------------------------------------------------------------------------ MAIN PROGRAM
task main()
{

	waitForStart();



	wait1Msec(2000);

	Forward(fullRev * 3.5, 50);

	wait1Msec(1000);

	turnCW(fullRev * 2.45);

	wait1Msec(1000);

	checkWall();

	wait1Msec(1000);

	Forward(fullRev * 10.82, 40);

	wait1Msec(1000);

	turnCCW(fullRev * 2.45);

	wait1Msec(1000);

	Forward(fullRev * 8, 40);

	wait1Msec(1000);

	specialTurn(fullRev, fullRev * 3.5, 10, 40);

	wait1Msec(1000);

	Forward(fullRev * 4, 40);

	wait1Msec(1000);

	turnCCW(fullRev * 2.25);

	wait1Msec(1000);

	Forward(fullRev * 11, 40);

	wait1Msec(1000);

	turnCCW(fullRev * 3);

	wait1Msec(1000);

	checkWall();

	wait1Msec(1000);

	Forward(fullRev * 1.5, 30);

	wait1Msec(1000);

	turnCCW(fullRev * 3);

	wait1Msec(1000);

	checkWall();

	wait1Msec(1000);

}

/*
THE ARCHIVES

nMotorEncoder[Left] = 0;
nMotorEncoder[Right] = 0;

while(nMotorEncoder[Left] < 1130)
{
motor[Left] = 20;
motor[Right] = 20;
wait1Msec(1000);
}


motor[Left] = 0;
motor[Right] = 0;

wait1Msec(3000);


Used to detirmine how many ticks of the encoder equalled one revolution of the wheel.
nMotorEncoder[Left] = 0;
while (true)
{
nxtDisplayTextLine(2, "Enc=%d", nMotorEncoder[Left]);
wait1Msec(100);
}
Used to detirmine how many ticks of the encoder equalled one revolution of the wheel.
*/
