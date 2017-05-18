package game;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Path.
 */
public class Path {

	/** The pf. */
	private static PathFinder pf = new PathFinder();
	
	/** The points. */
	private List<Point> points;
	
	/**
	 * Points.
	 *
	 * @return the list
	 */
	public List<Point> points() { return points; }
	
	/**
	 * Instantiates a new path.
	 *
	 * @param creature the creature
	 * @param x the x
	 * @param y the y
	 */
	public Path(Creature creature, int x, int y){
		points = pf.findPath(creature, new Point(creature.x, creature.y, creature.z), new Point(x, y, creature.z), 300);
	}
}
