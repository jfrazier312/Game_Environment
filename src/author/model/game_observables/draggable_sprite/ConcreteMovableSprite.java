package author.model.game_observables.draggable_sprite;

import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.scene.image.Image;

public class ConcreteMovableSprite extends DraggableSprite {

	private double mouseX;
	private double mouseY;

	public ConcreteMovableSprite(Sprite aSprite) {
		super(aSprite);
	}
	
	

	/**
	 * http://stackoverflow.com/questions/27080039/proper-way-to-move-a-javafx8-node-around
	 */
	@Override
	protected void makeDraggable() {
		super.getImageView().setOnMousePressed(event -> {
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
		});

		super.getImageView().setOnMouseDragged(event -> {
			double deltaX = event.getSceneX() - mouseX;
			double deltaY = event.getSceneY() - mouseY;
			super.getImageView().relocate(super.getImageView().getLayoutX() + deltaX,
					super.getImageView().getLayoutY() + deltaY);
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
			super.getSprite().getMyLocation().setLocation(super.getImageView().getLayoutX(), super.getImageView().getLayoutY());
		});
	}
	
	@Override
	protected InvalidationListener initListener(Sprite aSprite){
		InvalidationListener invalidationListener = (sprite) -> {
			this.getImageView().setImage(new Image(aSprite.getMyImagePath()));
			this.getImageView().setFitWidth(aSprite.getMyWidth());
			this.getImageView().setFitHeight(aSprite.getMyHeight());
		};
		return invalidationListener;
	}

}
