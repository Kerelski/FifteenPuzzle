package org.example;

import java.util.*;

public class Dfs extends Solver {

    public String[] solve(int[][] board, String parameter) {

        char[] directions = parameter.toCharArray();

        System.out.println(directions[0]);
        System.out.println(directions[1]);
        System.out.println(directions[2]);
        System.out.println(directions[3]);

        String correctBoard = generateCorrectBoard(board);
        System.out.println(correctBoard);

        int rows = board.length;
        int cols = board[0].length;

        int maxDepth = 8;
        int depth = 0;
        StringBuilder history = new StringBuilder();

        Node root = new Node(board, depth, history);

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        Set<String> visited = new HashSet<>();
        Node finalNode = root;

        while (!stack.isEmpty()) {

            Node currentNode = stack.pop();
            int[][] currentBoard = currentNode.board;
            visited.add(boardToString(currentBoard));

            String sCurrentBoard = boardToString(currentBoard);
            if (sCurrentBoard.equals(correctBoard)) {
                System.out.println(sCurrentBoard + " rozwiazany");
                finalNode = currentNode;
                break;
            }

            if (currentNode.depth >= maxDepth) {
                continue;
            }

            for (char dir : directions) {
                if (movePossibility(dir, currentBoard)) {
                    int[][] newBoard = new int[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        System.arraycopy(currentBoard[i], 0, newBoard[i], 0, cols);
                    }
                    swapZero(dir, newBoard);

                    String newBoardString = boardToString(newBoard);
                    if (!visited.contains(newBoardString)) {
                        System.out.println(dir);
                        System.out.println(boardToString(newBoard));
                        StringBuilder newHistory = new StringBuilder(currentNode.history);
                        newHistory.append(dir);
                        Node newNode = new Node(newBoard, currentNode.depth + 1, newHistory);
                        stack.push(newNode);
                        history.append(dir);
                        currentNode = newNode;
                        currentBoard = currentNode.board;
                        if (boardToString(currentBoard).equals(correctBoard)) {
                            break;
                        }
                    }
                }
            }

        }

        String[] datas = new String[5];

        return datas;

    }
}