package snakesladders;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.util.Duration;

/** Controller for the startup screen.
 * @author Adam Mak
 */
public class StartupController implements ControlledScreen {
	
	/** An AudioClip looping throughout the entire program runtime.
	 */
	public static AudioClip themeSong;
	private ScreensController controller;
	private Pane root;
	
	private @FXML Text textUltimate;
	
	private boolean initializeSwitch = true;
	
	/** Runs initializables.
	 */
	public void start() {
		setButtonTextCSS(root);
		
		if (initializeSwitch) {
			animation();
			playTheme();
			initializeSwitch = false;
		}
	}
	
	/** Handles button clicks.
	 * @param event A button click.
	 */
	public void buttonClickHandler(ActionEvent event) {
		Button clickedButton = (Button)event.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if (buttonLabel.equals("Play Game")) {
			controller.setScreen(3);
		}
		
		if (buttonLabel.equals("Instructions")) {
			controller.setScreen(2);
		}
		
		if (buttonLabel.equals("Quit")) {
			System.exit(0);
		}
	}
	
	/** Set CSS to buttons on a Pane.
	 * @param root The parent Pane of the button.
	 */
	public static void setButtonTextCSS(Pane root) {
		for (Node node : root.getChildren()) {
			if (node instanceof Button)
				node.getStyleClass().add("buttontext");
		}
	}
	
	private void animation() {
		textUltimate.getStyleClass().add("buttontext");
		textUltimate.setStyle("-fx-stroke: black;");
		ScaleTransition st = new ScaleTransition(Duration.seconds(0.8), textUltimate);
		st.setToX(3);
		st.setToY(3);
		st.setAutoReverse(true);
		st.setCycleCount(ScaleTransition.INDEFINITE);
		st.play();
	}
	
	private void playTheme() {
		final double MUSIC_VOLUME = 0.2;
		
		themeSong = new AudioClip(this.getClass().getResource("/music/theme_song.mp3").toExternalForm());
		themeSong.setVolume(MUSIC_VOLUME);
		themeSong.setCycleCount(Animation.INDEFINITE);
		themeSong.play();
	}
	
	public void setPane(Pane root) {
		this.root = root;
	}
	
	public void setScreenParent(ScreensController controller) {
        this.controller = controller;
    }
}