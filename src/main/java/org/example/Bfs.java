package org.example;

import java.sql.Array;
import java.util.*;

public class Bfs extends Solver {

    public String solve(int[][] board, String parameter) {

        char[] directions = parameter.toCharArray();

        String correctBoard = generateCorrectBoard(board);


        int rows = board.length;
        int cols = board[0].length;

        int maxDepth = 8;
        int depth = 0;
        StringBuilder history = new StringBuilder();

        Node root = new Node(board, depth, history);

        Queue<Node> que = new LinkedList<>();
        que.offer(root);

        List<Node> visited = new ArrayList<>();

        Node result = root;

        while (!que.isEmpty() && depth < maxDepth){
            Node currentNode = que.poll();
            visited.add(currentNode);

            String sCurrentBoard = boardToString(currentNode.board);
            if(sCurrentBoard.equals(correctBoard)){

                result = currentNode;
                break;
            }

            if(depth < currentNode.depth){
                depth = currentNode.depth;
            }

            for(char dir : directions){

                if(movePossibility(dir, currentNode.board)){

                    Node newNode = new Node(new int[rows][cols], currentNode.depth+1 , new StringBuilder(currentNode.history));
                    for (int i = 0; i< rows; i++){
                        System.arraycopy(currentNode.board[i], 0, newNode.board[i], 0, cols);
                    }
                    swapZero(dir, newNode.board);

                    boolean flag = true;
                    for (Node visitedNode : visited) {
                        if (Arrays.equals(visitedNode.board, newNode.board)) {
                            flag = false;
                        }
                    }
                    if(flag){
                        newNode.history.append(dir);
                        que.offer(newNode);
                    }

                }
            }

        }

        if (result == root){

            return "-1";
        }

        return  result.history.toString();
    }

}
