package snakesladders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/** Controller for the ending screen.
 * @author Adam Mak
 *
 */
public class EndController implements ControlledScreen {
	
	private @FXML Text winnerText;
	
	private ScreensController controller;
	private Pane root;
	
	/** Runs initializables.
	 */
	public void start() {
		StartupController.setButtonTextCSS(root);
		winnerText.getStyleClass().add("buttontext");
		winnerText.setText(SnLController.winner);
	}
	
	/** Handles button clicks.
	 * @param event A button click.
	 */
	public void buttonClickHandler(ActionEvent event) {
		Button clickedButton = (Button)event.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if (buttonLabel.equals("Player Select"))
			controller.setScreen(3);
		
		if (buttonLabel.equals("Quit"))
			System.exit(0);
	}
	
	public void setPane(Pane root) {
		this.root = root;
	}
	
	public void setScreenParent(ScreensController controller) {
        this.controller = controller;
    }
}
