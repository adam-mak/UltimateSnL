package snakesladders;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/** Representing a 6 sided die.
 * @author Adam Mak
 */
public class Die {
	
	private final int DIE_FACES = 6;
	
	private ImageView dieImgView;
	private Pane root;
	private ImageView dieFaces[] = new ImageView[DIE_FACES];
	private Timeline timeline;	
	private int value;
	
	/** Constructor.
	 * @param root Parent Pane.
	 * @param dieImgView ImageView of the die (unspecified image).
	 */
	public Die(Pane root, ImageView dieImgView) {
		this.root = root;
		this.dieImgView = dieImgView;
		
		value = roll();
		display(value);
	}
	
	/** Generates a random number between 1 and 6.
	 * @return A random number.
	 */
	public int roll() {
		int randNum = (int)(Math.random()*6 + 1);
		return randNum;
	}
	
	/** Displays the die onto the screen.
	 * @param num Latest dice roll.
	 */
	public void display(int num) {
		String imageName = "images/die" + num + ".png";
		Image image = new Image(imageName);
		dieImgView.setImage(image);
	}
}