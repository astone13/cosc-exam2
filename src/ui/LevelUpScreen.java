package ui;

import java.awt.event.KeyEvent;
import java.util.List;

import asciiPanel.AsciiPanel;
import game.Creature;
import game.LevelUpController;

// TODO: Auto-generated Javadoc
/**
 * The Class LevelUpScreen.
 */
public class LevelUpScreen implements Screen {
	
	/** The controller. */
	private LevelUpController controller;
	
	/** The player. */
	private Creature player;
	
	/** The picks. */
	private int picks;
	
	/**
	 * Instantiates a new level up screen.
	 *
	 * @param player the player
	 * @param picks the picks
	 */
	public LevelUpScreen(Creature player, int picks){
		this.controller = new LevelUpController();
		this.player = player;
		this.picks = picks;
	}
	
	/* (non-Javadoc)
	 * @see ui.Screen#displayOutput(asciiPanel.AsciiPanel)
	 */
	@Override
	public void displayOutput(AsciiPanel terminal) {
		List<String> options = controller.getLevelUpOptions();
		
		int y = 5;
		terminal.clear(' ', 5, y, 30, options.size() + 2);
		terminal.write("   Choose a level up bonus    ", 5, y++);
		terminal.write("------------------------------", 5, y++);
		
		for (int i = 0; i < options.size(); i++){
			terminal.write(String.format("[%d] %s", i+1, options.get(i)), 5, y++);
		}
	}

	/* (non-Javadoc)
	 * @see ui.Screen#respondToUserInput(java.awt.event.KeyEvent)
	 */
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		List<String> options = controller.getLevelUpOptions();
		String chars = "";
		
		for (int i = 0; i < options.size(); i++){
			chars = chars + Integer.toString(i+1);
		}
		
		int i = chars.indexOf(key.getKeyChar());
		
		if (i < 0)
			return this;
		
		controller.getLevelUpOption(options.get(i)).invoke(player);
		
		if (--picks < 1)
			return null;
		else
			return this;
	}
}
