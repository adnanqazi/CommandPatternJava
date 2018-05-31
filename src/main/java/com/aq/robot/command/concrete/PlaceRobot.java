package com.aq.robot.command.concrete;

import com.aq.robot.InvalidCommandException;
import com.aq.robot.command.Command;
import com.aq.robot.receiver.Robot;
import com.aq.robot.receiver.internal.Direction;
import com.aq.robot.receiver.internal.Position;

public class PlaceRobot implements Command {
	
	private Robot robot;
	private Position position;	
	
	public PlaceRobot(Robot robot, String[] dataSet) throws InvalidCommandException{
		
		if(dataSet.length <2) {
			throw new InvalidCommandException("Insufficient Data to Place command");
		}
		String [] positionData = dataSet[1].split(",");
		
		if(positionData.length < 3) {
			throw new InvalidCommandException("Insufficient Data to Place command");
		}
		Direction direction = null;
		try {
		 direction = Direction.valueOf(positionData[2]);
		}   catch (IllegalArgumentException e) {
			throw new InvalidCommandException(String.format("[%s] is not a valid Direction",positionData[2]));
		}
		
		Position position = new Position(validateInteger(positionData[0]),validateInteger(positionData[1]), direction);
		
		if(!position.isValidPosition()) {
			throw new InvalidCommandException(
					String.format(
							"[%d,%d] is not a valid Position on the Table. Try again.",
							position.getX(),
							position.getY()
					)
			);
		}
		//no value gets set if any exceptions got thrown. There is no noise in the Robot.		
		this.robot = robot;
		this.position = position;		
	}

	private Integer validateInteger(String value) throws InvalidCommandException {
		try {
			Integer returnValue =  new Integer(value);
			return returnValue;
		}catch (NumberFormatException nfe) {
			throw new InvalidCommandException(String.format("[%s] is not a valid position co-ordinate",value));
		}
	}

	@Override
	public void execute() {
		robot.init(position);		
	}

}
