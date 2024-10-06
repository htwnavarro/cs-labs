import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// SudokuBoard provides an implementation for consuming a 9x9 text file for initializing a sudoku board
// provides a `toString` display of the board in current state where board cells without a value (".")
// are replaced with an empty space.
// SudokuBoard is also outfitted with an inValid and isSolved method to determine if the puzzle is valid and solved!

public class SudokuBoard {
    int ROW = 9;
    int COL = 9;
    String[][] board = new String[ROW][COL];

    private String EMPTY_CELL_VALUE = ".";
    private int NO_EMPTY_CELL_VALUE = -1;

    // Pre: a text file with a given 9x9 data format
    // Post: a 2D array field is populated and be displayed
    SudokuBoard(String pathToFile) {
        try {

            Scanner input = new Scanner(new File(pathToFile));

            for (int row = 0; row < ROW; row++) {
                board[row] = input
                    .nextLine() // gets the file data for the row
                    .split(""); // splits the row at every char, assumes the data file is well-formatted
            }
        } catch (FileNotFoundException error) {
            System.out.println("File not found");
        }
    }

    // Pre: the class is constructed with board data file
    // Post: get a string display of the board
    public String toString() {
        StringBuilder display = new StringBuilder();

        display.append(this.getDisplayRowSeparator()); // top line on board

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col <= COL - 1; col++) {
                display.append(
                    this.getDisplayCellValue(
                        board[row][col].equals(EMPTY_CELL_VALUE)
                            ? " " // use a space for empty cell
                            : board[row][col], // use the cell value otherwise
                        col == 0
                    )
                );
            }
            display.append("\n");
            display.append(this.getDisplayRowSeparator());
        }

        return display.toString();
    }

    private String getDisplayCellValue(String cellValue, boolean isFirstCellInRow) {
        return (isFirstCellInRow ? "| " : " ") + cellValue + " " + "|";
    }

    private String getDisplayRowSeparator() {
        return "  -   -   -   -   -   -   -   -   - \n";
    }

    // Pre: a board data file has been added to the instance (see constructor)
    // Post: returns if the board in its current state is valid. i.e.:
    //  - valid board data file,
    //  - valid rows,
    //  - valid columns,
    //  - valid 3x3 "grids" within the board

    public boolean isValid() {
        boolean isValid = true;

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col <= COL - 1; col++) {
                if (isEmptyCell(row, col)) { // ignore empty cells
                    continue;
                }

                if (!isValidCell(row, col)) { // check for validity of the cell data
                    isValid = false;
                    break;
                }

                if (isCellRepeatedInRow(row, col)) { // check for a repeated cell value in a row
                    isValid = false;
                    break;
                }

                if (isCellRepeatedInColumn(row, col)) { // check for a repeated cell value in a column
                    isValid = false;
                    break;
                }
            }

            if (!isValid) { // any error is an erred board, early exit.
                break;
            }
        }

        if (hasValidGrids()) { // check for a repeated cell value in direct square
            isValid = false;
        }

        return isValid;
    }

    // Pre: a board data file has been added to the instance (see constructor)
    // Post: returns if the board in its current state is valid and correctly solved. i.e, 1-9 appears 9 times
    public boolean isSolved() {
        if (!isValid()) {
            return false;
        }

        HashMap<String, Integer> boardCellValueCount = new HashMap<>();
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                String cellValue = board[row][col];
                if (boardCellValueCount.containsKey(cellValue)) {
                    boardCellValueCount.put(cellValue, boardCellValueCount.get(cellValue) + 1);
                } else {
                    boardCellValueCount.put(cellValue, 1);
                }
            }
        }

        boolean isSolved = boardCellValueCount
            .values()
            .stream()
            .filter((v) -> v == 9) // should have found 1-9 9 times
            .count() == 9; // should have 9 entries 1-9

        return isSolved;
    }

    // Pre: a board data file has been added to the instance (see constructor) and the board position is confirmed not empty cell
    // Post: returns if a cell in the board given a board position is repeated within the row its in
    private boolean isCellRepeatedInRow(int row, int col) {
        List<String> currentRow = new ArrayList<>(Arrays.asList(board[row]));
        currentRow.remove(col); // need to remove the current cell from the copy
        if (currentRow.contains(board[row][col])) { // checks the rest of the row for a repeated value
            return true;
        }
        return false;
    }

    // Pre: a board data file has been added to the instance (see constructor) and the board position is confirmed not empty cell
    // Post: returns if a cell in the board given a board position is repeated within the column
    private boolean isCellRepeatedInColumn(int row, int col) {
        HashMap<Integer, String> currentCol = new HashMap();
        for (int row2 = row + 1; row2 < ROW; row2++) {
            currentCol.put(row2 - 1, board[row2][col]); // adds the next row to an array
        }
        if (currentCol.containsValue(board[row][col])) { // checks the array for a repeated value of the current cell value
            return true;
        }
        return false;
    }

    // Pre: a board data file has been added to the instance (see constructor)
    // Post: returns true/false for if a cell value given a board position can be parsed to an integer
    private boolean isValidCell(int row, int col) {
        try {
            Integer.parseInt(board[row][col]);
        } catch (NumberFormatException error) {
            // current cell is not blank and does not cast to integer
            return false;
        }
        return true;
    }

    // Pre: a board data file has been added to the instance (see constructor)
    // Post: returns true/false for if a cell in the board at a given board position is empty
    private boolean isEmptyCell(int row, int col) {
        return board[row][col].equals(EMPTY_CELL_VALUE);
    }

    // Pre: a board data file has been added to the instance (see constructor)
    // Post: returns if a cell in the board given cell value is empty
    private boolean isEmptyCell(String cell) {
        return cell.equals(EMPTY_CELL_VALUE);
    }

    // Pre: a board data file has been added to the instance (see constructor)
    // Post: returns true/false if the 3x3 grids do have repeated values
    private boolean hasValidGrids() {
        boolean isRepeated = false;

        for (int gridNumber = 1; gridNumber <= 9; gridNumber++) {
            String[][] grid = getGridByNumber(gridNumber);
            List<String> gridValues = Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .filter(v -> !isEmptyCell(v))
                .toList();
            for (String cell : gridValues) {
                if (gridValues.stream().filter(v -> v.equals(cell)).count() > 1) {
                    isRepeated = true;
                    break;
                }
            }
            if (isRepeated) {
                break;
            }
        }
        return isRepeated;
    }

    // Pre: a board data file has been added to the instance (see constructor)
    // Post: returns a 3x3 grid given a "grid number" 1-9
    private String[][] getGridByNumber(int gridNumber) {
        String[][] mini = new String[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                // whoa - wild! This took me a solid hour to figure out (at least)
                // This translates between the "gridNumber" in the 9x9 Sudoku board
                // and a new mini square of 3x3
                mini[r][c] = board[(gridNumber - 1) / 3 * 3 + r][(gridNumber - 1) % 3 * 3 + c];
            }
        }
        return mini;
    }

    // Pre: a board data file has been added to the instance (see constructor) -- ideally valid in unsolved
    // Post: a boolean indicating if the board is solve-able
    //  Uses a backtracking recursion pattern to attempt to solve a sudoku board
    public boolean solve() {
        if (!isValid()) {
            return false;
        }

        if (isSolved()) {
            return true;
        }

        Pair cellCoordinates = this.getEmptyBoardCell();

        int row = cellCoordinates.getX();
        int col = cellCoordinates.getY();

        for (int cellValue = 1; cellValue <= 9; cellValue++) {
            board[row][col] = String.valueOf(cellValue);

            if (solve()) {
                return true;
            } else {
                board[row][col] = EMPTY_CELL_VALUE;
            }
        }

        return false;
    }

    // Pre: a board data file has been added to the instance (see constructor)
    // Post: a Pair (row,column) that represents the next empty board cell location
    private Pair getEmptyBoardCell() {
        Pair boardCell = new Pair(NO_EMPTY_CELL_VALUE, NO_EMPTY_CELL_VALUE);
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(EMPTY_CELL_VALUE)) {
                    boardCell = new Pair(row, col);
                    break;
                }
            }
        }

        return boardCell;
    }
}

/*
Void output
 */