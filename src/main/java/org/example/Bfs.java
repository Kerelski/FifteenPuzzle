package org.example;

import java.sql.Array;
import java.util.*;

public class Bfs extends Solver {

    public void solve(int[][] board, String parameter) {

        char[] charArray = parameter.toCharArray();
        int[] directions = new int[4];
        int index = 0;
        for (char letter : charArray){
            if(letter == 'U') directions[index] = 0;
            else if (letter == 'R') directions[index] = 1;
            else if (letter == 'D') directions[index] = 2;
            else if (letter == 'L') directions[index] = 3;
            index++;
        }

        System.out.println(directions[0]);
        System.out.println(directions[1]);
        System.out.println(directions[2]);
        System.out.println(directions[3]);

        String correctBoard = generateCorrectBoard(board);

        int rows = board.length;
        int cols = board[0].length;

        int maxDepth = 8;
        int depth = 0;
        StringBuilder history = new StringBuilder();

        Node root = new Node(board, depth, history);

//        Queue<Node> que = new LinkedList<>();
//        que.offer(root);

        Queue<int[][]> que = new LinkedList<>();
        que.offer(board);

        List<int[][]> visited = new ArrayList<>();




        while (!que.isEmpty()){
            int[][] currentBoard = que.poll();
            visited.add(currentBoard);

            String sCurrentBoard = boardToString(currentBoard);
            if(sCurrentBoard.equals(correctBoard)){
                System.out.println(sCurrentBoard + "rozwiazany");
                break;
            }

            for(int dir : directions){
                if(movePossibility(dir, currentBoard)){
                    System.out.println(dir);
                    int[][] newBoard = new int[rows][cols];
                    for (int i = 0; i< rows; i++){
                        System.arraycopy(currentBoard[i], 0, newBoard[i], 0, cols);
                    }
                    swapZero(dir, newBoard);
                    System.out.println(boardToString(newBoard));
                    boolean flag = true;
                    for (int[][] ints : visited) {
                        if (Arrays.equals(ints, newBoard)) {
                            flag = false;
                        }
                    }
                    if(flag){
                        history.append(dir);
                        que.offer(newBoard);
                    }

                }
            }

        }
        System.out.println(history.toString() + "ruchy");
    }

}
