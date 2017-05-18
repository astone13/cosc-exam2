package ui;

import game.Creature;
import game.Line;
import game.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class FireWeaponScreen.
 */
public class FireWeaponScreen extends TargetBasedScreen {

	/**
	 * Instantiates a new fire weapon screen.
	 *
	 * @param player the player
	 * @param sx the sx
	 * @param sy the sy
	 */
	public FireWeaponScreen(Creature player, int sx, int sy) {
		super(player, "Fire " + player.nameOf(player.weapon()) + " at?", sx, sy);
	}

	/* (non-Javadoc)
	 * @see ui.TargetBasedScreen#isAcceptable(int, int)
	 */
	public boolean isAcceptable(int x, int y) {
		if (!player.canSee(x, y, player.z))
			return false;
		
		for (Point p : new Line(player.x, player.y, x, y)){
			if (!player.realTile(p.x, p.y, player.z).isGround())
				return false;
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see ui.TargetBasedScreen#selectWorldCoordinate(int, int, int, int)
	 */
	public void selectWorldCoordinate(int x, int y, int screenX, int screenY){
		Creature other = player.creature(x, y, player.z);
		
		if (other == null)
			player.notify("There's no one there to fire at.");
		else
			player.rangedWeaponAttack(other);
	}
}
