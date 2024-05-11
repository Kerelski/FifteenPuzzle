package org.example;

public class GameBoard {

    private int[][] board;

    private Solver solver;

    String parameter;

    public GameBoard(Solver solver, String parameter) {

        this.solver = solver;
        this.parameter = parameter;

    }

    public String solvePuzzle() {
        return solver.solve(board, parameter);
    }

    public void printBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setBoard(String data) {

        String[] numbers = data.split(" ");

        int rows = Integer.parseInt(numbers[0]);
        int columns = Integer.parseInt(numbers[1]);
        board = new int[rows][columns];


        int index = 2;
        for(int i = 0; i<rows; i++){
            for (int j = 0; j<columns; j++){
                board[i][j] = Integer.parseInt(numbers[index]);
                index++;
            }
        }


    }

    public Solver getSolver() {
        return solver;
    }

    public int[][] getBoard() {
        return board;
    }
}
