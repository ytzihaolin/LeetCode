 Game of Life 


According to the Wikipedias article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article)

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

public class Solution {
    public void gameOfLife(int[][] board) {
        int m=board.length;
        int n=board[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int temp=cal(board,i,j);
                board[i][j]=(temp<<1)+(board[i][j]&1);
            }
        }
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                board[i][j]=(board[i][j]>>1);
            }
        }
    }
    
    public int cal(int[][] board,int a,int b){
        int m=board.length;
        int n=board[0].length;
        int res=0;
        for(int i=a-1;i<=a+1;i++){
            for(int j=b-1;j<=b+1;j++){
                if(i<0||i>=m||j<0||j>=n||(i==a&&j==b)) continue;
                res+=board[i][j]&1;
            }
        }
       
        if((board[a][b]&1)==1){
            if(res==2||res==3) return 1;
            else return 0;
        }else{
            if(res==3) return 1;
            else return 0;
        }
    }
}




for the infinite follow-up, use hashset to store coordinate of the living cell, and iterate throught them, 
keep counters(could also use a hashset for keeping counters) for their 8 neighbors(since a cell will only be alive if it has live neighbors), and then use a 
new set to represent future state giving counters of potiental will-be-alive cell(i.e. cell with a counter)