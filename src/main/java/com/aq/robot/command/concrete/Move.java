package com.aq.robot.command.concrete;

import com.aq.robot.command.Command;
import com.aq.robot.receiver.Robot;

public class Move implements Command {
	private Robot robot;

	public Move(Robot robot) {
		this.robot = robot;
	}
	@Override
	public void execute() {			
		robot.step();
	}

}
