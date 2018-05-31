package com.aq.ronot;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.aq.robot.RobotController;
import com.aq.robot.receiver.Robot;
import com.aq.robot.receiver.internal.Direction;
import com.aq.robot.receiver.internal.Position;

public class TestPositionTransition {
/*
 * The design is open to read list of commands from a file.
 *  An overload  which reads commands from a file will then call this method
 */
	public void testMotion(Position start, Position end, String...commands) {
		RobotController robotController = new RobotController();
		String data  = "PLACE " + start.getX() +"," + start.getY() + "," + start.getDirection().name();
		for(String string: commands) {
			data += "\n" + string;
		}
		data += "\nREPORT\nQuit"; //harcoded into hte test so we never forget it
		ByteArrayInputStream baos = new ByteArrayInputStream(data.getBytes());
		Robot robot = robotController.operate(baos);
		Position position = robot.getPosition();
		Assert.assertTrue(position.equals(end)); //we are indeed where we want to be		
	}
	
	@Test
	public void testUseCase1c(){
		testMotion(
			new Position(1,2,Direction.EAST),
			new Position(3,3,Direction.NORTH),
			"MOVE","MOVE","LEFT","MOVE"
		);
			
		}
	
	@Test
	public void testUseCase1b(){
		testMotion(
			new Position(0,0,Direction.NORTH),
			new Position(0,0,Direction.WEST),
			"LEFT"
		);
			
		}
	
	@Test
	public void testUseCase1a(){
		testMotion(
			new Position(0,0,Direction.NORTH),
			new Position(0,1,Direction.NORTH),
			"MOVE"
		);
			
		}
	
	@Test
	public void testDiagonal(){
		testMotion(
			new Position(0,0,Direction.NORTH),
			new Position(5,5,Direction.SOUTH),
			"MOVE", "RIGHT","MOVE","LEFT", //1,1
			"MOVE", "RIGHT","MOVE","LEFT", //2,2
			"MOVE", "RIGHT","MOVE","LEFT",//3,3
			"MOVE", "RIGHT","MOVE","LEFT", //4,4
			"MOVE", "RIGHT","MOVE","LEFT", //5,5 
			"MOVE", "RIGHT","MOVE","LEFT" //extra moves that robot discards
			,"LEFT","LEFT" //responds to turn commands though
		);
			
		}
	
	
	@Test
	public void testOtherDiagonal(){
		testMotion(
			new Position(0,5,Direction.SOUTH),
			new Position(5,0,Direction.NORTH),
			"MOVE", "LEFT","MOVE","RIGHT", //1,4
			"MOVE", "LEFT","MOVE","RIGHT", //2,3
			"MOVE", "LEFT","MOVE","RIGHT",//3,2
			"MOVE", "LEFT","MOVE","RIGHT", //4,1
			"MOVE", "LEFT","MOVE","RIGHT", //5,0 
			"MOVE", "LEFT","MOVE","RIGHT" //extra moves that robot discards
			,"LEFT","LEFT" //responds to turn commands though
		);
			
		}
	
	@Test
	public void testRightDiagonal(){
		testMotion(
			new Position(5,0,Direction.NORTH),
			new Position(0,5,Direction.SOUTH),
			"MOVE", "LEFT","MOVE","RIGHT", //1,4
			"MOVE", "LEFT","MOVE","RIGHT", //2,3
			"MOVE", "LEFT","MOVE","RIGHT",//3,2
			"MOVE", "LEFT","MOVE","RIGHT", //4,1
			"MOVE", "LEFT","MOVE","RIGHT", //5,0 
			"MOVE", "LEFT","MOVE","RIGHT" //extra moves that robot discards
			,"LEFT","LEFT" //responds to turn commands though
		);
			
		}
}
