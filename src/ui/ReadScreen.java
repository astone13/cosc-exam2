package ui;

import game.Creature;
import game.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadScreen.
 */
public class ReadScreen extends InventoryBasedScreen {

	/** The sx. */
	private int sx;
	
	/** The sy. */
	private int sy;
	
	/**
	 * Instantiates a new read screen.
	 *
	 * @param player the player
	 * @param sx the sx
	 * @param sy the sy
	 */
	public ReadScreen(Creature player, int sx, int sy) {
		super(player);
		this.sx = sx;
		this.sy = sy;
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#getVerb()
	 */
	@Override
	protected String getVerb() {
		return "read";
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#isAcceptable(game.Item)
	 */
	@Override
	protected boolean isAcceptable(Item item) {
		return !item.writtenSpells().isEmpty();
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#use(game.Item)
	 */
	@Override
	protected Screen use(Item item) {
		return new ReadSpellScreen(player, sx, sy, item);
	}

}
