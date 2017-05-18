package game;

// TODO: Auto-generated Javadoc
/**
 * The Class Spell.
 */
public class Spell {

	/** The name. */
	private String name;
	
	/**
	 * Name.
	 *
	 * @return the string
	 */
	public String name() { return name; }

	/** The mana cost. */
	private int manaCost;
	
	/**
	 * Mana cost.
	 *
	 * @return the int
	 */
	public int manaCost() { return manaCost; }

	/** The effect. */
	private Effect effect;
	
	/**
	 * Effect.
	 *
	 * @return the effect
	 */
	public Effect effect() { return new Effect(effect); }

	/**
	 * Requires target.
	 *
	 * @return true, if successful
	 */
	public boolean requiresTarget() { return true; }
	
	/**
	 * Instantiates a new spell.
	 *
	 * @param name the name
	 * @param manaCost the mana cost
	 * @param effect the effect
	 */
	public Spell(String name, int manaCost, Effect effect){
		this.name = name;
		this.manaCost = manaCost;
		this.effect = effect;
	}
}
