package com.aq.robot.receiver.internal;

public class Position {	

	private int x;

	private int y;
	
	private Direction direction;

	public Position(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		
	}

	public boolean add(Position offset) {
		int xOffset = this.x + offset.x;
		int yOffset = this.y + offset.y;
		
		if(isValidPosition(xOffset,yOffset)) {
			this.x = xOffset;
			this.y = yOffset;
			
			/*System.out.println(
					String.format("Robot Moving to [%d,%d]",x,y)
			);*/
			return true;
			
		}else {
			System.out.println(
					String.format("Robot Not Moving. Stays  at [%d,%d]",x,y)
			);
			return false;
		}
	}
	
	public boolean isValidPosition() {
		return isValidPosition(x,y);						
	}
	
	public boolean isValidPosition(int x, int y) {
		return 
				( x<=UPPER_LIMIT && x >=LOWER_LIMIT) && 
				( y<=UPPER_LIMIT && y >=LOWER_LIMIT);		
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return String.format("%d,%d,%s", x,y,direction.name());
	}
	
	private static final int LOWER_LIMIT = 0;

	private static final int UPPER_LIMIT = 5;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}	
	
	@Override
	public boolean equals(Object object) {
		if(object == null) {
			return false;
		}
		
		if(object == this) {
			return true;
		}
		
		if(!(object instanceof Position)) {
			return false;
		}
		
		Position compare = (Position) object;
		return (
				this.x == compare.x &&
				this.y == compare.y &&
				this.direction == compare.direction
				);
	}
}
