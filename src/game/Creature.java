package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class Creature.
 */
public class Creature {
	
	/** The world. */
	private World world;
	
	/** The x. */
	public int x;
	
	/** The y. */
	public int y;
	
	/** The z. */
	public int z;
	
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

	/** The ai. */
	private CreatureAi ai;
	
	/**
	 * Sets the creature ai.
	 *
	 * @param ai the new creature ai
	 */
	public void setCreatureAi(CreatureAi ai) { this.ai = ai; }
	
	/** The max hp. */
	private int maxHp;
	
	/**
	 * Max hp.
	 *
	 * @return the int
	 */
	public int maxHp() { return maxHp; }
	
	/**
	 * Modify max hp.
	 *
	 * @param amount the amount
	 */
	public void modifyMaxHp(int amount) { maxHp += amount; }
	
	/** The hp. */
	private int hp;
	
	/**
	 * Hp.
	 *
	 * @return the int
	 */
	public int hp() { return hp; }
	
	/** The attack value. */
	private int attackValue;
	
	/**
	 * Modify attack value.
	 *
	 * @param value the value
	 */
	public void modifyAttackValue(int value) { attackValue += value; }
	
	/**
	 * Attack value.
	 *
	 * @return the int
	 */
	public int attackValue() { 
		return attackValue
			+ (weapon == null ? 0 : weapon.attackValue())
			+ (armor == null ? 0 : armor.attackValue());
	}

	/** The defense value. */
	private int defenseValue;
	
	/**
	 * Modify defense value.
	 *
	 * @param value the value
	 */
	public void modifyDefenseValue(int value) { defenseValue += value; }
	
	/**
	 * Defense value.
	 *
	 * @return the int
	 */
	public int defenseValue() { 
		return defenseValue
			+ (weapon == null ? 0 : weapon.defenseValue())
			+ (armor == null ? 0 : armor.defenseValue());
	}

	/** The vision radius. */
	private int visionRadius;
	
	/**
	 * Modify vision radius.
	 *
	 * @param value the value
	 */
	public void modifyVisionRadius(int value) { visionRadius += value; }
	
	/**
	 * Vision radius.
	 *
	 * @return the int
	 */
	public int visionRadius() { return visionRadius; }

	/** The name. */
	private String name;
	
	/**
	 * Name.
	 *
	 * @return the string
	 */
	public String name() { return name; }

	/** The inventory. */
	private Inventory inventory;
	
	/**
	 * Inventory.
	 *
	 * @return the inventory
	 */
	public Inventory inventory() { return inventory; }

	/** The max food. */
	private int maxFood;
	
	/**
	 * Max food.
	 *
	 * @return the int
	 */
	public int maxFood() { return maxFood; }
	
	/** The food. */
	private int food;
	
	/**
	 * Food.
	 *
	 * @return the int
	 */
	public int food() { return food; }
	
	/** The weapon. */
	private Item weapon;
	
	/**
	 * Weapon.
	 *
	 * @return the item
	 */
	public Item weapon() { return weapon; }
	
	/** The armor. */
	private Item armor;
	
	/**
	 * Armor.
	 *
	 * @return the item
	 */
	public Item armor() { return armor; }
	
	/** The xp. */
	private int xp;
	
	/**
	 * Xp.
	 *
	 * @return the int
	 */
	public int xp() { return xp; }
	
	/**
	 * Modify xp.
	 *
	 * @param amount the amount
	 */
	public void modifyXp(int amount) { 
		xp += amount;
		
		notify("You %s %d xp.", amount < 0 ? "lose" : "gain", amount);
		
		while (xp > (int)(Math.pow(level, 1.75) * 25)) {
			level++;
			doAction("advance to level %d", level);
			ai.onGainLevel();
			modifyHp(level * 2, "Died from having a negative level?");
		}
	}
	
	/** The level. */
	private int level;
	
	/**
	 * Level.
	 *
	 * @return the int
	 */
	public int level() { return level; }
	
	/** The regen hp cooldown. */
	private int regenHpCooldown;
	
	/** The regen hp per 1000. */
	private int regenHpPer1000;
	
	/**
	 * Modify regen hp per 1000.
	 *
	 * @param amount the amount
	 */
	public void modifyRegenHpPer1000(int amount) { regenHpPer1000 += amount; }
	
