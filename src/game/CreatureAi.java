package game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class CreatureAi.
 */
public class CreatureAi {
	
	/** The creature. */
	protected Creature creature;
	
	/** The item names. */
	private Map<String, String> itemNames;
	
	/**
	 * Instantiates a new creature ai.
	 *
	 * @param creature the creature
	 */
	public CreatureAi(Creature creature){
		this.creature = creature;
		this.creature.setCreatureAi(this);
		this.itemNames = new HashMap<String, String>();
	}
	
	/**
	 * Gets the name.
	 *
	 * @param item the item
	 * @return the name
	 */
	public String getName(Item item){
		String name = itemNames.get(item.name());
		
		return name == null ? item.appearance() : name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param item the item
	 * @param name the name
	 */
	public void setName(Item item, String name){
		itemNames.put(item.name(), name);
	}
	
	/**
	 * On enter.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @param tile the tile
	 */
	public void onEnter(int x, int y, int z, Tile tile){
		if (tile.isGround()){
			creature.x = x;
			creature.y = y;
			creature.z = z;
		} else {
			creature.doAction("bump into a wall");
		}
	}
	
	/**
	 * On update.
	 */
	public void onUpdate(){
	}
	
	/**
	 * On notify.
	 *
	 * @param message the message
	 */
	public void onNotify(String message){
	}

	/**
	 * Can see.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 * @return true, if successful
	 */
	public boolean canSee(int wx, int wy, int wz) {
		if (creature.z != wz)
			return false;
		
		if ((creature.x-wx)*(creature.x-wx) + (creature.y-wy)*(creature.y-wy) > creature.visionRadius()*creature.visionRadius())
			return false;
		
		for (Point p : new Line(creature.x, creature.y, wx, wy)){
			if (creature.realTile(p.x, p.y, wz).isGround() || p.x == wx && p.y == wy)
				continue;
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Wander.
	 */
	public void wander(){
		int mx = (int)(Math.random() * 3) - 1;
		int my = (int)(Math.random() * 3) - 1;
		
		Creature other = creature.creature(creature.x + mx, creature.y + my, creature.z);
		
		if (other != null && other.name().equals(creature.name()) 
				|| !creature.tile(creature.x+mx, creature.y+my, creature.z).isGround())
			return;
		else
			creature.moveBy(mx, my, 0);
	}

	/**
	 * On gain level.
	 */
	public void onGainLevel() {
		new LevelUpController().autoLevelUp(creature);
	}

	/**
	 * Remembered tile.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 * @return the tile
	 */
	public Tile rememberedTile(int wx, int wy, int wz) {
		return Tile.UNKNOWN;
	}

	/**
	 * Can throw at.
	 *
	 * @param other the other
	 * @return true, if successful
	 */
	protected boolean canThrowAt(Creature other) {
		return creature.canSee(other.x, other.y, other.z)
			&& getWeaponToThrow() != null;
	}

	/**
	 * Gets the weapon to throw.
	 *
	 * @return the weapon to throw
	 */
	protected Item getWeaponToThrow() {
		Item toThrow = null;
		
		for (Item item : creature.inventory().getItems()){
			if (item == null || creature.weapon() == item || creature.armor() == item)
				continue;
			
			if (toThrow == null || item.thrownAttackValue() > toThrow.attackValue())
				toThrow = item;
		}
		
		return toThrow;
	}

	/**
	 * Can ranged weapon attack.
	 *
	 * @param other the other
	 * @return true, if successful
	 */
	protected boolean canRangedWeaponAttack(Creature other) {
		return creature.weapon() != null
		    && creature.weapon().rangedAttackValue() > 0
		    && creature.canSee(other.x, other.y, other.z);
	}

	/**
	 * Can pickup.
	 *
	 * @return true, if successful
	 */
	protected boolean canPickup() {
		return creature.item(creature.x, creature.y, creature.z) != null
			&& !creature.inventory().isFull();
	}

	/**
	 * Hunt.
	 *
	 * @param target the target
	 */
	public void hunt(Creature target) {
		List<Point> points = new Path(creature, target.x, target.y).points();
		
		int mx = points.get(0).x - creature.x;
		int my = points.get(0).y - creature.y;
		
		creature.moveBy(mx, my, 0);
	}

	/**
	 * Can use better equipment.
	 *
	 * @return true, if successful
	 */
	protected boolean canUseBetterEquipment() {
		int currentWeaponRating = creature.weapon() == null ? 0 : creature.weapon().attackValue() + creature.weapon().rangedAttackValue();
		int currentArmorRating = creature.armor() == null ? 0 : creature.armor().defenseValue();
		
		for (Item item : creature.inventory().getItems()){
			if (item == null)
				continue;
			
			boolean isArmor = item.attackValue() + item.rangedAttackValue() < item.defenseValue();
			
			if (item.attackValue() + item.rangedAttackValue() > currentWeaponRating
					|| isArmor && item.defenseValue() > currentArmorRating)
				return true;
		}
		
		return false;
	}

	/**
	 * Use better equipment.
	 */
	protected void useBetterEquipment() {
		int currentWeaponRating = creature.weapon() == null ? 0 : creature.weapon().attackValue() + creature.weapon().rangedAttackValue();
		int currentArmorRating = creature.armor() == null ? 0 : creature.armor().defenseValue();
		
		for (Item item : creature.inventory().getItems()){
			if (item == null)
				continue;
			
			boolean isArmor = item.attackValue() + item.rangedAttackValue() < item.defenseValue();
			
			if (item.attackValue() + item.rangedAttackValue() > currentWeaponRating
					|| isArmor && item.defenseValue() > currentArmorRating) {
				creature.equip(item);
			}
		}
	}
}
