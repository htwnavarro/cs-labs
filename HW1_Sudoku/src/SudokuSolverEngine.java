public class SudokuSolverEngine {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard("static/boards/fast-solve.sdk");
        System.out.println("Initial board");
        System.out.println(board);
        System.out.println();

        if (!board.isValid()) {
            System.out.println("Board is invalid and unsolvable");
            return;
        }

        if (board.isSolved()) {
            System.out.println("Board is already solved");
            return;
        }


        System.out.print("Solving board...");
        long start = System.currentTimeMillis();
        board.solve();
        long stop = System.currentTimeMillis();
        System.out.printf("SOLVED in %.3f seconds.\n", ((stop-start)/1000.0));
        System.out.println();
        System.out.println(board);
    }
}

/*
Initial board
  -   -   -   -   -   -   -   -   -
| 8 | 2 | 7 | 1 | 5 | 4 | 3 | 9 | 6 |
  -   -   -   -   -   -   -   -   -
| 9 | 6 | 5 |   | 2 | 7 | 1 | 4 | 8 |
  -   -   -   -   -   -   -   -   -
| 3 | 4 | 1 | 6 |   | 9 | 7 | 5 | 2 |
  -   -   -   -   -   -   -   -   -
|   |   |   |   |   |   |   |   |   |
  -   -   -   -   -   -   -   -   -
|   |   |   |   |   |   |   |   |   |
  -   -   -   -   -   -   -   -   -
| 6 | 1 | 8 | 9 | 7 |   | 4 | 3 | 5 |
  -   -   -   -   -   -   -   -   -
| 7 | 8 | 6 | 2 | 3 | 5 |   | 1 | 4 |
  -   -   -   -   -   -   -   -   -
| 1 | 5 | 4 | 7 | 9 | 6 | 8 |   | 3 |
  -   -   -   -   -   -   -   -   -
| 2 | 3 | 9 | 8 | 4 |   |   |   |   |
  -   -   -   -   -   -   -   -   -


Solving board...SOLVED in 0.020 seconds.

  -   -   -   -   -   -   -   -   -
| 8 | 2 | 7 | 1 | 5 | 4 | 3 | 9 | 6 |
  -   -   -   -   -   -   -   -   -
| 9 | 6 | 5 | 3 | 2 | 7 | 1 | 4 | 8 |
  -   -   -   -   -   -   -   -   -
| 3 | 4 | 1 | 6 | 8 | 9 | 7 | 5 | 2 |
  -   -   -   -   -   -   -   -   -
| 5 | 9 | 3 | 4 | 6 | 8 | 2 | 7 | 1 |
  -   -   -   -   -   -   -   -   -
| 4 | 7 | 2 | 5 | 1 | 3 | 6 | 8 | 9 |
  -   -   -   -   -   -   -   -   -
| 6 | 1 | 8 | 9 | 7 | 2 | 4 | 3 | 5 |
  -   -   -   -   -   -   -   -   -
| 7 | 8 | 6 | 2 | 3 | 5 | 9 | 1 | 4 |
  -   -   -   -   -   -   -   -   -
| 1 | 5 | 4 | 7 | 9 | 6 | 8 | 2 | 3 |
  -   -   -   -   -   -   -   -   -
| 2 | 3 | 9 | 8 | 4 | 1 | 5 | 6 | 7 |
  -   -   -   -   -   -   -   -   -
 */