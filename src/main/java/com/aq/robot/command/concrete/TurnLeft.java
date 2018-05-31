package com.aq.robot.command.concrete;

import com.aq.robot.command.Command;
import com.aq.robot.receiver.Robot;
import com.aq.robot.receiver.internal.Orientation;

public class TurnLeft implements Command{
	
	private Robot robot;

	public TurnLeft(Robot robot) {
		this.robot = robot;
	}

	public void execute() {		
		robot.turn(Orientation.LEFT);		
	}

}
