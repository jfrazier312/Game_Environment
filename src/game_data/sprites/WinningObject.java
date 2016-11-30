package game_data.sprites;

import game_data.Location;
import game_data.Sprite;
/**
 * @author austingartside
 *
 */
public class WinningObject extends Sprite{

	public WinningObject(Location aLocation, int aWidth, int aHeight, String aName, String aImagePath) {
		super(aLocation, aWidth, aHeight, aName, aImagePath);
		// TODO Auto-generated constructor stub
	}
	
	public WinningObject(WinningObject aWinningObject){
		super(aWinningObject);
	}

	@Override
	public Sprite clone() {
		return new WinningObject(this);
	}

}
