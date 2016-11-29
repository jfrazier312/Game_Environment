package game_data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import util.XMLTranslator;

/**
 * A Game has a List of Levels. Each Level may contain Sprites
 * and other elements. A Game can be saved to a serialized XML
 * file to be loaded elsewhere.
 * 
 * @author Addison, Cleveland Thompson
 *
 */
public class Game extends GameObject{

	List<Level> myLevels;
	Set<Sprite> spritePresets = new HashSet<>();
	XMLTranslator myXMLSaver;
	
	private Level currentLevel;
	
	public Game(String aName){
		setName(aName);
		myLevels = new ArrayList<Level>();
		myXMLSaver = new XMLTranslator();
	}

	/**
	 * Saves Game, Levels, and all states to a serialized XML File
	 * 
	 * @param filePath
	 * @param fileName
	 * @author Addison
	 */
	public void saveGameAs(String aFilePath, String aFileName){
	    myXMLSaver.saveToFile(this, aFilePath, aFileName);
	}

	public void addNewLevel(Level aLevel){
		this.currentLevel = aLevel;
		myLevels.add(aLevel);
		this.notifyListeners();
	}
	
	public List<Level> getLevels(){
		return myLevels;
	}
	
	public void setCurrentLevel(Level aLevel){
		this.currentLevel = aLevel;
		this.notifyListeners();
	}
	
	public Level getCurrentLevel(){
		return this.currentLevel;
	}
	
	public void addPreset(Sprite aPresetSprite){
		this.spritePresets.add(aPresetSprite);
		this.notifyListeners();
	}
	
	public Set<Sprite> getPresets(){
		return Collections.unmodifiableSet(this.spritePresets);
	}

}
