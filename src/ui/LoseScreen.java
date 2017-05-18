package ui;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import game.Creature;

// TODO: Auto-generated Javadoc
/**
 * The Class LoseScreen.
 */
public class LoseScreen implements Screen {
	
	/** The player. */
	private Creature player;
	
	/**
	 * Instantiates a new lose screen.
	 *
	 * @param player the player
	 */
	public LoseScreen(Creature player){
		this.player = player;
	}
	
	/* (non-Javadoc)
	 * @see ui.Screen#displayOutput(asciiPanel.AsciiPanel)
	 */
	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("R.I.P.", 3);
		terminal.writeCenter(player.causeOfDeath(), 5);
		terminal.writeCenter("-- press [enter] to restart --", 22);
	}

	/* (non-Javadoc)
	 * @see ui.Screen#respondToUserInput(java.awt.event.KeyEvent)
	 */
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}
}
