package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public class Item {

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

	/** The name. */
	private String name;
	
	/**
	 * Name.
	 *
	 * @return the string
	 */
	public String name() { return name; }

	/** The appearance. */
	private String appearance;
	
	/**
	 * Appearance.
	 *
	 * @return the string
	 */
	public String appearance() { return appearance; }
	
	/** The food value. */
	private int foodValue;
	
	/**
	 * Food value.
	 *
	 * @return the int
	 */
	public int foodValue() { return foodValue; }
	
	/**
	 * Modify food value.
	 *
	 * @param amount the amount
	 */
	public void modifyFoodValue(int amount) { foodValue += amount; }

	/** The attack value. */
	private int attackValue;
	
	/**
	 * Attack value.
	 *
	 * @return the int
	 */
	public int attackValue() { return attackValue; }
	
	/**
	 * Modify attack value.
	 *
	 * @param amount the amount
	 */
	public void modifyAttackValue(int amount) { attackValue += amount; }

	/** The defense value. */
	private int defenseValue;
	
	/**
	 * Defense value.
	 *
	 * @return the int
	 */
	public int defenseValue() { return defenseValue; }
	
	/**
	 * Modify defense value.
	 *
	 * @param amount the amount
	 */
	public void modifyDefenseValue(int amount) { defenseValue += amount; }

	/** The thrown attack value. */
	private int thrownAttackValue;
	
	/**
	 * Thrown attack value.
	 *
	 * @return the int
	 */
	public int thrownAttackValue() { return thrownAttackValue; }
	
	/**
	 * Modify thrown attack value.
	 *
	 * @param amount the amount
	 */
	public void modifyThrownAttackValue(int amount) { thrownAttackValue += amount; }

	/** The ranged attack value. */
	private int rangedAttackValue;
	
	/**
	 * Ranged attack value.
	 *
	 * @return the int
	 */
	public int rangedAttackValue() { return rangedAttackValue; }
	
	/**
	 * Modify ranged attack value.
	 *
	 * @param amount the amount
	 */
	public void modifyRangedAttackValue(int amount) { rangedAttackValue += amount; }
	
	/** The quaff effect. */
	private Effect quaffEffect;
	
	/**
	 * Quaff effect.
	 *
	 * @return the effect
	 */
	public Effect quaffEffect() { return quaffEffect; }
	
	/**
	 * Sets the quaff effect.
	 *
	 * @param effect the new quaff effect
	 */
	public void setQuaffEffect(Effect effect) { this.quaffEffect = effect; }
	
	/** The written spells. */
	private List<Spell> writtenSpells;
	
	/**
	 * Written spells.
	 *
	 * @return the list
	 */
	public List<Spell> writtenSpells() { return writtenSpells; }
	
	/**
	 * Adds the written spell.
	 *
	 * @param name the name
	 * @param manaCost the mana cost
	 * @param effect the effect
	 */
	public void addWrittenSpell(String name, int manaCost, Effect effect){
		writtenSpells.add(new Spell(name, manaCost, effect));
	}
	
	/**
	 * Instantiates a new item.
	 *
	 * @param glyph the glyph
	 * @param color the color
	 * @param name the name
	 * @param appearance the appearance
	 */
	public Item(char glyph, Color color, String name, String appearance){
		this.glyph = glyph;
		this.color = color;
		this.name = name;
		this.appearance = appearance == null ? name : appearance;
		this.thrownAttackValue = 1;
		this.writtenSpells = new ArrayList<Spell>();
	}
	
	/**
	 * Details.
	 *
	 * @return the string
	 */
	public String details() {
		String details = "";
		
		if (attackValue != 0)
			details += "  attack:" + attackValue;

		if (thrownAttackValue != 1)
			details += "  thrown:" + thrownAttackValue;
		
		if (rangedAttackValue > 0)
			details += "  ranged:" + rangedAttackValue;
		
		if (defenseValue != 0)
			details += "  defense:" + defenseValue;

		if (foodValue != 0)
			details += "  food:" + foodValue;
		
		return details;
	}
}
