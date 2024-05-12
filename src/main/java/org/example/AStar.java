package org.example;

import java.util.*;

public class AStar extends Solver{

    private int hammingDistance(int[][] board, int[][] correctBoard){
        int distance = 0;
        for(int i = 0; i< board.length; i++){
            for (int j = 0 ; j< board[0].length; j++){
                if(board[i][j] != correctBoard[i][j] && board[i][j] != 0) distance++;
            }
        }
        return distance;
    }

    private int manhattanDistance(int[][] board, int[][] correctBoard){
        int distance = 0;
        for(int i = 0; i< board.length; i++){
            for (int j = 0 ; j< board[0].length; j++){
                if(board[i][j] != correctBoard[i][j] && board[i][j] != 0){
                    int value = board[i][j];
                    int[] correctPosition = findPosition(value, correctBoard);
                    distance += Math.abs(i - correctPosition[0]) + Math.abs(j - correctPosition[1]);
                }
            }
        }
        return distance;
    }


    public String[] solve(int[][] board, String parameter) {

        char[] directions = {'R', 'D', 'U', 'L'};

        int rows = board.length;
        int cols = board[0].length;

        int[][] correctBoard = new int[rows][cols];
        int value = 1;
        for(int i = 0; i< rows; i++){
            for (int j = 0; j<cols; j++){
                correctBoard[i][j] = value;
                value++;
            }
        }
        correctBoard[rows-1][cols-1] = 0;

        int depth = 0;
        StringBuilder history = new StringBuilder();

        AstrNode root = null;
       if(parameter.equals("hamm")){
           root = new AstrNode(board, depth, history, hammingDistance(board, correctBoard), 0);
       }
        if(parameter.equals("manh")){
            root = new AstrNode(board, depth, history, manhattanDistance(board, correctBoard), 0);
        }

        PriorityQueue<AstrNode> que = new PriorityQueue<>();
        que.add(root);

        List<AstrNode> processed = new ArrayList<>();
        processed.add(root);

        int visitedCounter = 0;
        int maxDepth = 0;
        AstrNode result = root;

        while (!que.isEmpty()){
            AstrNode currentNode = que.poll();
            visitedCounter++;
            if(maxDepth< currentNode.depth)maxDepth=currentNode.depth;

            if(Arrays.deepEquals(currentNode.board, correctBoard)){
                result = currentNode;
                break;
            }
            for(char dir : directions) {
                if (movePossibility(dir, currentNode.board)) {
                    int distance = currentNode.g + 1;
                    AstrNode newNode = null;
                    int[][] temp = new int[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        System.arraycopy(currentNode.board[i], 0, temp[i], 0, cols);
                    }
                    swapZero(dir, temp);
                    if (parameter.equals("hamm")) {
                        newNode = new AstrNode(temp, currentNode.depth + 1, new StringBuilder(currentNode.history), hammingDistance(temp, correctBoard), distance);
                    }
                    if (parameter.equals("manh")) {
                        newNode = new AstrNode(temp, currentNode.depth + 1, new StringBuilder(currentNode.history), manhattanDistance(temp, correctBoard), distance);
                    }
                    newNode.history.append(dir);
                    que.add(newNode);
                    processed.add(newNode);
                }
            }

        }

        String[] datas = new String[4];
        if (result == root){
            datas[0] = "-1";
        }
        else {
            datas[0] = result.history.toString();
            datas[1] = String.valueOf(visitedCounter);
            datas[2] = String.valueOf(processed.size());
            datas[3] = String.valueOf(maxDepth);
        }
        return datas;
    }


}
