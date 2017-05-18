package ui;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import asciiPanel.AsciiPanel;
import game.Creature;
import game.Item;
import game.Spell;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadSpellScreen.
 */
public class ReadSpellScreen implements Screen {

	/** The player. */
	protected Creature player;
	
	/** The letters. */
	private String letters;
	
	/** The item. */
	private Item item;
	
	/** The sx. */
	private int sx;
	
	/** The sy. */
	private int sy;
	
	/**
	 * Instantiates a new read spell screen.
	 *
	 * @param player the player
	 * @param sx the sx
	 * @param sy the sy
	 * @param item the item
	 */
	public ReadSpellScreen(Creature player, int sx, int sy, Item item){
		this.player = player;
		this.letters = "abcdefghijklmnopqrstuvwxyz";
		this.item = item;
		this.sx = sx;
		this.sy = sy;
	}
	
	/* (non-Javadoc)
	 * @see ui.Screen#displayOutput(asciiPanel.AsciiPanel)
	 */
	public void displayOutput(AsciiPanel terminal) {
		ArrayList<String> lines = getList();
		
		int y = 23 - lines.size();
		int x = 4;

		if (lines.size() > 0)
			terminal.clear(' ', x, y, 20, lines.size());
		
		for (String line : lines){
			terminal.write(line, x, y++);
		}
		
		terminal.clear(' ', 0, 23, 80, 1);
		terminal.write("What would you like to read?", 2, 23);
		
		terminal.repaint();
	}
	
	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	private ArrayList<String> getList() {
		ArrayList<String> lines = new ArrayList<String>();
		
		for (int i = 0; i < item.writtenSpells().size(); i++){
			Spell spell = item.writtenSpells().get(i);
			
			String line = letters.charAt(i) + " - " + spell.name() + " (" + spell.manaCost() + " mana)";
			
			lines.add(line);
		}
		return lines;
	}

	/* (non-Javadoc)
	 * @see ui.Screen#respondToUserInput(java.awt.event.KeyEvent)
	 */
	public Screen respondToUserInput(KeyEvent key) {
		char c = key.getKeyChar();

		Item[] items = player.inventory().getItems();
		
		if (letters.indexOf(c) > -1 
				&& items.length > letters.indexOf(c)
				&& items[letters.indexOf(c)] != null) {
			return use(item.writtenSpells().get(letters.indexOf(c)));
		} else if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			return null;
		} else {
			return this;
		}
	}

	/**
	 * Use.
	 *
	 * @param spell the spell
	 * @return the screen
	 */
	protected Screen use(Spell spell){
		if (spell.requiresTarget())
			return new CastSpellScreen(player, "", sx, sy, spell);
		
		player.castSpell(spell, player.x, player.y);
		return null;
	}
}
