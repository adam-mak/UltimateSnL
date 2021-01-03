package snakesladders;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/** Represents the snakes and ladders board.
 * @author Adam Mak
 */
public class Board {
	
	/** Width and height of the square board.
	 */
	public final static int BOARD_LENGTH = 10;
	/** Total number of cells in the board.
	 */
	public final static int NUM_CELLS = 100;
	private final int NUM_SNAKES = 7;
	private final int NUM_LADDERS = 5;
	private final int NUM_STARS = 5;
	private final int NUM_DISADVS = 5;

	private GridPane grid = new GridPane();
	
	int snakes[][];
	int ladders[][];
	int stars[];
	int disadvs[];
	
	/** Constructor.
	 * @param grid GridPane representing the board.
	 */
	public Board(GridPane grid) {
		this.grid = grid;
		
		setSnakes();
		setLadders();
		setStars();
		setDisadvs();
	}
	
	/** Initializes cells on the board.
	 * @return cells Array of cells representing each constraint on the board.
	 */
	public Cell[] initializeBoard() {
		Cell cells[] = new Cell[NUM_CELLS];
		int cellIndex = 0;
		boolean forward = true;
		
		for (int row = BOARD_LENGTH - 1; row >= 0; row--) {
			
			if (forward) {
				for (int col = 0; col < BOARD_LENGTH; col++) {
					cells[cellIndex] = new Cell(grid, row, col, cellIndex + 1);
					
					if ((cellIndex + 1) == NUM_CELLS)
						break;
					
					cellIndex++;
				}
			}
			else {
				for (int col = 9; col >= 0; col--) {
					cells[cellIndex] = new Cell(grid, row, col, cellIndex + 1);
					
					if ((cellIndex + 1) == NUM_CELLS)
						break;
					
					cellIndex++;
				}
			}
			forward ^= true;
		}
		return cells;
	}
	
	private void setSnakes() {
		snakes = new int[NUM_SNAKES][2];
		
		snakes[0][0] = 29;
		snakes[0][1] = 5;
		snakes[1][0] = 44;
		snakes[1][1] = 26;
		snakes[2][0] = 48;
		snakes[2][1] = 10;
		snakes[3][0] = 56;
		snakes[3][1] = 38;
		snakes[4][0] = 88;
		snakes[4][1] = 47;
		snakes[5][0] = 96;
		snakes[5][1] = 66;
		snakes[6][0] = 63;
		snakes[6][1] = 20;
	}
	
	private void setLadders() {
		ladders = new int[NUM_LADDERS][2];
		
		ladders[0][0] = 1;
		ladders[0][1] = 25;
		ladders[1][0] = 15;
		ladders[1][1] = 73;
		ladders[2][0] = 51;
		ladders[2][1] = 91;
		ladders[3][0] = 57;
		ladders[3][1] = 75;
		ladders[4][0] = 79;
		ladders[4][1] = 100;
	}
	
	private void setStars() {
		stars = new int[NUM_STARS];
		
		stars[0] = 22;
		stars[1] = 33;
		stars[2] = 78;
		stars[3] = 81;
		stars[4] = 67;
	}
	
	private void setDisadvs() {
		disadvs = new int[NUM_DISADVS];
		
		disadvs[0] = 7;
		disadvs[1] = 16;
		disadvs[2] = 50;
		disadvs[3] = 60;
		disadvs[4] = 93;
	}
}