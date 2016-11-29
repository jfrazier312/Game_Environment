package game_engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_data.Sprite;
import javafx.geometry.Side;
import javafx.scene.image.ImageView;

public class ListOfCollidingSprites {
	
	private Sprite targetSprite;
	private List<Sprite> spriteList;
	private Map<Sprite, Side> collisionSprites;
	private Map<Sprite, ImageView> mySpriteImages;

	public ListOfCollidingSprites(Sprite targetSprite, List<Sprite> spriteList, Map<Sprite, ImageView> spriteImages) {
		this.targetSprite =targetSprite;
		this.spriteList = spriteList;
		this.mySpriteImages=spriteImages;
		//collisionSprites = new HashMap<Sprite, Side>();
		getListOfSpritesCollided();
	}
	
	private Map<Sprite, Side> getListOfSpritesCollided(){
		collisionSprites = new HashMap<Sprite, Side>();
		
		for(Sprite mySprite:spriteList){
			if(mySprite != targetSprite && mySpriteImages.get(mySprite).getBoundsInLocal().intersects(mySpriteImages.get(targetSprite).getBoundsInLocal())){
				collisionSprites.put(mySprite, findSideOfCollission(mySprite, targetSprite));
			}
		}	
		return collisionSprites;
	}

	private  Side findSideOfCollission(Sprite mySprite, Sprite targetSprite) {
		if(mySprite.getMyLocation().getYLocation() <= targetSprite.getMyLocation().getYLocation()){
			return Side.BOTTOM;
		}
		if(mySprite.getMyLocation().getYLocation() >= targetSprite.getMyLocation().getYLocation()){
			return Side.TOP;
		}
		if(mySprite.getMyLocation().getXLocation() < targetSprite.getMyLocation().getXLocation()){
			return Side.LEFT;
		}
		if(mySprite.getMyLocation().getXLocation() > targetSprite.getMyLocation().getXLocation()){
			return Side.RIGHT;
		}
		return null;
		
	}
	
	public Map<Sprite, Side> getCollisionSpriteMap(){
		return this.collisionSprites;
	}

}
