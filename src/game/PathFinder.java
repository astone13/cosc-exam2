package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Class PathFinder.
 */
public class PathFinder {
    
    /** The open. */
    private ArrayList<Point> open;
    
    /** The closed. */
    private ArrayList<Point> closed;
    
    /** The parents. */
    private HashMap<Point, Point> parents;
	
	/** The total cost. */
	private HashMap<Point,Integer> totalCost;
    
    /**
     * Instantiates a new path finder.
     */
    public PathFinder() {
    	this.open = new ArrayList<Point>();
        this.closed = new ArrayList<Point>();
        this.parents = new HashMap<Point, Point>();
        this.totalCost = new HashMap<Point, Integer>();
    }
    
    /**
     * Heuristic cost.
     *
     * @param from the from
     * @param to the to
     * @return the int
     */
    private int heuristicCost(Point from, Point to) {
        return Math.max(Math.abs(from.x - to.x), Math.abs(from.y - to.y));
    }

    /**
     * Cost to get to.
     *
     * @param from the from
     * @return the int
     */
    private int costToGetTo(Point from) {
        return parents.get(from) == null ? 0 : (1 + costToGetTo(parents.get(from)));
    }
    
    /**
     * Total cost.
     *
     * @param from the from
     * @param to the to
     * @return the int
     */
    private int totalCost(Point from, Point to) {
        if (totalCost.containsKey(from))
        	return totalCost.get(from);
        
        int cost = costToGetTo(from) + heuristicCost(from, to);
        totalCost.put(from, cost);
        return cost;
    }

    /**
     * Re parent.
     *
     * @param child the child
     * @param parent the parent
     */
    private void reParent(Point child, Point parent){
        parents.put(child, parent);
        totalCost.remove(child);
    }

    /**
     * Find path.
     *
     * @param creature the creature
     * @param start the start
     * @param end the end
     * @param maxTries the max tries
     * @return the array list
     */
    public ArrayList<Point> findPath(Creature creature, Point start, Point end, int maxTries) {
        open.clear();
        closed.clear();
        parents.clear();
        totalCost.clear();
    	
        open.add(start);
        
        for (int tries = 0; tries < maxTries && open.size() > 0; tries++){
            Point closest = getClosestPoint(end);
            
            open.remove(closest);
            closed.add(closest);

            if (closest.equals(end))
                return createPath(start, closest);
            else
                checkNeighbors(creature, end, closest);
        }
        return null;
    }

	/**
	 * Gets the closest point.
	 *
	 * @param end the end
	 * @return the closest point
	 */
	private Point getClosestPoint(Point end) {
		Point closest = open.get(0);
		for (Point other : open){
		    if (totalCost(other, end) < totalCost(closest, end))
		        closest = other;
		}
		return closest;
	}

	/**
	 * Check neighbors.
	 *
	 * @param creature the creature
	 * @param end the end
	 * @param closest the closest
	 */
	private void checkNeighbors(Creature creature, Point end, Point closest) {
		for (Point neighbor : closest.neighbors8()) {
		    if (closed.contains(neighbor)
		    		|| !creature.canEnter(neighbor.x, neighbor.y, creature.z)
		    		&& !neighbor.equals(end))
		        continue;
			
		    if (open.contains(neighbor))
				reParentNeighborIfNecessary(closest, neighbor);
		    else
		        reParentNeighbor(closest, neighbor);
		}
	}

	/**
	 * Re parent neighbor.
	 *
	 * @param closest the closest
	 * @param neighbor the neighbor
	 */
	private void reParentNeighbor(Point closest, Point neighbor) {
		reParent(neighbor, closest);
		open.add(neighbor);
	}

	/**
	 * Re parent neighbor if necessary.
	 *
	 * @param closest the closest
	 * @param neighbor the neighbor
	 */
	private void reParentNeighborIfNecessary(Point closest, Point neighbor) {
		Point originalParent = parents.get(neighbor);
		double currentCost = costToGetTo(neighbor);
		reParent(neighbor, closest);
		double reparentCost = costToGetTo(neighbor);
		
		if (reparentCost < currentCost)
			open.remove(neighbor);
		else
			reParent(neighbor, originalParent);
	}

	/**
	 * Creates the path.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the array list
	 */
	private ArrayList<Point> createPath(Point start, Point end) {
		ArrayList<Point> path = new ArrayList<Point>();

		while (!end.equals(start)) {
		    path.add(end);
		    end = parents.get(end);
		}

		Collections.reverse(path);
		return path;
	}
}
