package ui;

import game.Creature;
import game.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class DropScreen.
 */
public class DropScreen extends InventoryBasedScreen {

	/**
	 * Instantiates a new drop screen.
	 *
	 * @param player the player
	 */
	public DropScreen(Creature player) {
		super(player);
	}

	/* (non-Javadoc)
	 * @see ui.InventoryBasedScreen#getVerb()
	 */
	@Override
	protected String getVerb() { 
		return "drop"; 
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
		player.drop(item); 
		return null;
	}
}
