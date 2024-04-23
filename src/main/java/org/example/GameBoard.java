package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {

    private ArrayList<ArrayList<Integer>> board;

    private Solver solver;

    public GameBoard( Solver solver) {
        board = new ArrayList<>();
        board.add(new ArrayList<>(List.of(1, 2, 3, 4)));
        board.add(new ArrayList<>(List.of(5, 6, 7, 8)));
        board.add(new ArrayList<>(List.of(9, 10, 11, 12)));
        board.add(new ArrayList<>(List.of(13, 14, 15, 0)));

        this.solver = solver;

    }

    public void shuffleBoard() {
        ArrayList<Integer> flatList = new ArrayList<>();
        for (ArrayList<Integer> row : board) {
            flatList.addAll(row);
        }
        Collections.shuffle(flatList);

        int index = 0;
        for (ArrayList<Integer> row : board) {
            for (int i = 0; i < row.size(); i++) {
                row.set(i, flatList.get(index++));
            }
        }
    }

    public void solvePuzzle() {
        solver.solve(board);
    }

    public void printBoard() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(board.get(i).get(j) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setBoard(ArrayList<ArrayList<Integer>> board) {
        int[] temp = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                temp[board.get(i).get(j)]++;
            }
        }
        for(int i = 0; i < 16; i++) {
            if(temp[i] != 1) return;
        }

        this.board = board;
    }

    public Solver getSolver() {
        return solver;
    }

    public ArrayList<ArrayList<Integer>> getBoard() {
        return board;
    }
}
