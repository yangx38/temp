// T = O(n^n)
// S = O(n)
class Solution {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(board[i], '.');
        helper(board, 0);
        return res;
    }

    private void helper(char[][] board, int rowPos) {
        if(rowPos == board.length) {
            res.add(generateBoard(board));
            return;
        }

        for(int colRos = 0; colRos < board.length; colRos++) {
            if(isValid(board, rowPos, colRos)) {
                board[rowPos][colRos] = 'Q';
                helper(board, rowPos+1);
                board[rowPos][colRos] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int rowPos, int colRos) {
        for(int i = 0; i < rowPos; i++) {
            if(board[i][colRos] == 'Q') return false;
        }
        for(int i = rowPos, j = colRos; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 'Q') return false;
        }
        for(int i = rowPos, j = colRos; i >= 0 && j < board.length; i--, j++) {
            if(board[i][j] == 'Q') return false;
        }
        return true;
    }

    private List<String> generateBoard(char[][] board) {
        List<String> resBoard = new ArrayList<>();
        for(char[] b : board) {
            String row = "";
            for(char c : b) {
                row += c;
            }
            resBoard.add(row);
        }
        return resBoard;
    } 
}