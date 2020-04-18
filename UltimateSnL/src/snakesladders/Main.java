/* Adam Mak
 * Final Game
 * ICU4U
 * Ultimate Snakes and Ladders
 * 01/19/19
 * 
 * This variation of the famous Snakes and Ladders takes a tiny twist, adding in some fancy
 * powerups to compliment what has already been added to the traditional game.
 * Runs smoothly with little to no errors, epic music and high flexibility of player selection.
 * 
 * Screens:
 * - Intro
 * - Instructions
 * - Player Selection
 * - Game
 * - End or Final
 * 
 * Extras:
 * - Improved screen and controller framework, with fade transitions
 * - Timelines, transitions and keyframes for animation and cooldown effects
 * - Dynamic rendering / creating and editing nodes through code
 * - HashMaps (dictionaries)
 * - AudioClips
 * - ImageViews
 * 
 * References:
 * Background: https://antonioamedina.com/projects/xKz52
 * Player Sprites - https://www.oryxdesignlab.com/products/16-bit-fantasy-tileset
 * Controller Framework - https://github.com/acaicedo/JFX-MultiScreen
 * Theme song - https://www.newgrounds.com/audio/listen/125026
 */

package snakesladders;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Main extends Application {
	
	private static final String INTRO_FXML = "/fxml/Startup.fxml";
	private static final String INSTR_FXML = "/fxml/Instructions.fxml";
	private static final String SELECT_FXML = "/fxml/Select.fxml";
	private static final String GAME_FXML = "/fxml/Game.fxml";
	private static final String FINAL_FXML = "/fxml/Final.fxml";
	
	private static final String TITLE = "Ultimate Snakes and Ladders";
	
	/** Array of controlled screens that can be accessed from any class.
	 */
	public static ControlledScreen controller[] = new ControlledScreen[5];
	
	@Override
	/** Initializes the program.
	 * @param primaryStage Parent of all parents.
	 */
	public void start(Stage primaryStage) {

		ScreensController mainContainer = new ScreensController();
		
		controller[0] = mainContainer.loadScreen(1, INTRO_FXML);
		controller[1] = mainContainer.loadScreen(2, INSTR_FXML);
		controller[2] = mainContainer.loadScreen(3, SELECT_FXML);
		controller[3] = mainContainer.loadScreen(4, GAME_FXML);
		controller[4] = mainContainer.loadScreen(5, FINAL_FXML);

		mainContainer.setScreen(1);
		
		Pane root = new Pane();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        primaryStage.show();
	}
	
	/** Backup if Main.start() fails.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}