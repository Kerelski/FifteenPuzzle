package org.example;

public class Main {
    public static void main(String[] args) throws Exception {

        String solverType = args[0];

        String parameter = args[1];

        String loadBoardFile = args[2];

        Solver solver = null;

        if (solverType.equals("bfs")) {
            solver = new Dfs();
        }
        if (solverType.equals("dfs")) {
            solver = new Dfs();
        }
        GameBoard board = new GameBoard(solver, parameter);

        FileReader file = new FileReader(loadBoardFile);
        file.read();

        board.setBoard(file.getData());

        board.printBoard();

        long startTime = System.nanoTime();
        board.solvePuzzle();
        long finishTime = System.nanoTime();

        double duration = (finishTime - startTime) / 1000000.0;

        System.out.println(duration + "ms");
    }
}