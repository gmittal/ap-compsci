import java.util.ArrayList;

import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class KnightCritter extends Critter {

	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locs = new ArrayList<Location>();

		relativeLocation(locs, 1, 2);
		relativeLocation(locs, 1, -2);
		relativeLocation(locs, -1, 2);
		relativeLocation(locs, -1, -2);
		relativeLocation(locs, 2, 1);
		relativeLocation(locs, 2, -1);
		relativeLocation(locs, -2, 1);
		relativeLocation(locs, -2, -1);

		return locs;
	}

	private void relativeLocation(ArrayList<Location> locs, int x, int y) {
		Location myLoc = getLocation();
		Location testLoc = new Location(myLoc.getRow() + y, myLoc.getCol() + x);

		if (getGrid().isValid(testLoc) && getGrid().get(testLoc) == null)
			locs.add(testLoc);
	}

}
