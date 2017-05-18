package game;

// TODO: Auto-generated Javadoc
/**
 * The Class Effect.
 */
public class Effect {
	
	/** The duration. */
	protected int duration;
	
	/**
	 * Checks if is done.
	 *
	 * @return true, if is done
	 */
	public boolean isDone() { return duration < 1; }

	/**
	 * Instantiates a new effect.
	 *
	 * @param duration the duration
	 */
	public Effect(int duration){
		this.duration = duration;
	}
	
	/**
	 * Instantiates a new effect.
	 *
	 * @param other the other
	 */
	public Effect(Effect other){
		this.duration = other.duration; 
	}
	
	/**
	 * Update.
	 *
	 * @param creature the creature
	 */
	public void update(Creature creature){
		duration--;
	}
	
	/**
	 * Start.
	 *
	 * @param creature the creature
	 */
	public void start(Creature creature){
		
	}
	
	/**
	 * End.
	 *
	 * @param creature the creature
	 */
	public void end(Creature creature){
		
	}
}
