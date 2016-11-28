package author.view.pages.level_editor.windows;

import java.io.File;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.ConcreteMovableSprite;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.view.util.FileLoader;
import author.view.util.FileLoader.FileType;
import author.view.util.ToolBarBuilder;
import author.view.util.authoring_buttons.ButtonFactory;
import game_data.Level;
import game_data.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 * This window is the actual level editor, where sprites will be placed from the
 * EntityWindow
 * 
 * @author Jordan Frazier
 * @see EntityWindow
 * @see ../LevelEditor
 */
public class LevelWindow extends AbstractLevelEditorWindow {

	private ScrollPane myLevelScroller;
	private Pane myContainer;
	private IAuthorController myController;

	private IntegerProperty horizontalPanes = new SimpleIntegerProperty();
	private IntegerProperty verticalPanes = new SimpleIntegerProperty();

	public LevelWindow(IAuthorController authorController) {
		super(authorController);
		myController = authorController;
		horizontalPanes.set(2);
		verticalPanes.set(2);
		createLevelScroller();
	}

	@Override
	public <T extends Node> void addChildren(T... child) {
		for (T node : child) {
			myContainer.getChildren().add(node);
		}
	}

	@Override
	protected void createToolBar() {
		ToolBarBuilder tbb = new ToolBarBuilder();
		tbb.addBurst(new Label("Level Window"));
		tbb.addFiller();
		tbb.addBurst(new ButtonFactory().createButton("Set Background", e -> {
			newBackgroundImage();
		}).getButton(), new ButtonFactory().createButton("Set Theme", e -> {
			// TODO: Jordan(vooga) - Add functionality to changing theme
			System.out.println("Change theme here");
		}).getButton(), new ButtonFactory().createButton("Extend Right", e -> {
			myContainer.setPrefWidth(myLevelScroller.getPrefViewportWidth() * horizontalPanes.get());
			horizontalPanes.set(horizontalPanes.get() + 1);
		}).getButton(), new ButtonFactory().createButton("Extend Down", e -> {
			myContainer.setPrefHeight(myLevelScroller.getPrefViewportHeight() * verticalPanes.get());
			verticalPanes.set(verticalPanes.get() + 1);
		}).getButton());

		super.getWindow().getChildren().add(tbb.getToolBar());
	}

	private void createLevelScroller() {
		myLevelScroller = new ScrollPane();
		myContainer = new Pane();
		myContainer.setOnDragEntered(e -> {
			System.out.println("Drag entered level editor pane");
		});

		myLevelScroller.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		myLevelScroller.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		// Lol these are staying hard coded, the user gon have to pay extra for
		// features like changing window size
		myLevelScroller.setPrefViewportHeight(400);
		myLevelScroller.setPrefViewportWidth(500);

		myContainer.setPrefHeight(myLevelScroller.getPrefViewportHeight());
		myContainer.setPrefWidth(myLevelScroller.getPrefViewportWidth());

		myLevelScroller.setContent(myContainer);
		acceptDraggableSprites();

		super.getWindow().getChildren().add(myLevelScroller);
	}

	private void acceptDraggableSprites() {

		myContainer.setOnDragDropped((DragEvent event) -> {
			if (checkGameHasLevel()) {

				System.out.println("DRAG DROPPED IN PANE");
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					String nodeId = db.getString();
					Sprite sprite = findSprite(nodeId);

					DraggableSprite newSprite;
					try {
						newSprite = new ConcreteMovableSprite(sprite);
					} catch (NullPointerException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
						throw new NullPointerException();
					}

					ImageView image = newSprite.getImageView();
					if (image != null) {
						image.setLayoutX(event.getX());
						image.setLayoutY(event.getY());
						success = true;
					}

					Sprite clone = sprite.clone();
					clone.getMyLocation().setLocation(event.getX(), event.getY());
					this.myController.getModel().getGame().getCurrentLevel().addNewSprite(clone);
				}

				event.setDropCompleted(success);
				event.consume();
			}
		});

		myContainer.setOnDragOver((DragEvent event) -> {
			if (event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});
	}

	private boolean checkGameHasLevel() {
		if (this.myController.getModel().getGame().getCurrentLevel() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Level");
			alert.setHeaderText("No Level created yet.");
			alert.setContentText("Select New -> New Level to create a new Level before dragging sprites");
			alert.showAndWait();
			return false;
		}
		return true;
	}

	private void newBackgroundImage() {
		File file = new FileLoader(FileType.GIF, FileType.JPEG, FileType.PNG, FileType.JPG).loadImage();

		if (file != null)
			this.myController.getModel().getGame().getCurrentLevel()
					.setBackgroundImageFilePath(file.toURI().toString());
	}

	private void setBackgroundImage(String filePath) {
		Level currentLevel = this.myController.getModel().getGame().getCurrentLevel();
		Image image = new Image(filePath, currentLevel.getWidth(), currentLevel.getHeight(), false, false);
		BackgroundImage backIm = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(currentLevel.getWidth(), currentLevel.getHeight(), false, false, false, true));

		myContainer.setBackground(new Background(backIm));
		myContainer.setMinSize(image.getWidth(), image.getHeight());
	}

	private Sprite findSprite(String nodeId) {
		for (Sprite s : myController.getModel().getGame().getPresets()) {
			if (nodeId.equals(s.getId())) {
				return s;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see author.view.pages.level_editor.windows.AbstractLevelEditorWindow#
	 * initListener(author.controller.IAuthorController, game_data.Level)
	 */
	@Override
	protected void initListener(IAuthorController authorController) {
		authorController.getModel().getGame().addListener((game) -> {
			Level currentLevel = authorController.getModel().getGame().getCurrentLevel();
			if (currentLevel != null)
				updateLevel(currentLevel);
		});
	}

	private void updateLevel(Level aLevel) {
		updatePane(aLevel);
		aLevel.addListener((level) -> {
			updatePane(aLevel);
			System.out.println("Updated");
		});
	}

	private void updatePane(Level aLevel) {
		myContainer.getChildren().clear();
		if (aLevel.getBackgroundImageFilePath() != null)
			setBackgroundImage(aLevel.getBackgroundImageFilePath());
		aLevel.getMySpriteList().forEach((sprite) -> {
			DraggableSprite draggableSprite = new ConcreteMovableSprite(sprite);
			draggableSprite.getImageView().setLayoutX(sprite.getMyLocation().getXLocation());
			draggableSprite.getImageView().setLayoutY(sprite.getMyLocation().getYLocation());
			myContainer.getChildren().add(draggableSprite.getImageView());
		});
	}

}
