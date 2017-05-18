package game;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerAi.
 */
public class PlayerAi extends CreatureAi {

	/** The messages. */
	private List<String> messages;
	
	/** The fov. */
	private FieldOfView fov;
	
	/**
	 * Instantiates a new player ai.
	 *
	 * @param creature the creature
	 * @param messages the messages
	 * @param fov the fov
	 */
	public PlayerAi(Creature creature, List<String> messages, FieldOfView fov) {
		super(creature);
		this.messages = messages;
		this.fov = fov;
	}

	/* (non-Javadoc)
	 * @see game.CreatureAi#onEnter(int, int, int, game.Tile)
	 */
	public void onEnter(int x, int y, int z, Tile tile){
		if (tile.isGround()){
			creature.x = x;
			creature.y = y;
			creature.z = z;
			
			Item item = creature.item(creature.x, creature.y, creature.z);
			if (item != null)
				creature.notify("There's a " + creature.nameOf(item) + " here.");
			
		} else if (tile.isDiggable()) {
			creature.dig(x, y, z);
		}
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureAi#onNotify(java.lang.String)
	 */
	public void onNotify(String message){
		messages.add(message);
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureAi#canSee(int, int, int)
	 */
	public boolean canSee(int wx, int wy, int wz) {
		return fov.isVisible(wx, wy, wz);
	}
	
	/* (non-Javadoc)
	 * @see game.CreatureAi#onGainLevel()
	 */
	public void onGainLevel(){
	}

	/* (non-Javadoc)
	 * @see game.CreatureAi#rememberedTile(int, int, int)
	 */
	public Tile rememberedTile(int wx, int wy, int wz) {
		return fov.tile(wx, wy, wz);
	}
}
