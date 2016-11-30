package game_data.characteristics;

import java.util.HashMap;
import java.util.Map;

import game_data.Sprite;
import javafx.geometry.Side;

public interface Characteristic {
	
	
	/**
	 * Method implemented by all characteristics (i.e. breakable, movable etc.). What happens for each characteristic every
	 * time step. 
	 * 
	 * @author Austin Gartside
	 */
	//public boolean toAct();
	public void execute(Map<Sprite, Side> myCollisionMap);
	public Characteristic copy();

}