package game;

// TODO: Auto-generated Javadoc
/**
 * The Class FieldOfView.
 */
public class FieldOfView {
	
	/** The world. */
	private World world;
	
	/** The depth. */
	private int depth;
	
	/** The visible. */
	private boolean[][] visible;
	
	/**
	 * Checks if is visible.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return true, if is visible
	 */
	public boolean isVisible(int x, int y, int z){
		return z == depth && x >= 0 && y >= 0 && x < visible.length && y < visible[0].length && visible[x][y];
	}
	
	/** The tiles. */
	private Tile[][][] tiles;
	
	/**
	 * Tile.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the tile
	 */
	public Tile tile(int x, int y, int z){
		return tiles[x][y][z];
	}
	
	/**
	 * Instantiates a new field of view.
	 *
	 * @param world the world
	 */
	public FieldOfView(World world){
		this.world = world;
		this.visible = new boolean[world.width()][world.height()];
		this.tiles = new Tile[world.width()][world.height()][world.depth()];
		
		for (int x = 0; x < world.width(); x++){
			for (int y = 0; y < world.height(); y++){
				for (int z = 0; z < world.depth(); z++){
					tiles[x][y][z] = Tile.UNKNOWN;
				}
			}
		}
	}
	
	/**
	 * Update.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 * @param r the r
	 */
	public void update(int wx, int wy, int wz, int r){
		depth = wz;
		visible = new boolean[world.width()][world.height()];
		
		for (int x = -r; x < r; x++){
			for (int y = -r; y < r; y++){
				if (x*x + y*y > r*r)
					continue;
				
				if (wx + x < 0 || wx + x >= world.width() || wy + y < 0 || wy + y >= world.height())
					continue;
				
				for (Point p : new Line(wx, wy, wx + x, wy + y)){
					Tile tile = world.tile(p.x, p.y, wz);
					visible[p.x][p.y] = true;
					tiles[p.x][p.y][wz] = tile; 
					
					if (!tile.isGround())
						break;
				}
			}
		}
	}
}
