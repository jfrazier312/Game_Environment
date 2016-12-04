package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_engine.actions.Action;
import game_engine.actions.Hit;
import game_engine.actions.HitTop;
import javafx.geometry.Side;

@CharacteristicAnnotation(name = "TransparentBottomImpassable")
public class TransparentBottomImpassable implements Characteristic{
	
	private Sprite mySprite;
	private Action myAction;
	
	@ParameterAnnotation(parameters={"Sprite"})
	public TransparentBottomImpassable(Sprite aSprite){
		mySprite = aSprite;
	}
	
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap){
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			myAction = new HitTop(collidedSprite, myCollisionMap.get(collidedSprite), mySprite);
			myAction.act();
		}
	}

	@Override
	public Characteristic copy() {
		return new TransparentBottomImpassable(mySprite);
	}

}