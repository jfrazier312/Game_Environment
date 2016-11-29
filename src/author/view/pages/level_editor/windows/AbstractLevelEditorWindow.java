package author.view.pages.level_editor.windows;


import author.controller.IAuthorController;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * The abstract class that windows in the level editor will extend
 * 
 * @author Jordan Frazier, Cleveland Thompson
 *
 */
public abstract class AbstractLevelEditorWindow {

	private Pane myWindow;
	private IAuthorController authorController;
	
	public AbstractLevelEditorWindow(IAuthorController authorController){
		this.authorController = authorController;
		createWindow();
		createToolBar();
		initListener();
	}
	
	@SuppressWarnings("unchecked")
	public abstract <T extends Node> void addChildren(T... child);
	
	protected abstract void initListener();
	
	protected abstract void createToolBar();
	
	public Pane getWindow() {
		return myWindow;
	}
	
	protected Pane createWindow() {
		myWindow = new VBox();
		return myWindow;
	}
	
	protected IAuthorController getController() {
		return authorController;
	}
	
	protected ImageView getImageView(String path, ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height){
		ImageView imageView = new ImageView(path);
//		imageView.setPreserveRatio(true);
		imageView.setFitWidth(width.doubleValue());
		imageView.setFitHeight(height.doubleValue());
//		imageView.fitHeightProperty().bind(height);
//		imageView.fitWidthProperty().bind(width);
		return imageView;
	}
}
