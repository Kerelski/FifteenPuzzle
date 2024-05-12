package org.example;

public class Node {

    int[][] board;
    int depth;
    StringBuilder history;

    public Node(int[][] board, int depth, StringBuilder history) {
        this.board = board;
        this.depth = depth;
        this.history = history;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public StringBuilder getHistory() {
        return history;
    }

    public void setHistory(StringBuilder history) {
        this.history = history;
    }

}
