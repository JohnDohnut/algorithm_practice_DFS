import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek_11048_bottom_up {
    public static int[][] grid;
    public static int[][] dp;
    public static int[] dir_row = new int[] {1, 0, 1};
    public static int[] dir_col = new int[] {0, 1, 1};
    public static final int DIR = 3;

    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = inputs[0];
        int col = inputs[1];

        grid = new int[row][col];
        dp = new int[row][col];

        for(int i=0; i<row; i++){
            Arrays.fill(dp[i] , 0);
            grid[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        dp[0][0] = grid[0][0];

        dfs(0, 0);

        for(int[] els : dp){
            for(int el : els){
                System.out.printf("%d ", el);
            }
            System.out.println();
        }
        System.out.println(dp[dp.length-1][dp[0].length-1]);
    }

    public int dfs(int row, int col){
        if(row == 0 && col == 0 ){
            return dp[row][col];
        }
        for(int i=0; i<DIR; i++){
            int next_row = row - dir_row[i];
            int next_col = col - dir_col[i];
            if(isReachable(new_row, new_col)) {
                dp[next_row][next_col] =
                        Math.max()
            }

        }
        return dp[row][col];
    }

    public boolean isReachable(int row, int col){
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length){
            return false;
        }
        return true;
    }
}
