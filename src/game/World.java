package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class World.
 */
public class World {
	
	/** The tiles. */
	private Tile[][][] tiles;
	
	/** The items. */
	private Item[][][] items;
	
	/** The width. */
	private int width;
	
	/**
	 * Width.
	 *
	 * @return the int
	 */
	public int width() { return width; }
	
	/** The height. */
	private int height;
	
	/**
	 * Height.
	 *
	 * @return the int
	 */
	public int height() { return height; }

	/** The depth. */
	private int depth;
	
	/**
	 * Depth.
	 *
	 * @return the int
	 */
	public int depth() { return depth; }
	
	/** The creatures. */
	private List<Creature> creatures;
	
	/**
	 * Instantiates a new world.
	 *
	 * @param tiles the tiles
	 */
	public World(Tile[][][] tiles){
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
		this.depth = tiles[0][0].length;
		this.creatures = new ArrayList<Creature>();
		this.items = new Item[width][height][depth];
	}

	/**
	 * Creature.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the creature
	 */
	public Creature creature(int x, int y, int z){
		for (Creature c : creatures){
			if (c.x == x && c.y == y && c.z == z)
				return c;
		}
		return null;
	}
	
	/**
	 * Tile.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the tile
	 */
	public Tile tile(int x, int y, int z){
		if (x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
			return Tile.BOUNDS;
		else
			return tiles[x][y][z];
	}
	
	/**
	 * Glyph.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the char
	 */
	public char glyph(int x, int y, int z){
		Creature creature = creature(x, y, z);
		if (creature != null)
			return creature.glyph();
		
		if (item(x,y,z) != null)
			return item(x,y,z).glyph();
		
		return tile(x, y, z).glyph();
	}
	
	/**
	 * Color.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the color
	 */
	public Color color(int x, int y, int z){
		Creature creature = creature(x, y, z);
		if (creature != null)
			return creature.color();
		
		if (item(x,y,z) != null)
			return item(x,y,z).color();
		
		return tile(x, y, z).color();
	}

	/**
	 * Dig.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public void dig(int x, int y, int z) {
		if (tile(x, y, z).isDiggable())
			tiles[x][y][z] = Tile.FLOOR;
	}
	
	/**
	 * Adds the at empty location.
	 *
	 * @param creature the creature
	 * @param z the z
	 */
	public void addAtEmptyLocation(Creature creature, int z){
		int x;
		int y;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		while (!tile(x,y,z).isGround() || creature(x,y,z) != null);
		
		creature.x = x;
		creature.y = y;
		creature.z = z;
		creatures.add(creature);
	}
	
	/**
	 * Update.
	 */
	public void update(){
		List<Creature> toUpdate = new ArrayList<Creature>(creatures);
		for (Creature creature : toUpdate){
			creature.update();
		}
	}

	/**
	 * Removes the.
	 *
	 * @param other the other
	 */
	public void remove(Creature other) {
		creatures.remove(other);
	}
	
	/**
	 * Removes the.
	 *
	 * @param item the item
	 */
	public void remove(Item item) {
		for (int x = 0; x < width; x++){
			for (int y = 0; y < height; y++){
				for (int z = 0; z < depth; z++){
					if (items[x][y][z] == item) {
						items[x][y][z] = null;
						return;
					}
				}	
			}	
		}
	}
	
	/**
	 * Item.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the item
	 */
	public Item item(int x, int y, int z){
		return items[x][y][z];
	}
	
	/**
	 * Adds the at empty location.
	 *
	 * @param item the item
	 * @param depth the depth
	 */
	public void addAtEmptyLocation(Item item, int depth) {
		int x;
		int y;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		while (!tile(x,y,depth).isGround() || item(x,y,depth) != null);
		
		items[x][y][depth] = item;
	}

	/**
	 * Removes the.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public void remove(int x, int y, int z) {
		items[x][y][z] = null;
	}

	/**
	 * Adds the at empty space.
	 *
	 * @param item the item
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return true, if successful
	 */
	public boolean addAtEmptySpace(Item item, int x, int y, int z){
		if (item == null)
			return true;
		
		List<Point> points = new ArrayList<Point>();
		List<Point> checked = new ArrayList<Point>();
		
		points.add(new Point(x, y, z));
		
		while (!points.isEmpty()){
			Point p = points.remove(0);
			checked.add(p);
			
			if (!tile(p.x, p.y, p.z).isGround())
				continue;
				
			if (items[p.x][p.y][p.z] == null){
				items[p.x][p.y][p.z] = item;
				Creature c = this.creature(p.x, p.y, p.z);
				if (c != null)
					c.notify("A %s lands between your feet.", c.nameOf(item));
				return true;
			} else {
				List<Point> neighbors = p.neighbors8();
				neighbors.removeAll(checked);
				points.addAll(neighbors);
			}
		}
		return false;
	}

	/**
	 * Adds the.
	 *
	 * @param pet the pet
	 */
	public void add(Creature pet) {
		creatures.add(pet);
	}
}
