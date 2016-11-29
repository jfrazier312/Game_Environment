package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_engine.actions.Bounce;
import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */
@CharacteristicAnnotation(name = "Winnable")
public class Winnable implements Characteristic{

	@ParameterAnnotation( parameters = {""} )
	public Winnable(){
		super();
	}
	
	@Override
	public Characteristic copy() {
		return new Winnable();
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO: make and execute win action
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			//myAction = new Bounce();
			//myAction.act();
		}
	}


}
