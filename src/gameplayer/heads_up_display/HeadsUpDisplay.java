package gameplayer.heads_up_display;

import gameplayer.GUIGenerator.GUIGenerator;
import gameplayer.GUIGenerator.IGUIGenerator;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HeadsUpDisplay {
	
	private HBox myTopMenu; 
	private IGUIGenerator myGUIGenerator;
	private BorderPane myRoot;
	
	public HeadsUpDisplay(Scene aScene, double aWidth, double aHeight) {
		//super(aWidth, aHeight);
		myGUIGenerator = new GUIGenerator();
		myRoot = new BorderPane();
	}
	
	public void addButton(String aText, EventHandler<? super MouseEvent> aHandler) {
		myTopMenu.getChildren().add(myGUIGenerator.createButton(aText, 0, 0, aHandler));
	}

	private Node createTop() {
		myTopMenu = new HBox(8);
		myTopMenu.setAlignment(Pos.CENTER);
		return myTopMenu;
	}
	
	private Node createLeft(){
		VBox leftMenu = new VBox();
		leftMenu.getChildren().add(myGUIGenerator.createLabel("Left", 0, 0));
		leftMenu.setAlignment(Pos.CENTER);
		return leftMenu;
	}
	
	private Node createRight(){
		VBox rightMenu = new VBox();
		rightMenu.getChildren().add(myGUIGenerator.createLabel("Right", 0, 0));
		rightMenu.setAlignment(Pos.CENTER);
		return rightMenu;
	}
	
	private Node createBottom(){
		HBox bottomMenu = new HBox();
		bottomMenu.getChildren().add(myGUIGenerator.createLabel("Bottom", 0, 0));
		bottomMenu.setAlignment(Pos.CENTER);
		return bottomMenu;
	}
	
	/*
	private Button createRestartButton(){
		myRestartButton = createButton("Restart", 0, 0, null);
		return myRestartButton;
	}
	
	private Button createMainMenuButton(){
		myMainMenuButton = createButton("Main Menu", 0, 0, null);
		return myMainMenuButton;
	}

	private Button createChangeToRed() {
		myChangeToRedButton = createButton("Change To Red", 0, 0, null);
		return myChangeToRedButton;
	}
	*/
	
	public Pane init() {
		myRoot.setRight(createRight());
		myRoot.setLeft(createLeft());
		myRoot.setBottom(createBottom());
		myRoot.setTop(createTop());
		myRoot.setBackground(Background.EMPTY);
		//myRoot.getScene().setFill(Color.TRANSPARENT);
		//myRoot.getScene().getRoot().setStyle("-fx-background-color: transparent !important;");
		return myRoot;
	}

}
