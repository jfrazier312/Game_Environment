package author.view.pages.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class MenuElement {
	
	private MenuItem item;
	
	public MenuElement(String name, EventHandler<ActionEvent> e) {
		item = new MenuItem();
		item.setText(name);
		item.setOnAction(e);
	}
	
	public MenuItem getItem() {
		return item;
	}

}
