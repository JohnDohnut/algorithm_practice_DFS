import java.util.*;
import java.io.*;

public class Baek_1973 {
    public static int[][] grid;
    public static int[][] dp;
    public static boolean[][] visited;
    public static int[] dir_row = {0,0,1,-1};
    public static int[] dir_col = {1,-1,0,0};
    public static final int DIR = 4;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        grid = new int[size][size];
        dp = new int[size][size];
        visited = new boolean[size][size];
        for(int i=0; i<size; i++){
            grid[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], 1);
            Arrays.fill(visited[i], false);
        }

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(!visited[i][j]){
                    dfs(i,j);
                }
            }
        }

        for(int[] els : dp){
            for(int el : els){
                System.out.printf("%d ", el);
            }
            System.out.println();
        }





    }

    public int dfs(int row, int col){

        if(visited[row][col]){
            return dp[row][col];
        }
        if(!visited [row][col]){
            visited [row][col] = true;
            for(int i=0; i<DIR; i++){
                int out_row = row + dir_row[i];
                int out_col = col + dir_col[i];

                if(isReachable(out_row, out_col, row, col)){
                    dp[row][col] = Math.max(dfs(out_row, out_col)+1, dp[row][col]);
                }

            }
        }
        return dp[row][col];
    }

    public boolean isReachable(int from_row, int from_col, int target_row, int target_col){
        if(from_row < 0 ||
                from_col < 0 ||
                target_row < 0 ||
                target_col < 0 ||
                from_row >= grid.length||
                target_row >= grid.length||
                from_col>=grid[0].length||
                target_col>=grid[0].length||
                grid[from_row][from_col] >= grid[target_row][target_col]){
            return false;
        }
        return true;
    }
}
