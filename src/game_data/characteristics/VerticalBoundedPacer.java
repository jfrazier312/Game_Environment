package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import javafx.geometry.Side;

@CharacteristicAnnotation(name = "Vertical Bounded Pacer")
public class VerticalBoundedPacer extends BoundedPacer implements Characteristic{

	@ParameterAnnotation(parameters = {"Speed", "Lower Bound", "Upper Bound"})
	public VerticalBoundedPacer(int speed, double lowerBound, double upperBound) {
		super(speed, lowerBound, upperBound);
	}

	@Override
	public boolean toChangeDirection(Sprite aSprite) {
		double yLocation = aSprite.getMyLocation().getYLocation();
		return yLocation<=this.getLowerBound() || yLocation>=this.getUpperBound();
	}
	
	@Override
	public Characteristic copy() {
		return new VerticalBoundedPacer(this.getSpeed(), this.getLowerBound(), this.getUpperBound());
	}
	
	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		// TODO Auto-generated method stub
		
	}

}
