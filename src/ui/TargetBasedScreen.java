package ui;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import game.Creature;
import game.Line;
import game.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class TargetBasedScreen.
 */
public abstract class TargetBasedScreen implements Screen {

	/** The player. */
	protected Creature player;
	
	/** The caption. */
	protected String caption;
	
	/** The sx. */
	private int sx;
	
	/** The sy. */
	private int sy;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/**
	 * Instantiates a new target based screen.
	 *
	 * @param player the player
	 * @param caption the caption
	 * @param sx the sx
	 * @param sy the sy
	 */
	public TargetBasedScreen(Creature player, String caption, int sx, int sy){
		this.player = player;
		this.caption = caption;
		this.sx = sx;
		this.sy = sy;
	}

	/* (non-Javadoc)
	 * @see ui.Screen#displayOutput(asciiPanel.AsciiPanel)
	 */
	@Override
	public void displayOutput(AsciiPanel terminal) {
		for (Point p : new Line(sx, sy, sx + x, sy + y)){
			if (p.x < 0 || p.x >= 80 || p.y < 0 || p.y >= 24)
				continue;
			
			terminal.write('*', p.x, p.y, AsciiPanel.brightMagenta);
		}
		
		terminal.clear(' ', 0, 23, 80, 1);
		terminal.write(caption, 0, 23);
	}

	/* (non-Javadoc)
	 * @see ui.Screen#respondToUserInput(java.awt.event.KeyEvent)
	 */
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		int px = x;
		int py = y;

		switch (key.getKeyCode()){
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_H: x--; break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_L: x++; break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_J: y--; break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_K: y++; break;
		case KeyEvent.VK_Y: x--; y--; break;
		case KeyEvent.VK_U: x++; y--; break;
		case KeyEvent.VK_B: x--; y++; break;
		case KeyEvent.VK_N: x++; y++; break;
		case KeyEvent.VK_ENTER: selectWorldCoordinate(player.x + x, player.y + y, sx + x, sy + y); return null; 
		case KeyEvent.VK_ESCAPE: return null;
		}
		
		if (!isAcceptable(player.x + x, player.y + y)){
			x = px;
			y = py;
		}
		
		enterWorldCoordinate(player.x + x, player.y + y, sx + x, sy + y);
		
		return this;
	}
	
	/**
	 * Checks if is acceptable.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if is acceptable
	 */
	public boolean isAcceptable(int x, int y) {
		return true;
	}

	/**
	 * Enter world coordinate.
	 *
	 * @param x the x
	 * @param y the y
	 * @param screenX the screen X
	 * @param screenY the screen Y
	 */
	public void enterWorldCoordinate(int x, int y, int screenX, int screenY) {
		
	}

	/**
	 * Select world coordinate.
	 *
	 * @param x the x
	 * @param y the y
	 * @param screenX the screen X
	 * @param screenY the screen Y
	 */
	public void selectWorldCoordinate(int x, int y, int screenX, int screenY){
	}
}
