package ui;

import game.Creature;
import game.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class EquipScreen.
 */
public class EquipScreen extends InventoryBasedScreen {

	/**
	 * Instantiates a new equip screen.
	 *
	 * @param player the player
	 */
	public EquipScreen(Creature player) {
		super(player);
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#getVerb()
	 */
	protected String getVerb() {
		return "wear or wield";
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#isAcceptable(game.Item)
	 */
	protected boolean isAcceptable(Item item) {
		return item.attackValue() > 0 || item.defenseValue() > 0;
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#use(game.Item)
	 */
	protected Screen use(Item item) {
		player.equip(item);
		return null;
	}
}
