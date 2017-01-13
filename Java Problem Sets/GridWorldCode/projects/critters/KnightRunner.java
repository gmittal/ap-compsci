import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class KnightRunner {

	public static void main(String[] args) {

		ActorWorld world = new ActorWorld();
		world.add(new Location(2, 3), new Rock());
		world.add(new Location(3, 2), new Rock());
		world.add(new Location(3, 0), new Rock());
		world.add(new Location(0, 3), new Rock());
		world.add(new Location(7, 8), new Flower());
		world.add(new Location(2, 2), new Flower());
		world.add(new Location(3, 5), new Flower());
		world.add(new Location(3, 8), new Flower());
		world.add(new Location(6, 5), new Bug());
		world.add(new Location(5, 3), new Bug());
		world.add(new Location(4, 5), new CrabCritter());
		world.add(new Location(0, 0), new ChameleonCritter());
		world.add(new Location(1, 1), new KnightCritter());
		world.add(new Location(5, 5), new KnightCritter());
		world.show();

	}

}
