package author.view;

import java.io.File;

import author.controller.IAuthorController;
import author.view.pages.level_editor.ILevelEditorExternal;
import author.view.pages.level_editor.LevelEditorFactory;
import author.view.pages.menu.MenuFactory;
import author.view.pages.sprite.SpritesPage;
import author.view.util.FileLoader;
import author.view.util.TabPaneFacade;
import author.view.util.FileLoader.FileType;
import author.view.util.authoring_buttons.ButtonFactory;
import game_data.Level;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * AuthorView handles the JavaFX GUI.
 * 
 * @author George Bernard, Cleveland Thompson, Addison Howenstine
 */
public class AuthorView {

	Scene myScene;
	Pane myPane = new VBox();
	TabPaneFacade myTabPaneFacade;
	IAuthorController authorController;

	private SpritesPage mySpritesPage;
	private ILevelEditorExternal myLevelEditor;

	// TODO move these to properties, as well as button names
	public static final int WIDTH = 800;
	public static final int HEIGHT = 650;
	public static final String SPRITE_IMAGE_PATH = "author/images/mymario.jpg";
	//public static final String BACKGROUND_IMAGE_PATH = "author/images/mario.jpg";

	public AuthorView(IAuthorController authorController) {
		this.authorController = authorController;
		this.mySpritesPage = new SpritesPage(authorController);
		this.myLevelEditor = new LevelEditorFactory().create(this.authorController);

		myScene = new Scene(myPane, WIDTH, HEIGHT, Color.WHITE);
		myPane.getChildren().addAll(buildToolBar(), buildTabPane());
		// TESTING SPRITE POSITIONS, DELETE THIS WHENEVER YOU WANT
		// This is just going through the presets(so you can only display one mario and one of that other thing)
		// and multiplying their positions by the ratio fo the new scenes width and height, so it should
		// scale corrrectly
		myPane.getChildren().add(new ButtonFactory().createButton("Test Sprite Positions", e -> {
			Stage stage = new Stage();
			Pane root = new Pane();
			Scene scene = new Scene(root, WIDTH, HEIGHT);
			authorController.getModel().getGame().getPresets().forEach(event -> {
				ImageView v = new ImageView(event.getMyImagePath());
				v.setFitHeight(40);
				v.setFitWidth(40);
				v.setLayoutX(event.getMyLocation().getXLocation() * (800.0 / 500.0));
				v.setLayoutY(event.getMyLocation().getYLocation() * (650.0 / 400.0));
				root.getChildren().add(v);
			});
			stage.setScene(scene);
			stage.show();
		}).getButton());
	}

	/**
	 * Returns Toolbar built for primary AuthorScene
	 */
	private Node buildToolBar() {

		MenuBar menuBar = new MenuBar();
		Menu menuNew = new Menu("New");
		Menu menuSave = new Menu("Save");
		Menu menuLoad = new Menu("Load");
		menuBar.getMenus().addAll(menuNew, menuSave, menuLoad);

		menuNew.getItems().addAll(new MenuFactory().createItem("New Game", e -> {
			// TODO: Jordan(vooga) - create new game
		}).getItem(), new MenuFactory().createItem("New Level", e -> {
			Level createdLevel = this.myLevelEditor.createLevel();
			if (createdLevel != null){
				this.authorController.getModel().getGame().addNewLevel(createdLevel);
			}
		}).getItem());

		menuSave.getItems().add(new MenuFactory().createItem(("Save Game"), e -> {
			// Save game
			authorController.getModel().saveGame("Saved");// TODO: prompt user for name
		}).getItem());

		menuLoad.getItems().add(new MenuFactory().createItem("Load Game", e -> {
			authorController.getModel().loadGame(loadFileChooser());;
		}).getItem());

		return menuBar;
	}

	/**
	 * Returns TabPane built for primary AuthorScene
	 */
	private Node buildTabPane() {
		myTabPaneFacade = new TabPaneFacade();
		myTabPaneFacade.getTabPane().prefWidthProperty().bind(myScene.widthProperty());
		myTabPaneFacade.getTabPane().prefHeightProperty().bind(myScene.heightProperty());
		myTabPaneFacade.getTabPane().setSide(Side.BOTTOM);

		myTabPaneFacade.addTab(mySpritesPage.toString(), mySpritesPage.getRegion());
		myTabPaneFacade.addTab(myLevelEditor.toString(), myLevelEditor.getPane());

		return myTabPaneFacade.getTabPane();
	}
	
	public Scene getScene() {
		return myScene;
	}
	
	private File loadFileChooser() {
		File file = new FileLoader(FileType.XML).loadImage();
		return file;
	}

}
