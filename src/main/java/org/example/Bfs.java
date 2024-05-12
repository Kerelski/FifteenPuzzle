package org.example;

import java.sql.Array;
import java.util.*;

public class Bfs extends Solver {

    public String[] solve(int[][] board, String parameter) {

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

        List<Node> processed = new ArrayList<>();
        processed.add(root);
        int visitedCounter = 0;

        Node result = root;

        while (!que.isEmpty() && depth < maxDepth){
            Node currentNode = que.poll();
            visitedCounter++;

            if(depth < currentNode.depth){
                depth = currentNode.depth;
            }

            String sCurrentBoard = boardToString(currentNode.board);
            if(sCurrentBoard.equals(correctBoard)){
                result = currentNode;
                break;
            }



            for(char dir : directions){

                if(movePossibility(dir, currentNode.board)){

                    Node newNode = new Node(new int[rows][cols], currentNode.depth+1 , new StringBuilder(currentNode.history));
                    for (int i = 0; i< rows; i++){
                        System.arraycopy(currentNode.board[i], 0, newNode.board[i], 0, cols);
                    }
                    swapZero(dir, newNode.board);

                    boolean flag = true;
                    for (Node processedNode : processed) {
                        if (Arrays.deepEquals(processedNode.board, newNode.board)) {
                            flag = false;
                        }
                    }
                    if(flag){
                        newNode.history.append(dir);
                        que.offer(newNode);
                        processed.add(currentNode);
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
