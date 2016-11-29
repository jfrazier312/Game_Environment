package game_engine;

import game_data.Game;
import util.XMLTranslator;

public class GameEngine {
	private Game myGame;
	private EnginePlayerController myEnginePlayerController;

	// public GameEngine(IEngineReceiverControllerInterface myInterface) {
	public GameEngine(String filePath) {
		makeGameFromXML(filePath);
		myEnginePlayerController = new EnginePlayerController(myGame);
	}

	public GameEngine(String filePath, int levelNumber) {
		makeGameFromXML(filePath);
		myGame.setCurrentLevel(levelNumber);
		myEnginePlayerController = new EnginePlayerController(myGame);
	}

	private void makeGameFromXML(String filePath) {
		String myXML = filePath;
		XMLTranslator myTranslator = new XMLTranslator();
		myGame = (Game) myTranslator.deserialize(myXML);
	}

	public EnginePlayerController getMyEnginePlayerController() {
		return myEnginePlayerController;
	}

	public void setEnginePlayerController(EnginePlayerController enginePlayerController) {
		myEnginePlayerController = enginePlayerController;
	}
}
