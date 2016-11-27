/**
 * 
 */
package author.model;

import game_data.Game;
import game_data.Sprite;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface IAuthorModel {
	
	public Sprite addSprite(Sprite aSpritePreset);
	
	public void newGame();
	
	public Game getGame();
}
