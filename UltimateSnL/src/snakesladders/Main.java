/* Adam Mak
 * Final Game
 * ICS4U
 * Ultimate Snakes and Ladders
 * 01/19/19
 * 
 * This variation of the famous Snakes and Ladders takes a tiny twist, adding in fancy
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
 * 
 * Issues:
 * Font faces from CSS do not load properly in JavaFX 11+.
 * This is due to a bug that prevents local font files in directories with spaces from being loaded.
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
	
	/** Array of controlled screens that can be accessed from any class.
	 */
	public static ControlledScreen controller[] = new ControlledScreen[5];
	
	@Override
	/** Initializes the program.
	 * @param primaryStage Parent of all parents.
	 */
	public void start(Stage primaryStage) {

		ScreensController mainContainer = new ScreensController();
		
		controller[0] = mainContainer.loadScreen(1, "/fxml/Startup.fxml");
		controller[1] = mainContainer.loadScreen(2, "/fxml/Instructions.fxml");
		controller[2] = mainContainer.loadScreen(3, "/fxml/Select.fxml");
		controller[3] = mainContainer.loadScreen(4, "/fxml/Game.fxml");
		controller[4] = mainContainer.loadScreen(5, "/fxml/Final.fxml");

		mainContainer.setScreen(1);
		
		Pane root = new Pane();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ultimate Snakes and Ladders");
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