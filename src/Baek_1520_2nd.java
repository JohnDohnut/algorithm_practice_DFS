import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Baek_1520_2nd {
    public static int[][] grid;
    public static int[][] dp;

    public static void solution() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int row = inputs[0];
        int col = inputs[1];

        grid = new int[row][col];
        dp = new int[row][col];

        for(int i=0; i<row; i++){
            grid[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = 1;
        dfs(row-1, col-1);
        for(int[] els : dp){
            for(int el : els){
                System.out.printf("%d ", el);
            }
            System.out.println();
        }
    }

    public static int dfs(int row, int col){
        int[] dir_row = {0,0,1,-1};
        int[] dir_col = {1,-1,0,0};


        if(row == 0 && col == 0){
            return 1;
        }

        if(dp[row][col] != -1){
            return dp[row][col];
        }

        dp[row][col] = 0;

        for(int i=0; i<4; i++){

            int out_row = row+dir_row[i];
            int out_col = col+dir_col[i];

            if(isReachable(out_row, out_col, row, col)){
                dp[row][col] += dfs(out_row, out_col);
            }
        }
        return dp[row][col];

    }

    public static boolean isReachable(int from_row, int from_col, int target_row, int target_col){
        if(from_row < 0 ||
        from_col < 0 ||
        target_row < 0 ||
        target_col < 0 ||
        from_row >= grid.length||
        target_row >= grid.length||
        from_col>=grid[0].length||
        target_col>=grid[0].length||
        grid[from_row][from_col] <= grid[target_row][target_col]){
            return false;
        }
        return true;
    }
}
