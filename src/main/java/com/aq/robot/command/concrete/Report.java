package com.aq.robot.command.concrete;

import com.aq.robot.command.Command;
import com.aq.robot.receiver.Robot;
import com.aq.robot.receiver.internal.Orientation;

public class Report implements Command{
	
	private Robot robot;

	public Report(Robot robot) {
		this.robot = robot;
	}

	public void execute() {	
		robot.report();
	}

}
