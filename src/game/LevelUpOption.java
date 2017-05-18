package game;

// TODO: Auto-generated Javadoc
/**
 * The Class LevelUpOption.
 */
public abstract class LevelUpOption {
	
	/** The name. */
	private String name;
	
	/**
	 * Name.
	 *
	 * @return the string
	 */
	public String name() { return name; }
	
	/**
	 * Instantiates a new level up option.
	 *
	 * @param name the name
	 */
	public LevelUpOption(String name){
		this.name = name;
	}
	
	/**
	 * Invoke.
	 *
	 * @param creature the creature
	 */
	public abstract void invoke(Creature creature);
}
