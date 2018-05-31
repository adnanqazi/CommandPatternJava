package com.aq.robot.receiver.internal;

import java.util.logging.Logger;

public enum Direction {
	NORTH(0), EAST(1), SOUTH(2), WEST(3);

	private Integer identifier;

	private Direction(int indentifier) {
		this.identifier = indentifier;
	}

	public Direction turn(Orientation orientation) {
		LOG.finest(
				String.format(
						"current direction is [%s] turning [%s]", this.name(),  orientation.name()
				)
		);
		int offset = (Orientation.RIGHT == orientation) ? 1 : -1;
		Direction newDirection =  Direction.parse(this.identifier + offset);
		LOG.finest("New Direction is "+ newDirection);
		return newDirection;
	}

	private static Direction parse(int subscript) {
		subscript += 4;
		subscript %= 4; // 0-3 is now a circular list

		for (Direction direction : Direction.values()) {
			if (direction.identifier == subscript) {
				return direction;
			}
		} //or fail fast
		throw new IllegalStateException("Unable to parse direction");
	}	
	
private static final Logger LOG  = Logger.getLogger(Direction.class.getName());
}
