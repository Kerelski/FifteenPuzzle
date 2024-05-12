package org.example;

import java.util.*;

public class AStar extends Solver{

    private final int[][] correctBoard = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    private int hammingDistance(int[][] board){
        int distance = 0;
        for(int i = 0; i< board.length; i++){
            for (int j = 0 ; j< board[0].length; j++){
                if(board[i][j] != correctBoard[i][j] && board[i][j] != 0) distance++;
            }
        }
        return distance;
    }

    private int manhattanDistance(int[][] board){
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
        int depth = 0;
        StringBuilder history = new StringBuilder();

        Node root = new Node(board, depth, history);

        PriorityQueue<Node> que = null;
        
        if(parameter.equals("hamm")){
            que = new PriorityQueue<>(Comparator.comparingInt((Node node) -> hammingDistance(node.board)));
        }
        else if (parameter.equals("manh")){
            que = new PriorityQueue<>(Comparator.comparingInt((Node node) -> manhattanDistance(node.board)));
        }
        que.add(root);

        Map<Node, Node> cameFrom = new HashMap<>();
        Map<Node, Integer> totalDistance = new HashMap<>();
        totalDistance.put(root, 0);

        List<Node> processed = new ArrayList<>();
        processed.add(root);

        int visitedCounter = 0;

        Node result = root;

        while (!que.isEmpty()){
            Node currentNode = que.poll();
            visitedCounter++;

            if(Arrays.deepEquals(currentNode.board, correctBoard)){
                result = currentNode;
                break;
            }

            for(char dir : directions){
                if(movePossibility(dir, currentNode.board)){
                    int distance = totalDistance.get(currentNode) + 1;
                    Node newNode = new Node(new int[rows][cols], currentNode.depth+1 , new StringBuilder(currentNode.history));
                    for (int i = 0; i< rows; i++){
                        System.arraycopy(currentNode.board[i], 0, newNode.board[i], 0, cols);
                    }
                    swapZero(dir, newNode.board);

                    if(!totalDistance.containsKey(newNode) || distance < totalDistance.get(newNode)){
                        totalDistance.put(newNode, distance);
                        int priority = 0;
                        if(parameter.equals("hamm")) priority = distance + hammingDistance(newNode.board);
                        else if (parameter.equals("manh")) priority = distance + manhattanDistance(newNode.board);

                        boolean flag = true;
                        for (Node processedNode : processed) {
                             if (Arrays.deepEquals(processedNode.board, newNode.board)) {
                                 flag = false;
                            }
                         }
                         if(flag){
                             newNode.history.append(dir);
                             que.add(newNode);
                             processed.add(currentNode);
                             cameFrom.put(newNode, currentNode);
                    }

                    }


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
            datas[3] = String.valueOf(result.depth);
        }
        return datas;
    }


}
