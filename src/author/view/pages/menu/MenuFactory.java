package author.view.pages.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MenuFactory {

	
	public MenuFactory() {
		
	}
	
	public MenuElement createItem(String name, EventHandler<ActionEvent> event) {
		return new MenuElement(name, event);
	}
}
