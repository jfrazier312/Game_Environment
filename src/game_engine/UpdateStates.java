package game_engine;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import game_data.Level;
import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.sprites.WinningObject;
import game_engine.actions.Action;
import game_engine.actions.MoveLeft;
import game_engine.actions.MoveRight;
import game_engine.actions.MoveUp;
import javafx.geometry.Side;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import states.Health;
import states.LevelWon;
import states.Physics;
import states.State;

/**
 * TODO make sure that player doesnt run into walls or thigns 
 * NOTE: doing the runKeyCalls and then updating sprite posistions separately might lead to an issue with glitches
 * CHECK: CALCULATING HEARDING FROM THE VERTICAL (NOON) CHECK CORRECT CALC FOR VEL
 * QUESTION who is going to keep track of the time of the game? how are we going to provoke a win? through interface?
 * TODO give myLevel all the properties I want.
 * CLARIFY: does the x and y loc represent the middle or the left top corner or what?
 * There are some things that I dont know if I should be getting from level class of the engine itself
 * Losses should actually probably be integrated within characteristics so we dont check for collision repeatedly.
 * Do we have to deal with if the sprite hits a block at multiple sides?
 * 
 * @author LuciaMartos
 *
 */

public class UpdateStates {

	private Level myLevel;
	private List<Sprite> mySpriteList;
	private double timeElapsed;
	private KeyCode myKey;
	private Map<KeyCode, Action> myKeyMap;
	private Set<KeyCode> myKeys;
	private Map<Sprite, ImageView> mySpriteImages;

	public UpdateStates(Level aLevel, double timeElapsed, Set<KeyCode> myKeys, Map<Sprite, ImageView> mySpriteImages) {
		this.myLevel = aLevel;
		this.mySpriteList = myLevel.getMySpriteList();
		this.timeElapsed = timeElapsed;
		this.myKeys = myKeys;
		this.mySpriteImages=mySpriteImages;
		generateDefaultKeyMap();
		executeCharacteristics();
		runKeyCalls();
		updateSpritePositions();
		checkForWin();
		checkForLoss();
	}

	private void checkForLoss() {
		for(State s: myLevel.getMainPlayer().getStates()){
			if(s instanceof Health){
				myLevel.setLevelLost(!((Health)s).isAlive());
			}
		}
	}

	private void checkForWin() {
		for(State s: myLevel.getMainPlayer().getStates()){
			if(s instanceof LevelWon){
				myLevel.setLevelWon(((LevelWon)s).isHasWon());
			}
		}
		
	}

	//keys will only control the main player rn
	private void generateDefaultKeyMap() {
		myKeyMap.put(KeyCode.RIGHT, new MoveRight(myLevel.getMainPlayer(), Double.parseDouble(GameResources.MOVE_RIGHT_SPEED.getResource())));
		myKeyMap.put(KeyCode.LEFT, new MoveLeft(myLevel.getMainPlayer(), Double.parseDouble(GameResources.MOVE_LEFT_SPEED.getResource())));
		myKeyMap.put(KeyCode.UP, new MoveUp(myLevel.getMainPlayer(), Double.parseDouble(GameResources.JUMP_SPEED.getResource())));		
	}


	private void runKeyCalls() {
		for(KeyCode myKey: myKeys){
			if(myKeyMap.containsKey(myKey)){
				myKeyMap.get(myKey).act();
			}
		}	
	}

	private void executeCharacteristics() {
		for(Sprite mySprite:mySpriteList){
			
			ListOfCollidingSprites collidingSprites = new ListOfCollidingSprites(mySprite, mySpriteList, mySpriteImages);
			Map<Sprite, Side> myCollisionMap = collidingSprites.getCollisionSpriteMap();
			
			Set<Characteristic> characteristics = mySprite.getCharacteristics();
			for(Characteristic myCharacteristic:characteristics){	
				myCharacteristic.execute(myCollisionMap);
			}
		}
	}

	// not the best design in the world but works for the time being
	
	/**
	 * Checking for win should just happen naturally while checking collisions (i.e. once something collides
	 * with winning object)
	 */
	/*private void checkForWin() {
		Set<String> type = enginePlayerController.getMyLevel().getWinType();
		if(type.contains("time")&& enginePlayerController.getMyLevel().getTime() > enginePlayerController.getMyLevel().getTimeToWin()){
			System.out.print("YOU WIN");
		}
		
		if(type.contains("score") && enginePlayerController.getMyLevel().getMainPlayerSprite().getPoints() > enginePlaterController.getMyLevel().getPointsToWin()){
			System.out.println("YOU WIN");
		}
		
		if(type.contains("object") && enginePlayerController.getMyLevel().getWinningSprite().getBoundsInLocal().interects(enginePlayerController.getMyLevel().getMainPlayerSprite())){
			System.out.println("YOU WIN");
		}
	}*/
	
	/*private void checkForWin(Sprite aSprite){
		if(aSprite instanceof WinningObject){
			System.out.println("Do win action???");
			//Do somethin?
		}
	}*/
	
	/**
	 * Checking for loss also should happen naturally (or just have to check if the players health characteristic has health<0)
	 */
	/*private void checkForLoss() {
		Set<String> type = enginePlayerController.getMyLevel().getLossType();
		Sprite mainPlayer = enginePlayerController.getMainPlayer();
		if(type.contains("object")){
			List<Sprite> deathProvokingObj = enginePlayerController.getMyObjectSpriteList();
			for(Sprite myObj : deathProvokingObj){
				if(enginePlayerController.getImageView(myObj).getBoundsInLocal().intersects(enginePlayerController.getImageView(mainPlayer).getBoundsInLocal())){
					System.out.println("DEATH");
				}
			}
		}
	}*/

	private void updateSpritePositions() {
		for(Sprite sprite:mySpriteList){
			updateSpritePosition(sprite);
		}	
	}

	private void updateSpritePosition(Sprite sprite){
		SpritePhysics spritePhysics = null;
		for(State s: sprite.getStates()){
			if(s instanceof Physics){
				spritePhysics = ((Physics) s).getPhysics();
			}
		}
		
		Location myCurrentLocation = sprite.getMyLocation();
		double curXLoc = myCurrentLocation.getXLocation();
		double curYLoc = myCurrentLocation.getYLocation();
		
		//get initial x velocity component and acceleration 
		double xVelocity = sprite.getMyVelocity()*Math.cos(myCurrentLocation.getMyHeading());
		double newXVelocity = xVelocity + spritePhysics.getVerticalGravity()*timeElapsed;
		
		//get initial y velocity component and acceleration
		double yVelocity = sprite.getMyVelocity()*Math.sin(myCurrentLocation.getMyHeading()*timeElapsed);
		double newYVelocity = yVelocity + spritePhysics.getHorizontalGravity();	
		
		sprite.setMyVelocity(Math.sqrt(Math.pow(newXVelocity, 2) + Math.pow(newYVelocity, 2)));
		
		// calculate the new x and y locations
		double myXLocation = curXLoc + newXVelocity*timeElapsed;
		double myYLocation = curYLoc + newYVelocity*timeElapsed;
		
		// update the location of the sprite
//		Location myNewLocation = new Location(myXLocation, myYLocation, Math.asin(newXVelocity/newYVelocity));
		Location myNewLocation = new Location(myXLocation, myYLocation, myCurrentLocation.getMyHeading());
		sprite.setMyLocation(myNewLocation);
	}
	
}
