package com.aq.robot.receiver;

import com.aq.robot.receiver.internal.Orientation;
import com.aq.robot.receiver.internal.Position;

public class Robot {	
	private Position position;	
	private boolean placed;	
	
	public void  init(Position position){
		this.position = position;			
		this.placed = true;
		//System.out.println(String.format("I am at %s facing %s",position,direction.name()));
	}
	
	public void  turn(Orientation orientation){
		if(!placed) {			
			return;
		}
		position.setDirection(position.getDirection().turn(orientation));//TODO refactor turn into position
		//System.out.println("New Direction is " + direction.name());
	}
	
	public void  step(){
		if(!placed) {			
			return;
		}
		
		switch(position.getDirection()) {
		
		case EAST: this.position.add(new Position(1,0)); break; //x++ y same
		
		case WEST: this.position.add(new Position(-1,0)); break; //x-- y same
		
		case NORTH: this.position.add(new Position(0,1)); break; //x same y++
		
		case SOUTH: this.position.add(new Position(0,-1)); break; //x same y--
		
		}
	}

	private void emitPlacedMessage() {
		if(placed) {
			return;
		}
		System.out.println("Am not on the Table !");
		
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}	
	

	public void setPlaced(boolean placed) {
		this.placed = placed;
	}

	public void report() {
		if(!placed) {	
			emitPlacedMessage();
			return;
		}
		System.out.println(position);
		
	}
}