	/** The effects. */
	private List<Effect> effects;
	
	/**
	 * Effects.
	 *
	 * @return the list
	 */
	public List<Effect> effects(){ return effects; }
	
	/** The max mana. */
	private int maxMana;
	
	/**
	 * Max mana.
	 *
	 * @return the int
	 */
	public int maxMana() { return maxMana; }
	
	/**
	 * Modify max mana.
	 *
	 * @param amount the amount
	 */
	public void modifyMaxMana(int amount) { maxMana += amount; }
	
	/** The mana. */
	private int mana;
	
	/**
	 * Mana.
	 *
	 * @return the int
	 */
	public int mana() { return mana; }
	
	/**
	 * Modify mana.
	 *
	 * @param amount the amount
	 */
	public void modifyMana(int amount) { mana = Math.max(0, Math.min(mana+amount, maxMana)); }
	
	/** The regen mana cooldown. */
	private int regenManaCooldown;
	
	/** The regen mana per 1000. */
	private int regenManaPer1000;
	
	/**
	 * Modify regen mana per 1000.
	 *
	 * @param amount the amount
	 */
	public void modifyRegenManaPer1000(int amount) { regenManaPer1000 += amount; }
	
	/** The cause of death. */
	private String causeOfDeath;
	
	/**
	 * Cause of death.
	 *
	 * @return the string
	 */
	public String causeOfDeath() { return causeOfDeath; }
	
	/**
	 * Instantiates a new creature.
	 *
	 * @param world the world
	 * @param glyph the glyph
	 * @param color the color
	 * @param name the name
	 * @param maxHp the max hp
	 * @param attack the attack
	 * @param defense the defense
	 */
	public Creature(World world, char glyph, Color color, String name, int maxHp, int attack, int defense){
		this.world = world;
		this.glyph = glyph;
		this.color = color;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.attackValue = attack;
		this.defenseValue = defense;
		this.visionRadius = 9;
		this.name = name;
		this.inventory = new Inventory(20);
		this.maxFood = 1000;
		this.food = maxFood / 3 * 2;
		this.level = 1;
		this.regenHpPer1000 = 10;
		this.effects = new ArrayList<Effect>();
		this.maxMana = 5;
		this.mana = maxMana;
		this.regenManaPer1000 = 20;
	}
	
	/**
	 * Move by.
	 *
	 * @param mx the mx
	 * @param my the my
	 * @param mz the mz
	 */
	public void moveBy(int mx, int my, int mz){
		if (mx==0 && my==0 && mz==0)
			return;
		
		Tile tile = world.tile(x+mx, y+my, z+mz);
		
		if (mz == -1){
			if (tile == Tile.STAIRS_DOWN) {
				doAction("walk up the stairs to level %d", z+mz+1);
			} else {
				doAction("try to go up but are stopped by the cave ceiling");
				return;
			}
		} else if (mz == 1){
			if (tile == Tile.STAIRS_UP) {
				doAction("walk down the stairs to level %d", z+mz+1);
			} else {
				doAction("try to go down but are stopped by the cave floor");
				return;
			}
		}
		
		Creature other = world.creature(x+mx, y+my, z+mz);
		
		modifyFood(-1);
		
		if (other == null)
			ai.onEnter(x+mx, y+my, z+mz, tile);
		else
			meleeAttack(other);
	}

	/**
	 * Melee attack.
	 *
	 * @param other the other
	 */
	public void meleeAttack(Creature other){
		commonAttack(other, attackValue(), "attack the %s for %d damage", other.name);
	}

	/**
	 * Throw attack.
	 *
	 * @param item the item
	 * @param other the other
	 */
	private void throwAttack(Item item, Creature other) {
		commonAttack(other, attackValue / 2 + item.thrownAttackValue(), "throw a %s at the %s for %d damage", nameOf(item), other.name);
		other.addEffect(item.quaffEffect());
	}
	
	/**
	 * Ranged weapon attack.
	 *
	 * @param other the other
	 */
	public void rangedWeaponAttack(Creature other){
		commonAttack(other, attackValue / 2 + weapon.rangedAttackValue(), "fire a %s at the %s for %d damage", nameOf(weapon), other.name);
	}
	
