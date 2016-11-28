package author.view.pages.level_editor.windows;

import author.controller.IAuthorController;
import author.view.util.ToolBarBuilder;
import game_data.Level;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

/**
 * This window serves as the container for all levels, where a user will be able
 * to select a level name and the LevelWindow will change to reflect that new
 * level
 * 
 * @author Jordan Frazier
 * @see ../LevelEditor
 */
public class LevelSelectionWindow extends AbstractLevelEditorWindow {

	private ScrollPane levelScroller;
	private VBox container;

	public LevelSelectionWindow(IAuthorController authorController) {
		super(authorController);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Node> void addChildren(T... child) {
		for (T node : child) {
			container.getChildren().add(node);
		}
	}
	
	@Override
	protected void createToolBar() {
		ToolBarBuilder tbb = new ToolBarBuilder();
		tbb.addBurst(new Label("Level Selection"));
		
		container = new VBox();
		levelScroller = new ScrollPane();
		levelScroller.setFitToHeight(true);
		levelScroller.setFitToWidth(true);

		levelScroller.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		levelScroller.setHbarPolicy(ScrollBarPolicy.NEVER);
		levelScroller.prefHeightProperty().bind(super.getWindow().heightProperty());
		levelScroller.prefWidthProperty().bind(super.getWindow().widthProperty());
		
//		container.prefHeightProperty().bind(levelScroller.prefViewportHeightProperty());
//		container.prefWidthProperty().bind(levelScroller.prefViewportWidthProperty());
		
		levelScroller.setContent(container);

		super.getWindow().getChildren().add(tbb.getToolBar());
		super.getWindow().getChildren().add(levelScroller);
	}

	/* (non-Javadoc)
	 * @see author.view.pages.level_editor.windows.AbstractLevelEditorWindow#initListener(author.controller.IAuthorController, game_data.Level)
	 */
	@Override
	protected void initListener(IAuthorController authorController) {
		authorController.getModel().getGame().addListener((game) -> {
			this.container.getChildren().clear();
			authorController.getModel().getGame().getLevels().forEach((level) ->{
				Label label = new Label(level.getName());
				label.setOnMouseClicked(e -> updateCurrentLevel(level, authorController));
				this.container.getChildren().add(label);
			});
		});
	}
	
	private void updateCurrentLevel(Level aNewCurrentLevel, IAuthorController authorController){
		authorController.getModel().getGame().setCurrentLevel(aNewCurrentLevel);
	}
}
