package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Line.
 */
public class Line implements Iterable<Point> {
	
	/** The points. */
	private List<Point> points;
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public List<Point> getPoints() { return points; }
	
	/**
	 * Instantiates a new line.
	 *
	 * @param x0 the x 0
	 * @param y0 the y 0
	 * @param x1 the x 1
	 * @param y1 the y 1
	 */
	public Line(int x0, int y0, int x1, int y1) {
		points = new ArrayList<Point>();
		
		int dx = Math.abs(x1-x0);
		int dy = Math.abs(y1-y0);
		
		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;
		int err = dx-dy;
		
		while (true){
			points.add(new Point(x0, y0, 0));
			
			if (x0==x1 && y0==y1)
				break;
			
			int e2 = err * 2;
			if (e2 > -dx) {
				err -= dy;
				x0 += sx;
			}
			if (e2 < dx){
				err += dx;
				y0 += sy;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Point> iterator() {
		return points.iterator();
	}
}
