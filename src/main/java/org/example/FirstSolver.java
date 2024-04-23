package org.example;

import java.util.ArrayList;

public class FirstSolver implements Solver{

    public void solve(ArrayList<ArrayList<Integer>> board) {
        System.out.println(getDistance(0, 0, board));
    }

    private int getDistance(int row, int col, ArrayList<ArrayList<Integer>> board) {

        int number = board.get(row).get(col) -1;
        int cRow = number / 4;
        int cCol = number % 4;

        return Math.abs(cRow - row) + Math.abs(cCol - col);
    }
}
