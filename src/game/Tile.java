package game;

import java.awt.Color;
import asciiPanel.AsciiPanel;

// TODO: Auto-generated Javadoc
/**
 * The Enum Tile.
 */
public enum Tile {
	
	/** The floor. */
	FLOOR((char)250, AsciiPanel.yellow, "A dirt and rock cave floor."),
	
	/** The wall. */
	WALL((char)177, AsciiPanel.yellow, "A dirt and rock cave wall."),
	
	/** The bounds. */
	BOUNDS('x', AsciiPanel.brightBlack, "Beyond the edge of the world."),
	
	/** The stairs down. */
	STAIRS_DOWN('>', AsciiPanel.white, "A stone staircase that goes down."),
	
	/** The stairs up. */
	STAIRS_UP('<', AsciiPanel.white, "A stone staircase that goes up."),
	
	/** The unknown. */
	UNKNOWN(' ', AsciiPanel.white, "(unknown)");
	
	/** The glyph. */
	private char glyph;
	
	/**
	 * Glyph.
	 *
	 * @return the char
	 */
	public char glyph() { return glyph; }
	
	/** The color. */
	private Color color;
	
	/**
	 * Color.
	 *
	 * @return the color
	 */
	public Color color() { return color; }

	/** The description. */
	private String description;
	
	/**
	 * Details.
	 *
	 * @return the string
	 */
	public String details(){ return description; }
	
	/**
	 * Instantiates a new tile.
	 *
	 * @param glyph the glyph
	 * @param color the color
	 * @param description the description
	 */
	Tile(char glyph, Color color, String description){
		this.glyph = glyph;
		this.color = color;
		this.description = description;
	}

	/**
	 * Checks if is ground.
	 *
	 * @return true, if is ground
	 */
	public boolean isGround() {
		return this != WALL && this != BOUNDS;
	}

	/**
	 * Checks if is diggable.
	 *
	 * @return true, if is diggable
	 */
	public boolean isDiggable() {
		return this == Tile.WALL;
	}
}
