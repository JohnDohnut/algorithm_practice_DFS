import java.util.*;
import java.io.*;

public class Baek_2583 {
    public static int[][] grid;
    public static boolean[][] visited;
    public static int row_size;
    public static int col_size;
    public static int squares;
    public void solution() throws IOException{
        //pivot ; left, bottom
        // grid is vertically flopped
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        row_size = inputs[0];
        col_size = inputs[1];
        squares = inputs[2];

        grid = new int[row_size][col_size];
        visited = new boolean[row_size][col_size];

        Arrays.stream(grid).forEach(x->Arrays.fill(x, 1));

        for(int i=0; i<squares ; i++){
            inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int left = inputs[0];
            int right = inputs[2];
            int top = inputs[3];
            int bottom = inputs[1];
            for(int row = bottom; row<top; row++){
                for(int col = left; col<right; col++){

                    grid[row][col] = 0;
                    visited[row][col] = true;

                }
            }
        }

        int count = 0;
        ArrayList<Integer> sizes = new ArrayList<>();


        for(int row = 0; row < grid.length; row ++){
            for(int col = 0; col < grid[0].length; col++){
                if(isReachable(row, col) && !visited[row][col]){
                    sizes.add(DFS(row, col));
                    count ++;
                }
            }
        }
        System.out.println(count);
        sizes.sort((a,b) -> a - b);
        sizes.forEach(x-> System.out.printf("%d ", x));
    }


    public int DFS(int row, int col){

        int size = 0;

        int[] dir_row = {0,0,1,-1};
        int[] dir_col = {1,-1,0,0};

        Stack<int[]> stack = new Stack<>();
        stack.push(coord(row, col));
        visited[row][col] = true;

        while(!stack.empty()){
            int[] curr = stack.pop();
            size ++;
            for(int i=0; i<4; i++){
                int new_row = curr[0] + dir_row[i];
                int new_col = curr[1] + dir_col[i];

                if(isReachable(new_row, new_col) && !visited[new_row][new_col]) {
                    stack.push(coord(new_row, new_col));
                    visited[new_row][new_col] = true;
                }
            }
        }
        return size;
    }

    public int[] coord(int row, int col){
        return new int[] {row, col};
    }

    public boolean isReachable(int row, int col){
        if (row < 0 || row >= grid.length || col < 0 || col >=grid[0].length || grid[row][col] == 0){
            return false;
        }
        return true;

    }
}
