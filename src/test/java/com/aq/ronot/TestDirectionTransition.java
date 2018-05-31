package com.aq.ronot;

import org.junit.Assert;
import org.junit.Test;

import com.aq.robot.receiver.internal.Direction;
import com.aq.robot.receiver.internal.Orientation;

public class TestDirectionTransition {
	@Test
	public void testTransitionsOnLeft(){
		Direction direction = Direction.WEST;
		Direction direction2 = direction.turn(Orientation.LEFT);	
		Assert.assertTrue(direction2 == Direction.SOUTH);
		Direction direction3 = direction2.turn(Orientation.LEFT);
		Assert.assertTrue(direction3 == Direction.EAST);
		Direction direction4= direction3.turn(Orientation.LEFT);
		Assert.assertTrue(direction4 == Direction.NORTH);
		Direction direction5 = direction4.turn(Orientation.LEFT);
		Assert.assertTrue(direction5 == Direction.WEST);
	}
	
	
	@Test
	public void testTransitionsOnRight(){
		Direction direction = Direction.WEST;
		Direction direction2 = direction.turn(Orientation.RIGHT);
		Assert.assertTrue(direction2 == Direction.NORTH);
		Direction direction3 = direction2.turn(Orientation.RIGHT);
		Assert.assertTrue(direction3 == Direction.EAST);
		Direction direction4= direction3.turn(Orientation.RIGHT);
		Assert.assertTrue(direction4 == Direction.SOUTH);
		Direction direction5 = direction4.turn(Orientation.RIGHT);
		Assert.assertTrue(direction5 == Direction.WEST);
	}

}
