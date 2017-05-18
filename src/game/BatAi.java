package game;

// TODO: Auto-generated Javadoc
/**
 * The Class BatAi.
 */
public class BatAi extends CreatureAi {

	/**
	 * Instantiates a new bat ai.
	 *
	 * @param creature the creature
	 */
	public BatAi(Creature creature) {
		super(creature);
	}

	/* (non-Javadoc)
	 * @see game.CreatureAi#onUpdate()
	 */
	public void onUpdate(){
		wander();
		wander();
	}
}
