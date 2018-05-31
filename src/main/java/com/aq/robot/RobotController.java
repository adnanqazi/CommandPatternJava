package com.aq.robot;

import java.io.InputStream;
import java.util.Scanner;

import com.aq.robot.command.Command;
import com.aq.robot.command.concrete.Move;
import com.aq.robot.command.concrete.PlaceRobot;
import com.aq.robot.command.concrete.Quit;
import com.aq.robot.command.concrete.Report;
import com.aq.robot.command.concrete.TurnLeft;
import com.aq.robot.command.concrete.TurnRight;
import com.aq.robot.invoker.Invoker;
import com.aq.robot.receiver.Robot;

public class RobotController {
/*
 * Using command pattern. Assuming a real world robot would queue its commands.
 * A priority queue would then fetch and execute commands
 * In this simple example, the Invoker is held by the client
 */
	public static void main(String[] args) {
		
		RobotController robotController = new RobotController();
		robotController.operate(System.in);
	}
	public Robot operate(InputStream inputStream) {		
		Scanner scanner = new Scanner(inputStream);
		Robot robot = new Robot();	
		Invoker invoker = new Invoker();	//not a convention, as noted above
		
		while (true) {
			String commandLine = scanner.nextLine();			
			try {
				Command command = getCommandForInput(commandLine.toUpperCase(),robot);
				invoker.setCommand(command);
				invoker.invoke(); //in the real world this happens from a different object. our design is open to do that
				
				if(command instanceof Quit) {
					return robot;
				}
			} catch (InvalidCommandException e) {
				System.out.println(e.getMessage());				
			}
		}
		
	}

	private static Command getCommandForInput(String myString, Robot robot) throws InvalidCommandException{
		String [] dataFromCommandLine = myString.split(ANY_SPACE);
		
		switch(dataFromCommandLine[0])  {
		
			case "LEFT":	return new TurnLeft(robot);
			
			case "PLACE":   return new PlaceRobot(robot,dataFromCommandLine);
			
			case "MOVE":	return new Move(robot);
			
			case "RIGHT":	return new TurnRight(robot);
			
			case "REPORT":	return new Report(robot);
			
			case "QUIT" : return new Quit();
			
			default: throw new InvalidCommandException(String.format("Unknown command [%s]",myString));
		}
		
	}
	private static final String ANY_SPACE = "\\s+"; 
}
