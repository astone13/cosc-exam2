package ui;

import game.Creature;
import game.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class ThrowScreen.
 */
public class ThrowScreen extends InventoryBasedScreen {
	
	/** The sx. */
	private int sx;
	
	/** The sy. */
	private int sy;
	
	/**
	 * Instantiates a new throw screen.
	 *
	 * @param player the player
	 * @param sx the sx
	 * @param sy the sy
	 */
	public ThrowScreen(Creature player, int sx, int sy) {
		super(player);
		this.sx = sx;
		this.sy = sy;
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#getVerb()
	 */
	@Override
	protected String getVerb() {
		return "throw";
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#isAcceptable(game.Item)
	 */
	@Override
	protected boolean isAcceptable(Item item) {
		return true;
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#use(game.Item)
	 */
	@Override
	protected Screen use(Item item) {
		return new ThrowAtScreen(player, sx, sy, item);
	}
}
