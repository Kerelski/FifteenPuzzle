package org.example;

import java.util.*;

public class Dfs extends Solver {
    private String correctBoard;
    private final int MAX_DEPTH = 30;
    private int visitedStates = 0;
    private int processedStates = 0;
    private int further = 0;

    public String[] solve(int[][] board, String parameter) {
        char[] directions = parameter.toCharArray();
        correctBoard = generateCorrectBoard(board);
        Node initialNode = new Node(board, 0, new StringBuilder());

        Stack<Node> stack = new Stack<>();
        HashSet<String> visited = new HashSet<>();
        stack.push(initialNode);
        Node result = null;

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            int[][] currentBoard = currentNode.getBoard();
            processedStates++;

            if (boardToString(currentBoard).equals(correctBoard)) {
                result = currentNode;
                break;
            }

            if (currentNode.getDepth() >= MAX_DEPTH) {
                continue;
            }
            int counter = 0;
            for (char direction : directions) {
                if (movePossibility(direction, currentBoard)) {
                    int[][] newBoard = copyBoard(currentBoard);
                    swapZero(direction, newBoard);

                    if (!visited.contains(boardToString(newBoard))) {
                        StringBuilder newHistory = new StringBuilder(currentNode.getHistory());
                        newHistory.append(direction);
                        Node newNode = new Node(newBoard, currentNode.getDepth() + 1, newHistory);
                        stack.push(newNode);
                        further = Math.max(newNode.getDepth(), further);
                        visited.add(boardToString(newBoard));
                        visitedStates++;

                        counter++;
                    }
                }
            }
            Node tab[] = new Node[counter];
            for(int i = 0; i < counter; i++) {
                tab[i] = stack.pop();
            }
            for(int i = 0; i < counter; i++) {
                stack.push(tab[i]);
            }

        }

        String[] datas = new String[4];
        if (result == null){
            datas[0] = "-1";
        }
        else {
            datas[0] = result.getHistory().toString();

        }
        datas[1] = String.valueOf(visitedStates);
        datas[2] = String.valueOf(processedStates);
        datas[3] = String.valueOf(further);
        return datas;
    }


    private int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[0].length);
        }
        return newBoard;
    }
}