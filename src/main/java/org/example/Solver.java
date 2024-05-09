package org.example;

public abstract class Solver {

    abstract void solve(int[][] board, String parameter);

    public int[] findPosition(int number, int[][] board) {
        int[] position = new int[2];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == number) {
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }

    public boolean movePossibility(int direction, int[][] board){
        int zeroX = findPosition(0, board)[0];
        int zeroY = findPosition(0, board)[1];

        switch (direction) {
            case 0: {
                if (zeroX == 0) return false;
                else return true;
            }
            case 1: {
                if (zeroY == board[0].length - 1) return false;
                else return true;
            }
            case 2: {
                if (zeroX == board.length - 1) return false;
                else return true;
            }
            case 3: {
                if (zeroY == 0) return false;
                else return true;
            }
        }
        return false;
    }
    public void swapZero(int direction, int[][] board) { //0 - up, 1 - right, 2 - down, 3 - left

        int zeroX = findPosition(0, board)[0];
        int zeroY = findPosition(0, board)[1];
        int temp;

        switch (direction) {
            case 0: {
                temp = board[zeroX][zeroY];
                board[zeroX][zeroY] = board[zeroX-1][zeroY];
                board[zeroX-1][zeroY] = temp;
                break;
            }
            case 1: {
                temp = board[zeroX][zeroY];
                board[zeroX][zeroY] = board[zeroX][zeroY+1];
                board[zeroX][zeroY+1] = temp;
                break;
            }
            case 2: {
                temp = board[zeroX][zeroY];
                board[zeroX][zeroY] = board[zeroX+1][zeroY];
                board[zeroX+1][zeroY] = temp;
                break;
            }
            case 3: {
                temp = board[zeroX][zeroY];
                board[zeroX][zeroY] = board[zeroX][zeroY-1];
                board[zeroX][zeroY-1] = temp;
                break;
            }

        }

    }

    public String boardToString(int[][] board){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i< board.length; i++){
            for (int j = 0; j<board[0].length; j++){
                result.append(board[i][j]);
            }
        }
        return result.toString();
    }

    public String generateCorrectBoard(int[][] board){
        StringBuilder result = new StringBuilder();
        int number = 1;
        for (int i = 0; i< board.length; i++){
            for (int j = 0; j<board[0].length; j++){
                if(number == 16) result.append(0);
                else {
                    result.append(number);
                    number++;
                }
            }
        }
        return result.toString();
    }
}
