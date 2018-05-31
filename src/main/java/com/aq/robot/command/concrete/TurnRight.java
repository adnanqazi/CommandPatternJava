package com.aq.robot.command.concrete;

import com.aq.robot.command.Command;
import com.aq.robot.receiver.Robot;
import com.aq.robot.receiver.internal.Orientation;

public class TurnRight implements Command{
	
	private Robot robot;

	public TurnRight(Robot robot) {
		this.robot = robot;
	}

	public void execute() {		
		robot.turn(Orientation.RIGHT);		
	}
}