	/**
	 * Common attack.
	 *
	 * @param other the other
	 * @param attack the attack
	 * @param action the action
	 * @param params the params
	 */
	private void commonAttack(Creature other, int attack, String action, Object ... params) {
		modifyFood(-2);
		
		int amount = Math.max(0, attack - other.defenseValue());
		
		amount = (int)(Math.random() * amount) + 1;
		
		Object[] params2 = new Object[params.length+1];
		System.arraycopy(params, 0, params2, 0, params.length);
		params2[params2.length - 1] = amount;
		
		doAction(action, params2);
		
		other.modifyHp(-amount, "Killed by a " + name);
		
		if (other.hp < 1)
			gainXp(other);
	}
	
	/**
	 * Gain xp.
	 *
	 * @param other the other
	 */
	public void gainXp(Creature other){
		int amount = other.maxHp 
			+ other.attackValue() 
			+ other.defenseValue()
			- level;
		
		if (amount > 0)
			modifyXp(amount);
	}

	/**
	 * Modify hp.
	 *
	 * @param amount the amount
	 * @param causeOfDeath the cause of death
	 */
	public void modifyHp(int amount, String causeOfDeath) { 
		hp += amount;
		this.causeOfDeath = causeOfDeath;
		
		if (hp > maxHp) {
			hp = maxHp;
		} else if (hp < 1) {
			doAction("die");
			leaveCorpse();
			world.remove(this);
		}
	}
	
	/**
	 * Leave corpse.
	 */
	private void leaveCorpse(){
		Item corpse = new Item('%', color, name + " corpse", null);
		corpse.modifyFoodValue(maxHp * 5);
		world.addAtEmptySpace(corpse, x, y, z);
		for (Item item : inventory.getItems()){
			if (item != null)
				drop(item);
		}
	}
	
	/**
	 * Dig.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 */
	public void dig(int wx, int wy, int wz) {
		modifyFood(-10);
		world.dig(wx, wy, wz);
		doAction("dig");
	}
	
	/**
	 * Update.
	 */
	public void update(){
		modifyFood(-1);
		regenerateHealth();
		regenerateMana();
		updateEffects();
		ai.onUpdate();
	}
	
	/**
	 * Update effects.
	 */
	private void updateEffects(){
		List<Effect> done = new ArrayList<Effect>();
		
		for (Effect effect : effects){
			effect.update(this);
			if (effect.isDone()) {
				effect.end(this);
				done.add(effect);
			}
		}
		
		effects.removeAll(done);
	}
	
	/**
	 * Regenerate health.
	 */
	private void regenerateHealth(){
		regenHpCooldown -= regenHpPer1000;
		if (regenHpCooldown < 0){
			if (hp < maxHp){
				modifyHp(1, "Died from regenerating health?");
				modifyFood(-1);
			}
			regenHpCooldown += 1000;
		}
	}

	/**
	 * Regenerate mana.
	 */
	private void regenerateMana(){
		regenManaCooldown -= regenManaPer1000;
		if (regenManaCooldown < 0){
			if (mana < maxMana) {
				modifyMana(1);
				modifyFood(-1);
			}
			regenManaCooldown += 1000;
		}
	}
	
