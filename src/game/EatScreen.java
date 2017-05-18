package game;

import ui.InventoryBasedScreen;
import ui.Screen;

// TODO: Auto-generated Javadoc
/**
 * The Class EatScreen.
 */
public class EatScreen extends InventoryBasedScreen {

	/**
	 * Instantiates a new eat screen.
	 *
	 * @param player the player
	 */
	public EatScreen(Creature player) {
		super(player);
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#getVerb()
	 */
	@Override
	protected String getVerb() {
		return "eat";
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#isAcceptable(game.Item)
	 */
	@Override
	protected boolean isAcceptable(Item item) {
		return item.foodValue() != 0;
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#use(game.Item)
	 */
	@Override
	protected Screen use(Item item) {
		player.eat(item);
		return null;
	}
}
