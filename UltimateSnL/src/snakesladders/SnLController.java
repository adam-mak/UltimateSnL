package snakesladders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class SnLController implements ControlledScreen {
	
	private ScreensController controller;
	private Pane root;
	
	private Board board;
	private Cell cells[];
	private Die die;
	private GameText gameText;
	private ArrayList<Player> players;
	
	private @FXML GridPane grid;
	private @FXML ImageView dieImgView;
	private @FXML Button rollButton;
	private @FXML Text
	player1Score,
	player2Score,
	player3Score,
	player4Score,
	textGuide;
	
	/** The name of the winning player.
	 */
	public static String winner;
	private int dieValue = 1;
	private int turn = 1;
	private boolean animationFinished = true;
	
	/** Runs initializables.
	 */
	public void start() {
		load();
	}

	/** Handles button clicks.
	 * @param event A button click.
	 */
	public void buttonClickHandler(ActionEvent event) throws InterruptedException {
		Button clickedButton = (Button)event.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if (buttonLabel.equals("Roll")) {
			if (animationFinished)
				rollAnim();
		}
	}
	
	private void turn() {
		Player player = players.get(turn - 1);
		gameText.normalMove(player.name, dieValue);
		player.move(dieValue);
		
		Timeline waitTimeline = new Timeline(
				new KeyFrame(Duration.millis(2000), e -> {
					
					for (int[] i : board.snakes) {
						if (i[0] == player.pos) {
							player.transport(i[1]);
							gameText.snakeMove(player.name);
						}
					}
					
					for (int[] i : board.ladders) {
						if (i[0] == player.pos) {
							player.transport(i[1]);
							gameText.ladderMove(player.name);
						}
					}
					
					for (int i : board.stars) {
						if (i == player.pos) {
							turn--;
							if (turn < 1)
								turn = players.size();
							gameText.starMove(player.name);
						}
					}
					
					for (int i : board.disadvs) {
						if (i == player.pos) {
							player.move(-dieValue);
							gameText.disadvMove(player.name);
						}
					}
					
					if (player.pos >= 100) {
						winner = player.name;
						gameText.winMove(player.name);
						
						Timeline waitEnd = new Timeline(
								new KeyFrame(Duration.millis(1900), e1 -> {
									for (Player finishedPlayer : players) {
										finishedPlayer.imgView.setImage(null);
										finishedPlayer.setScore("");
									}
									players.clear();
									/* new KeyValue(StartupController.themeSong.volumeProperty(), 1.0); */
									controller.setScreen(5);
								}));
						waitEnd.play();
					}
				}),
				
				new KeyFrame(Duration.millis(4000), e -> {
					animationFinished = true;
					turn++;
					if (turn > players.size())
						turn = 1;
					if (players.get(turn - 1).ai == true)
						rollAnim();
					gameText.currentTurn(players.get(turn - 1).name);
				}));
		waitTimeline.play();
	}
	
	private void rollAnim() {
		animationFinished = false;
		gameText.rolling(players.get(turn - 1).name);
		dieValue = die.roll();
		final int MIN_ANIM_TIME = 15;
		final int MAX_ANIM_TIME = 40;
		
		AudioClip diceRattle = new AudioClip(
				this.getClass().getResource("/music/dice_rattle.mp3").toExternalForm());
		AudioClip diceLand = new AudioClip(
				this.getClass().getResource("/music/dice_land.mp3").toExternalForm());
		AudioClip diceLandSpecial = new AudioClip(
				this.getClass().getResource("/music/dice_land_special.mp3").toExternalForm());
		
		int animTime = (int)(Math.random()*(MAX_ANIM_TIME-MIN_ANIM_TIME) + MIN_ANIM_TIME);
		
		diceRattle.setCycleCount(Animation.INDEFINITE);
		diceRattle.play();
		Timeline timeline = new Timeline(
		new KeyFrame(Duration.millis(100), e -> {
			int fakeValue = die.roll();
			die.display(fakeValue);
		}));
		timeline.setCycleCount(animTime);
		timeline.setOnFinished(e -> {
			die.display(dieValue);
			diceRattle.stop();
			if (dieValue == 1)
				diceLandSpecial.play();
			else
				diceLand.play();
			turn();
		});
		timeline.play();
	}
	
	private void load() {
		board = new Board(grid);
		die = new Die(root, dieImgView);
		gameText = new GameText(root, textGuide);
		cells = board.initializeBoard();
		players = new ArrayList<Player>(loadPlayers());
		
		rollButton.setFont(Font.font("Rockwell", 14));
	}
	
	private ArrayList<Player> loadPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		HashMap<String, Text> scoreIDs = new HashMap<String, Text>() {{
			put("player1Name", player1Score);
			put("player2Name", player2Score);
			put("player3Name", player3Score);
			put("player4Name", player4Score);
		}};
		
		for (TextField playerID : SelectController.finalPlayerIDs) {
			Player player = new Player(
					root, grid, playerID.getText(), scoreIDs.get(playerID.getId()), cells);
			players.add(player);
		}
		return players;
	}
	
	public void setPane(Pane root) {
		this.root = root;
	}
	
	public void setScreenParent(ScreensController controller) {
        this.controller = controller;
    }
}