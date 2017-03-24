
/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class LifeBug extends Bug {

	private double probBreed, probDeath;
	private Random rand;

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public LifeBug() {
		probBreed = 1.0 / 7.0;
		probDeath = 1.0 / 5.0;
		rand = new Random();
	}

	public LifeBug(Color c) {
		probBreed = 1.0 / 7.0;
		probDeath = 1.0 / 5.0;
		rand = new Random();
		setColor(c);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {

		if (rand.nextDouble() < probBreed) {
			ArrayList<Location> open = new ArrayList<>();
			for (int dir = 0; dir < 360; dir += 90)
				if (canMoveInDirection(dir))
					open.add(getLocation().getAdjacentLocation(dir));

			for (Location loc : open) {
				new LifeBug(getColor()).putSelfInGrid(getGrid(), loc);
			}

		} else {
			ArrayList<Integer> open = new ArrayList<>();
			for (int dir = -90; dir <= 90; dir += 90)
				if (canMoveInDirection(getDirection() + dir))
					open.add(getDirection() + dir);

			if (!open.isEmpty()) {
				setDirection(open.get(rand.nextInt(open.size())));
				move();
			}
		}

		if (rand.nextDouble() < probDeath)
			removeSelfFromGrid();

	}

	public boolean canMoveInDirection(int dir) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return false;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(dir);
		if (!gr.isValid(next))
			return false;
		Actor neighbor = gr.get(next);
		return (neighbor == null) || (neighbor instanceof Flower);
		// ok to move into empty location or onto flower
		// not ok to move onto any other actor
	}
}
