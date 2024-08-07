import java.util.*;
import java.io.*;

public class Baek_1520 {
    public static int[][] grid;
    public static int[][] visited;

    public static void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int row = inputs[0];
        int col = inputs[1];

        grid = new int[row][col];
        visited = new int[row][col];

        for(int i=0; i<row; i++){
            grid[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(visited[i], 0);
        }

        DFS();

        for(int [] els : visited){
            for(int el : els ){
                System.out.printf("%d ", el);
            }
            System.out.println();
        }
    }

    public static void DFS(){
        int[] dir_row = new int[] {0,0,1,-1};
        int[] dir_col = new int[] {1,-1,0,0};

        Stack<int[]> stack = new Stack<>();
        stack.push(coord(0,0));
        visited[0][0] += 1;

        while(!stack.isEmpty()){
            int[] curr = stack.pop();

            for(int i=0; i<4; i++){
                int new_row = curr[0] + dir_row[i];
                int new_col = curr[1] + dir_col[i];

                if(isReachable(curr[0], curr[1], new_row, new_col)){
                    stack.push(coord(new_row, new_col));
                    visited[new_row][new_col]++;

                }

            }
        }



    }

    public static int[] coord(int row, int col){
        return new int[] {row, col};
    }

    public static boolean isReachable (int from_row, int from_col, int to_row, int to_col){
        if(to_row<0
            || to_col <0
            || to_row >= grid.length
            || to_col >= grid[0].length
            ||  grid[from_row][from_col] <= grid[to_row][to_col] ){
            return false;
        }
        return true;

    }
}
