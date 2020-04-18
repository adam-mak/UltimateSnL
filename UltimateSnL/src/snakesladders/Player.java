package snakesladders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/** Represents a player.
 * @author Adam Mak
 */
public class Player {

	private final int IMAGE_SIZE = 40;
	private final int OFFSET = 5;
	
	/** The player's name.
	 */
	public String name;
	/** Position of the player on the board.
	 */
	public int pos;
	/** Imageview of the player sprite.
	 */
	public ImageView imgView;
	public boolean ai;
	private Cell cells[];
	private Text score;
	private int playerNum;
	
	/** Contructor.
	 * @param root Parent Pane.
	 * @param grid GridPane representing the board.
	 * @param name 
	 * @param score A text displaying the position of the player.
	 * @param cells Array containing all cells on the board.
	 */
	public Player(Pane root, GridPane grid, String name, Text score, Cell[] cells) {
		this.name = name;
		this.score = new Text("0");
		this.cells = cells;
		
		playerNum = Integer.parseInt(
				score.getId().replaceFirst(".*?(\\d+).*", "$1"));
		
		imgView = setImageView();
		
		this.score.setFont(Font.font("Rockwell", 14));
		this.score.setX(score.getLayoutX());
		this.score.setY(score.getLayoutY());
		root.getChildren().add(this.score);
		
		if (name.toLowerCase().equals("ai")) {
			ai = true;
		}
		pos = 0;
		root.getChildren().add(imgView);
	}

	/** Moves player's position by the number of spaces.
	 * @param numSpaces Number of spaces to pass.
	 */
	public void move(int numSpaces) {
		pos += numSpaces;
		display();
	}
	
	/** Moves player's position to a certain position.
	 * @param pos The end position.
	 */
	public void transport(int pos) {
		this.pos = pos;
		display();
	}
	
	private void display() {
		imgView.setX(OFFSET);
		imgView.setY(OFFSET);
		try {
			cells[pos - 1].root.getChildren().add(imgView);
			score.setText(String.valueOf(pos));
		}
		catch(ArrayIndexOutOfBoundsException e) {
			cells[Board.NUM_CELLS - 1].root.getChildren().add(imgView);
			score.setText(String.valueOf(Board.NUM_CELLS));
		}
	}
	
	private ImageView setImageView() {
		String imageName = "images/player" + playerNum + ".png";
		Image image = new Image(imageName);
		imgView = new ImageView(image);
		imgView.setFitHeight(IMAGE_SIZE);
		imgView.setPreserveRatio(true);
		imgView.setX(25);
		imgView.setY(530);
		
		return imgView;
	}
}