package game_engine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_data.Sprite;
import game_data.Game;
import game_data.Level;
import game_data.Location;
import game_data.sprites.Character;
import javafx.scene.image.ImageView;
import states.Health;
import states.State;
public class EnginePlayerController implements IEnginePlayerControllerInterface {
	private Level myLevel;
	private int myWidth, myHeight;
	private String myBackgroundImageFilePath;
	private List<Sprite> mySpriteList;
	private List<Double> mySpriteXCoordinateList;
	private List<Double> mySpriteYCoordinateList;
	private List<Double> mySpriteHeadingList;
	private List<String> mySpriteImagePathList;
	private List<Integer> mySpriteHealthList;
	private List<Boolean> mySpriteIsAliveList;
	private Game myGame;

	public EnginePlayerController(){
		myGame=null;
		myLevel=null;
		mySpriteList=null;
		myBackgroundImageFilePath=null;
		myWidth=0;
		myHeight=0;
		mySpriteXCoordinateList=null;
		mySpriteYCoordinateList=null;
		mySpriteHeadingList=null;
		mySpriteImagePathList=null;
		mySpriteHealthList=null;
		mySpriteIsAliveList=null;
	}
	public EnginePlayerController(Game game){
		myGame=game;
		myLevel=myGame.getCurrentLevel();
		mySpriteList=myLevel.getMySpriteList();
		myBackgroundImageFilePath=myLevel.getBackgroundImageFilePath();
		myWidth=myLevel.getWidth();
		myHeight=myLevel.getHeight();
		updateSpriteData();
	}
	public void updateControllerData(){
		//need to update level in here
		
		mySpriteList=myLevel.getMySpriteList();
		myBackgroundImageFilePath=myLevel.getBackgroundImageFilePath();
		myWidth=myLevel.getWidth();
		myHeight=myLevel.getHeight();
		updateSpriteData();
	}
	private void updateSpriteData(){
		for(int i=0; i<mySpriteList.size(); i++){
			Object sprite = mySpriteList.get(i);
			mySpriteImagePathList.set(i, ((Sprite) sprite).getMyImagePath());
			Location location = ((Sprite) sprite).getMyLocation();
			mySpriteXCoordinateList.set(i, location.getXLocation());
			mySpriteYCoordinateList.set(i, location.getYLocation());
			mySpriteHeadingList.set(i, location.getMyHeading());
			if(sprite instanceof Character){
				for(State myState:((Character) sprite).getStates()){
					if(myState instanceof Health){
						mySpriteHealthList.set(i, ((Health) myState).getMyHealth());
						mySpriteIsAliveList.set(i, ((Health) sprite).isAlive());
					}
				}
			}
			else{
				mySpriteHealthList.set(i, null);
				mySpriteIsAliveList.set(i, null);
			}
		}
	}
	public Level getMyLevel() {
		return myLevel;
	}
	public int getMyWidth() {
		return myWidth;
	}
	public int getMyHeight() {
		return myHeight;
	}
	public String getMyBackgroundImageFilePath() {
		return myBackgroundImageFilePath;
	}
	public List<Sprite> getMySpriteList() {
		return mySpriteList;
	}
	public List<Double> getMySpriteXCoordinateList() {
		return mySpriteXCoordinateList;
	}
	public List<Double> getMySpriteYCoordinateList() {
		return mySpriteYCoordinateList;
	}
	public List<Double> getMySpriteHeadingList() {
		return mySpriteHeadingList;
	}
	public List<String> getMySpriteImagePathList() {
		return mySpriteImagePathList;
	}
	public List<Integer> getMySpriteHealthList() {
		return mySpriteHealthList;
	}
	public List<Boolean> getMySpriteIsAliveList() {
		return mySpriteIsAliveList;
	} 
	public boolean isLost(){
		return myLevel.isLevelLost();
	}
	public Game getMyGame(){
		return myGame;
	}
}
