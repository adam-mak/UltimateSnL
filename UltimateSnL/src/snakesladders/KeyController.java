package snakesladders;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/** Controller for the instructions screen
 * @author Adam Mak
 */
public class KeyController implements ControlledScreen {
	
	private final int PAGES = 2;
	
	@FXML TextFlow textInstructions;
	@FXML Button next;
	
	private ScreensController controller;
	private Pane root;
	private int page = 1;
	
	/** Runs initializables.
	 */
	public void start() {
		StartupController.setButtonTextCSS(root);
		setPages();
	}
	
	/** Handles button clicks.
	 * @param event A button click.
	 */
	public void buttonClickHandler(ActionEvent event) {
		Button clickedButton = (Button)event.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if (buttonLabel.equals("Back")) {
			if (page == 1)
				controller.setScreen(1);
			else if (page == PAGES)
				next.setText("Next");
			if (page != 1)
				page--;
		}
		
		if (buttonLabel.equals("Next")) {
			if (page == PAGES - 1)
				clickedButton.setText("Done");
			page++;
		}
		
		if (buttonLabel.equals("Done")) {
			controller.setScreen(1);
			clickedButton.setText("Next");
			page = 1;
		}
		setPages();
	}
	
	private void setPages() {
		final String TITLE_CSS = "-fx-font-size: 36; -fx-font-family: Rockwell Extra Bold;";
		final String SUBTITLE_CSS = "-fx-font-size: 24; -fx-font-family: Rockwell;";
		final String NORMAL_CSS = "-fx-font-size: 18; -fx-font-family: Rockwell;";
		switch(page) {
		case 1:
			Text t1 = new Text("Welcome to Ultimate Snakes and Ladders!");
			t1.setStyle(TITLE_CSS);
			Text t2 = new Text("\n\nHow To Play\n\n");
			t2.setStyle(SUBTITLE_CSS + "-fx-underline: true;");
			t2.setFill(Color.web("#333333"));
			Text t3 = new Text(
					  "This game can be played with up to four players or computers.\n\n"
					+ "Each player gets a character to try to reach to the top of the board first.\n\n"
					+ "Players take turns rolling a die.  They will move spots according the result.\n\n");
			t3.setStyle(NORMAL_CSS);
			textInstructions.getChildren().clear();
			textInstructions.getChildren().addAll(t1, t2, t3);
			break;
		case 2:
			Text t4 = new Text("Continue: Objectives\n\n");
			t4.setStyle(TITLE_CSS);
			Text t5 = new Text(
					  "If your player reaches 100, you win!  However, there will be advantages and obstacles along the way.\n\n"
					+ "If you ends the turn where a ladder is, they will climb up (not down).\n"
					+ "Landing on the head of a snake will cause you to fall until you reach its tail.\n\n"
					+ "Stars will give you an EXTRA roll, giving you the possibility to move up to 12 spaces.\n\n"
					+ "Be careful though, there are also disadvantages, which will take you back to your original position.\n"
					);
			t5.setStyle(NORMAL_CSS);
			textInstructions.getChildren().clear();
			textInstructions.getChildren().addAll(t4, t5);
			break;
		default:
			break;
		}
	}
	
	public void setPane(Pane root) {
		this.root = root;
	}
	
	public void setScreenParent(ScreensController controller) {
        this.controller = controller;
    }
}