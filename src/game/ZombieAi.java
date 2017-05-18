package game;


// TODO: Auto-generated Javadoc
/**
 * The Class ZombieAi.
 */
public class ZombieAi extends CreatureAi {
	
	/** The player. */
	private Creature player;
	
	/**
	 * Instantiates a new zombie ai.
	 *
	 * @param creature the creature
	 * @param player the player
	 */
	public ZombieAi(Creature creature, Creature player) {
		super(creature);
		this.player = player;
	}

	/* (non-Javadoc)
	 * @see game.CreatureAi#onUpdate()
	 */
	public void onUpdate(){
		if (Math.random() < 0.2)
			return;
		
		if (creature.canSee(player.x, player.y, player.z))
			hunt(player);
		else
			wander();
	}
}
