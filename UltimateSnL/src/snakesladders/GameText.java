package snakesladders;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/** Represents the text guide during the game.
 * @author adami
 */
public class GameText {

	private Pane root;
	private Text textGuide;
	
	/** Constructor.
	 * @param root Parent Pane.
	 * @param textGuide Text to display contents.
	 */
	public GameText(Pane root, Text textGuide) {
		this.root = root;
		this.textGuide = textGuide;
		textGuide.setTextAlignment(TextAlignment.CENTER);
		textGuide.setFont(Font.font("Verdana", 16));
	}
	
	/** For when the dice is being rolled.
	 * @param playerName The player that is rolling's name.
	 */
	public void rolling(String playerName) {
		String s = playerName + " is rolling...";
		textGuide.setText(s);
	}
	
	/** Displays who's turn it is when no actions are underway.
	 * @param playerName The player who's turn is current roll.
	 */
	public void currentTurn(String playerName) {
		String s = "It is now " + playerName + "'s turn.";
		textGuide.setText(s);
	}
	
	/** Displays a dice roll.
	 * @param playerName The player that moved.
	 * @param numSpaces The amount of spaces the player moved.
	 */
	public void normalMove(String playerName, int numSpaces) {
		String s;
		if (numSpaces == 1)
			s = String.format("%s has moved one space.", playerName);
		else
			s = String.format("%s has moved %d spaces.", playerName, numSpaces);
		textGuide.setText(s);
	}
	
	/** When a player encounters a ladder.
	 * @param playerName
	 */
	public void ladderMove(String playerName) {
		String s = playerName + " has found a ladder!";
		textGuide.setText(s);
	}
	
	/** When a player encounters a snake.
	 * @param playerName
	 */
	public void snakeMove(String playerName) {
		String s = playerName + " has met a snake!";
		textGuide.setText(s);
	}
	
	/** When a player encounters a star.
	 * @param playerName
	 */
	public void starMove(String playerName) {
		String s = playerName + " gets an extra roll!";
		textGuide.setText(s);
	}
	
	/** When a player encounters a disadvantage.
	 * @param playerName
	 */
	public void disadvMove(String playerName) {
		String s;
		int special = (int)(Math.random() * 5);
		if (special == 0)
			s = playerName + " looks at Dylan's face!";
		else
			s = playerName + " has to go back to its original position!";
		textGuide.setText(s);
	}
	
	/** Displays the winner.
	 * @param playerName
	 */
	public void winMove(String playerName) {
		String s = playerName + " has won.";
		textGuide.setText(s);
	}
}