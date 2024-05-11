package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {

        String solverType = args[0];

        String parameter = args[1];

        String loadBoardFile = args[2];

        String resultBoardFile = args[3];

        String fileStats = args[4];

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

        long startTime = System.nanoTime();
        String[] result = board.solvePuzzle();
        long finishTime = System.nanoTime();
        double duration = (finishTime - startTime) / 1000000.0;
        String stringDuration = String.format("%.3f", duration);

        StringBuilder solution = new StringBuilder();
        if(result[0] == "-1"){
            solution.append(result[0]);
        }else{
            solution.append(result[0].length());
            solution.append("\n");
            solution.append(result[0]);
        }

        Writer writerSolution = new Writer(resultBoardFile);
        writerSolution.write(solution.toString());



        StringBuilder stats = new StringBuilder();
        stats.append(result[0].length());
        stats.append("\n");
        stats.append(result[1]);
        stats.append("\n");
        stats.append(result[2]);
        stats.append("\n");
        stats.append(result[3]);
        stats.append("\n");
        stats.append(stringDuration);

        Writer writerStats = new Writer(fileStats);
        writerStats.write(stats.toString());
    }
}