	/**
	 * Can enter.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 * @return true, if successful
	 */
	public boolean canEnter(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz).isGround() && world.creature(wx, wy, wz) == null;
	}

	/**
	 * Notify.
	 *
	 * @param message the message
	 * @param params the params
	 */
	public void notify(String message, Object ... params){
		ai.onNotify(String.format(message, params));
	}
	
	/**
	 * Do action.
	 *
	 * @param message the message
	 * @param params the params
	 */
	public void doAction(String message, Object ... params){
		for (Creature other : getCreaturesWhoSeeMe()){
			if (other == this){
				other.notify("You " + message + ".", params);
			} else {
				other.notify(String.format("The %s %s.", name, makeSecondPerson(message)), params);
			}
		}
	}
	
	/**
	 * Do action.
	 *
	 * @param item the item
	 * @param message the message
	 * @param params the params
	 */
	public void doAction(Item item, String message, Object ... params){
		if (hp < 1)
			return;
		
		for (Creature other : getCreaturesWhoSeeMe()){
			if (other == this){
				other.notify("You " + message + ".", params);
			} else {
				other.notify(String.format("The %s %s.", name, makeSecondPerson(message)), params);
			}
			other.learnName(item);
		}
	}
	
	/**
	 * Gets the creatures who see me.
	 *
	 * @return the creatures who see me
	 */
	private List<Creature> getCreaturesWhoSeeMe(){
		List<Creature> others = new ArrayList<Creature>();
		int r = 9;
		for (int ox = -r; ox < r+1; ox++){
			for (int oy = -r; oy < r+1; oy++){
				if (ox*ox + oy*oy > r*r)
					continue;
				
				Creature other = world.creature(x+ox, y+oy, z);
				
				if (other == null)
					continue;
				
				others.add(other);
			}
		}
		return others;
	}
	
	/**
	 * Make second person.
	 *
	 * @param text the text
	 * @return the string
	 */
	private String makeSecondPerson(String text){
		String[] words = text.split(" ");
		words[0] = words[0] + "s";
		
		StringBuilder builder = new StringBuilder();
		for (String word : words){
			builder.append(' ');
			builder.append(word);
		}
		
		return builder.toString().trim();
	}
	
	/**
	 * Can see.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 * @return true, if successful
	 */
	public boolean canSee(int wx, int wy, int wz){
		return (detectCreatures > 0 && world.creature(wx, wy, wz) != null
				|| ai.canSee(wx, wy, wz));
	}

	/**
	 * Real tile.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 * @return the tile
	 */
	public Tile realTile(int wx, int wy, int wz) {
		return world.tile(wx, wy, wz);
	}
	
	/**
	 * Tile.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 * @return the tile
	 */
	public Tile tile(int wx, int wy, int wz) {
		if (canSee(wx, wy, wz))
			return world.tile(wx, wy, wz);
		else
			return ai.rememberedTile(wx, wy, wz);
	}

	/**
	 * Creature.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 * @return the creature
	 */
	public Creature creature(int wx, int wy, int wz) {
		if (canSee(wx, wy, wz))
			return world.creature(wx, wy, wz);
		else
			return null;
	}
	
	/**
	 * Pickup.
	 */
	public void pickup(){
		Item item = world.item(x, y, z);
		
		if (inventory.isFull() || item == null){
			doAction("grab at the ground");
		} else {
			doAction("pickup a %s", nameOf(item));
			world.remove(x, y, z);
			inventory.add(item);
		}
	}
	
	/**
	 * Drop.
	 *
	 * @param item the item
	 */
	public void drop(Item item){
		if (world.addAtEmptySpace(item, x, y, z)){
			doAction("drop a " + nameOf(item));
			inventory.remove(item);
			unequip(item);
		} else {
			notify("There's nowhere to drop the %s.", nameOf(item));
		}
	}
	
	/**
	 * Modify food.
	 *
	 * @param amount the amount
	 */
	public void modifyFood(int amount) { 
		food += amount;
		
		if (food > maxFood) {
			maxFood = (maxFood + food) / 2;
			food = maxFood;
			notify("You can't belive your stomach can hold that much!");
			modifyHp(-1, "Killed by overeating.");
		} else if (food < 1 && isPlayer()) {
			modifyHp(-1000, "Starved to death.");
		}
	}
	
	/**
	 * Checks if is player.
	 *
	 * @return true, if is player
	 */
	public boolean isPlayer(){
		return glyph == '@';
	}
	
	/**
	 * Eat.
	 *
	 * @param item the item
	 */
	public void eat(Item item){
		doAction("eat a " + nameOf(item));
		consume(item);
	}
	
	/**
	 * Quaff.
	 *
	 * @param item the item
	 */
	public void quaff(Item item){
		doAction("quaff a " + nameOf(item));
		consume(item);
	}
	
	/**
	 * Consume.
	 *
	 * @param item the item
	 */
	private void consume(Item item){
		if (item.foodValue() < 0)
			notify("Gross!");
		
		addEffect(item.quaffEffect());
		
		modifyFood(item.foodValue());
		getRidOf(item);
	}
	
	/**
	 * Adds the effect.
	 *
	 * @param effect the effect
	 */
	private void addEffect(Effect effect){
		if (effect == null)
			return;
		
		effect.start(this);
		effects.add(effect);
	}
	
	/**
	 * Gets the rid of.
	 *
	 * @param item the item
	 * @return the rid of
	 */
	private void getRidOf(Item item){
		inventory.remove(item);
		unequip(item);
	}
	
	/**
	 * Put at.
	 *
	 * @param item the item
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 */
	private void putAt(Item item, int wx, int wy, int wz){
		inventory.remove(item);
		unequip(item);
		world.addAtEmptySpace(item, wx, wy, wz);
	}
	
	/**
	 * Unequip.
	 *
	 * @param item the item
	 */
	public void unequip(Item item){
		if (item == null)
			return;
		
		if (item == armor){
			if (hp > 0)
				doAction("remove a " + nameOf(item));
			armor = null;
		} else if (item == weapon) {
			if (hp > 0) 
				doAction("put away a " + nameOf(item));
			weapon = null;
		}
	}
	
	/**
	 * Equip.
	 *
	 * @param item the item
	 */
	public void equip(Item item){
		if (!inventory.contains(item)) {
			if (inventory.isFull()) {
				notify("Can't equip %s since you're holding too much stuff.", nameOf(item));
				return;
			} else {
				world.remove(item);
				inventory.add(item);
			}
		}
		
		if (item.attackValue() == 0 && item.rangedAttackValue() == 0 && item.defenseValue() == 0)
			return;
		
		if (item.attackValue() + item.rangedAttackValue() >= item.defenseValue()){
			unequip(weapon);
			doAction("wield a " + nameOf(item));
			weapon = item;
		} else {
			unequip(armor);
			doAction("put on a " + nameOf(item));
			armor = item;
		}
	}
	
	/**
	 * Item.
	 *
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 * @return the item
	 */
	public Item item(int wx, int wy, int wz) {
		if (canSee(wx, wy, wz))
			return world.item(wx, wy, wz);
		else
			return null;
	}
	
	/**
	 * Details.
	 *
	 * @return the string
	 */
	public String details() {
		return String.format("  level:%d  attack:%d  defense:%d  hp:%d", level, attackValue(), defenseValue(), hp);
	}
	
	/**
	 * Throw item.
	 *
	 * @param item the item
	 * @param wx the wx
	 * @param wy the wy
	 * @param wz the wz
	 */
	public void throwItem(Item item, int wx, int wy, int wz) {
		Point end = new Point(x, y, 0);
		
		for (Point p : new Line(x, y, wx, wy)){
			if (!realTile(p.x, p.y, z).isGround())
				break;
			end = p;
		}
		
		wx = end.x;
		wy = end.y;
		
		Creature c = creature(wx, wy, wz);
		
		if (c != null)
			throwAttack(item, c);				
		else
			doAction("throw a %s", nameOf(item));
		
		if (item.quaffEffect() != null && c != null)
			getRidOf(item);
		else
			putAt(item, wx, wy, wz);
	}
	
	/**
	 * Summon.
	 *
	 * @param other the other
	 */
	public void summon(Creature other) {
		world.add(other);
	}
	
	/** The detect creatures. */
	private int detectCreatures;
	
	/**
	 * Modify detect creatures.
	 *
	 * @param amount the amount
	 */
	public void modifyDetectCreatures(int amount) { detectCreatures += amount; }
	
	/**
	 * Cast spell.
	 *
	 * @param spell the spell
	 * @param x2 the x 2
	 * @param y2 the y 2
	 */
	public void castSpell(Spell spell, int x2, int y2) {
		Creature other = creature(x2, y2, z);
		
		if (spell.manaCost() > mana){
			doAction("point and mumble but nothing happens");
			return;
		} else if (other == null) {
			doAction("point and mumble at nothing");
			return;
		}
		
		other.addEffect(spell.effect());
		modifyMana(-spell.manaCost());
	}
	
	/**
	 * Name of.
	 *
	 * @param item the item
	 * @return the string
	 */
	public String nameOf(Item item){
		return ai.getName(item);
	}
	
	/**
	 * Learn name.
	 *
	 * @param item the item
	 */
	public void learnName(Item item){
		notify("The " + item.appearance() + " is a " + item.name() + "!");
		ai.setName(item, item.name());
	}
}
