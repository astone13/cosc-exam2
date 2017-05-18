package ui;

import game.Creature;
import game.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class QuaffScreen.
 */
public class QuaffScreen extends InventoryBasedScreen {

	/**
	 * Instantiates a new quaff screen.
	 *
	 * @param player the player
	 */
	public QuaffScreen(Creature player) {
		super(player);
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#getVerb()
	 */
	@Override
	protected String getVerb() {
		return "quaff";
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#isAcceptable(game.Item)
	 */
	@Override
	protected boolean isAcceptable(Item item) {
		return item.quaffEffect() != null;
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#use(game.Item)
	 */
	@Override
	protected Screen use(Item item) {
		player.quaff(item);
		return null;
	}

}
