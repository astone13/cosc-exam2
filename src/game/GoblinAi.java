package game;


// TODO: Auto-generated Javadoc
/**
 * The Class GoblinAi.
 */
public class GoblinAi extends CreatureAi {
	
	/** The player. */
	private Creature player;
	
	/**
	 * Instantiates a new goblin ai.
	 *
	 * @param creature the creature
	 * @param player the player
	 */
	public GoblinAi(Creature creature, Creature player) {
		super(creature);
		this.player = player;
	}

	/* (non-Javadoc)
	 * @see game.CreatureAi#onUpdate()
	 */
	public void onUpdate(){
		if (canUseBetterEquipment())
			useBetterEquipment();
		else if (canRangedWeaponAttack(player))
			creature.rangedWeaponAttack(player);
		else if (canThrowAt(player))
			creature.throwItem(getWeaponToThrow(), player.x, player.y, player.z);
		else if (creature.canSee(player.x, player.y, player.z))
			hunt(player);
		else if (canPickup())
			creature.pickup();
		else
			wander();
	}
}
