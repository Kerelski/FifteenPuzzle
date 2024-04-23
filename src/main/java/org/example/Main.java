package org.example;

public class Main {
    public static void main(String[] args) {
        Solver solver = new FirstSolver();
        GameBoard board = new GameBoard(solver);
        board.printBoard();
        board.shuffleBoard();
        board.printBoard();

        board.solvePuzzle();
    }
}