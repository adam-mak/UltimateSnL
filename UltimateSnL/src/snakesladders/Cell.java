package snakesladders;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Cell {
	
	private final int CELL_LENGTH = 50;
	
	/** Parent Pane.
	 */
	public Pane root;
	private GridPane grid;
	
	private int x;
	private int y;
	private int row;
	private int col;
	private int cellNum;
	
	/** Constructor.
	 * @param grid GridPane representing the board.
	 * @param row Board row.
	 * @param col Board column.
	 * @param cellNum Number when the board is finalized.
	 */
	public Cell(GridPane grid, int row, int col, int cellNum) {
		this.grid = grid;
		this.cellNum = cellNum;
		this.row = row;
		this.col = col;
		
		root = getCellPane(row, col);
		x = (int)root.getLayoutX();
		y = (int)root.getLayoutY();
		
		displayCellNum();
	}
	
	private Pane getCellPane(int row, int col) {
		for (Node node : grid.getChildren()) {
			if (GridPane.getColumnIndex(node) == null) 
				GridPane.setColumnIndex(node, 0);
			if (GridPane.getRowIndex(node) == null)
				GridPane.setRowIndex(node, 0);

			if (GridPane.getColumnIndex(node) == col
					&& GridPane.getRowIndex(node) == row) {
				if (node instanceof Pane)
					return (Pane)node;
			}
		}
		return null;
	}
	
	private void displayCellNum() {
		Text numText = new Text(String.valueOf(cellNum));
		numText.setFont(Font.font("Times New Roman", 12));
		numText.setY(10);
		root.getChildren().add(numText);
		
		switch (cellNum % 7) {
		case 0:
			colourCell(Color.web("#00ff92"));
			break;
		case 1:
			colourCell(Color.web("#d76814"));
			break;
		case 2:
			colourCell(Color.web("#2ab9c2"));
			break;
		case 3:
			colourCell(Color.web("#fffcfc"));
			break;
		case 4:
			colourCell(Color.web("#d4fffa"));
			break;
		case 5:
			colourCell(Color.web("#ffe9c8"));
			break;
		case 6:
			colourCell(Color.web("#aeb9e8"));
			break;
		}
	}
	
	private void colourCell(Color color) {
		Rectangle colour = new Rectangle();
		colour.setFill(color);
		colour.setWidth(50);
		colour.setHeight(50);
		colour.setStroke(Color.BLACK);
		colour.setStrokeType(StrokeType.INSIDE);
		GridPane.setColumnIndex(colour, col);
		GridPane.setRowIndex(colour, row);
		grid.getChildren().add(colour);
		colour.toBack();
	}
}
