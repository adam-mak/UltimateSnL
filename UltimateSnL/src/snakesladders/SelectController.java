package snakesladders;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/** Controller for the player selection screen.
 * @author Adam Mak
 */
public class SelectController implements ControlledScreen {
	
	private final int CHAR_LIMIT = 9;
	
	private @FXML TextField 
	player1Name,
	player2Name,
	player3Name,
	player4Name;
	private @FXML Text textGuide;
	
	/** An ArrayList of the players that will be instantiated in the game.
	 */
	public static ArrayList<TextField> finalPlayerIDs;
	private ScreensController controller;
	private Pane root;
	private TextField[] playerIDs;
	private boolean textSwitch = false;
	private int count = 0;
	
	/** Runs initializables.
	 */
	public void start() {
		playerIDs = new TextField[]
				{player1Name, player2Name, player3Name, player4Name};
		for (TextField i : playerIDs) {
			addTextLimiter(i, CHAR_LIMIT);
		}
		StartupController.setButtonTextCSS(root);
		textSwitch = true;
		textLoop();
	}
	
	/** Handles button clicks.
	 * @param event A button click.
	 */
	public void buttonClickHandler(ActionEvent event) {
		Button clickedButton = (Button)event.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if (buttonLabel.equals("GO")) {
			finalPlayerIDs = new ArrayList<TextField>(setPlayerNames());
			/*
			Timeline volumeFade = new Timeline(
					new KeyFrame(Duration.millis(1500),
					new KeyValue(StartupController.themeSong.volumeProperty(), 0.1)));
			volumeFade.play();
			*/
			if (finalPlayerIDs.size() > 1) {
				textSwitch = false;
				controller.setScreen(4);
			}
			else
				textGuide.setText("Two or more players only.");
		}
		
		if (buttonLabel.equals("Back")) {
			textSwitch = false;
			controller.setScreen(1);
		}
	}
	
	/** Handles mouse clicks.
	 * @param event A mouse click.
	 */
	public void hoverImageHandler(MouseEvent event) {
		ImageView imgView = (ImageView)event.getSource();
		imgView.setOnMouseEntered(e -> {
			addBloom(imgView);
		});
		imgView.setOnMouseExited(e -> {
			removeBloom(imgView);
		});
		imgView.setOnMousePressed(e -> {
			System.out.println("hi");
			pressedImage(imgView, ((double)(7/8)), true);
		});
		imgView.setOnMouseReleased(e -> {
			pressedImage(imgView, ((double)(7/8)), false);
		});
	}
	
	private ArrayList<TextField> setPlayerNames() {
		ArrayList<TextField> IDs = new ArrayList<TextField>();
		
		for (TextField playerID : playerIDs) {
			if (!playerID.getText().isEmpty())
				IDs.add(playerID);
		}
		return IDs;
	}
	
	private void textLoop() {
		String textArray[] = new String[2];
		textArray[0] = "Type in 'AI' to get an AI opponent.";
		textArray[1] = "Add a name to each player to add it.";
		textGuide.getStyleClass().add("buttontext");
		new AnimationTimer() {
			@Override
			public void handle(long currentTime) {
				if (count % 180 == 0) {
					if (textGuide.getText() == textArray[0])
						textGuide.setText(textArray[1]);
					else
						textGuide.setText(textArray[0]);
				}
				count++;
				if (!textSwitch)
					stop();
			}
		}
		.start();
	}
	
	private void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
	
	private void addBloom(ImageView imgView) {
		Bloom bloom = new Bloom();
		bloom.setThreshold(0.5);
		imgView.setEffect(bloom);
	}
	
	private void removeBloom(ImageView imgView) {
		imgView.setEffect(null);
	}
	
	private void pressedImage(ImageView imgView, double ratio, boolean shrink) {
		if (shrink) {
			imgView.setFitHeight(imgView.getImage().getHeight() * ratio);
			imgView.setPreserveRatio(true);
		}
		else {
			imgView.setFitHeight(imgView.getImage().getHeight());
			imgView.setPreserveRatio(true);
		}
	}
	
	public void setPane(Pane root) {
		this.root = root;
	}
	
	public void setScreenParent(ScreensController controller) {
        this.controller = controller;
    }
}