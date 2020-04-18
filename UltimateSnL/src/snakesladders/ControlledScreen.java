package snakesladders;

import javafx.scene.layout.Pane;

public interface ControlledScreen {
	
	public void setScreenParent(ScreensController screenPage);
	
	public void setPane(Pane root);
	
	public void start();
}
