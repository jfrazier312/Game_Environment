package game_data;

public class GameDataTestingMain {

	public static void main(String[] args){
		Sprite aSprite = new Player(new Location(0,0), "path/spriteImage.jpg");
		Level aLevel = new Level(500, 8000, "path/backgroundImage.jpg");
		aLevel.addNewSprite(aSprite);
		Game aGame = new Game();
		aGame.addNewStage(aLevel);
		aGame.saveGameAs("XMLGameFiles/","TestXMLFile");
	}
}
