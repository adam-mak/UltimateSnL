package snakesladders;

import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/** Handles all screen framework and transitions, aswell as controller initialization.
 * Idea from Angie, modifications were made to enable easy controlller intiazlization.
 * @author Angie
 * @author Adam Mak
 */
public class ScreensController extends StackPane {
	
	private HashMap<Integer, Node> screens = new HashMap<>();
	
	/** Loads FXML file and controls its controller.
	 * @param index Screen number.
	 * @param resource FXML file name
	 * @return The FXML file controller's class.
	 */
	public ControlledScreen loadScreen(int index, String resource) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Pane root = (Pane)loader.load();
            ControlledScreen screenController = (ControlledScreen)loader.getController();
            screenController.setScreenParent(this);
            screenController.setPane(root);
            addScreen(index, root);
            return screenController;
        }
        catch(Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	/** Displays the screen's node onto a Parent StackPane.
	 * @param index Screen number.
	 */
	public void setScreen(final int index) { 
		// fadeOut();
		try {
    		getChildren().remove(0);
    	}
    	catch(Exception e) {
		}
        getChildren().add(screens.get(index));
        Main.controller[index - 1].start();
        fadeIn();
	}
	
	private Node getScreen(int index) {
        return screens.get(index);
    }
	
	private void addScreen(Integer index, Node screen) {
        screens.put(index, screen);
    }
	
	/** Unload and remove the screen from the loaded lists.
	 * @param index Screen number to be removed.
	 */
	public void removeScreen(int index) {
		screens.remove(index);
	}
	
	private void fadeIn() {
		final DoubleProperty opacity = opacityProperty();
		Timeline fadeIn = new Timeline(
		        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.5)),
		        new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
        fadeIn.play();
	}
	
	/** Turns screen white, do not use.
	 * @deprecated
	 */
	private void fadeOut() {
		final DoubleProperty opacity = opacityProperty();
		Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                new KeyFrame(new Duration(1000), new KeyValue(opacity, 0.0)));
		fadeOut.play();
	}
}