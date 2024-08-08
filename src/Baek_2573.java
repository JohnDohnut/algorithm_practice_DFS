import java.util.*;
import java.io.*;

public class Baek_2573 {
    public static int[][] grid;
    public static int[][] surrounded;
    public static boolean[][] visited;
    public static int[] dir_row = new int[] {0,0,1,-1};
    public static int[] dir_col = new int[] {1,-1,0,0};

    public void solution() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = inputs[0];
        int col = inputs[1];

        grid = new int[row][col];
        visited = new boolean[row][col];
        surrounded = new int[row][col];

        for(int i=0; i<row; i++){
            grid[i] =  Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(surrounded[i],0);
        }
        int iceburgs = 0;
        int years = 0;
        while(iceburgs <2){
            iceburgs = 0;
            boolean melt = true;
            for(int[] surroundings : surrounded){
                Arrays.fill(surroundings, 0);
            }

            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    if(!isSea(i, j)){
                        melt = false;
                        surrounded[i][j] = isSurrounded(i, j);
                    }
                }
            }
            if (melt) {
                years = 0;
                break;
            }

            for(int i=1; i<row-1; i++){
                for(int j=1; j<col-1; j++){
                    if(!isSea(i, j)){
                        grid[i][j] -= surrounded[i][j];
                        if (grid[i][j] < 0)
                            grid[i][j] = 0;
                    }
                }
            }


            for(boolean[] visits : visited){
                Arrays.fill(visits, false);

            }

            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    if(isReachable(i, j) && !visited[i][j]){
                        if(iceburgs >= 1){break;}
                        DFS(i, j);
                        iceburgs ++;
                    }
                }
            }

//            for(int i=0; i<row; i++){
//                for(int j=0; j<col; j++){
//                    System.out.printf("%d ", grid[i][j]);
//                }
//                System.out.println();
//            }


            years ++;
        }
        System.out.println(years);

    }
    public boolean DFS(int row, int col){
        if(isSea(row, col)) return false;
        Stack<int[]> stack = new Stack<>();
        stack.push(coord(row, col));
        visited[row][col] = true;
        while(!stack.isEmpty()){
            int[] curr = stack.pop();

            for(int i=0; i<4; i++){
                int next_row = curr[0] + dir_row[i];
                int next_col = curr[1] + dir_col[i];

                if(isReachable(next_row, next_col) && ! visited[next_row][next_col]){
                    stack.push(coord(next_row, next_col));
                    visited[next_row][next_col] = true;

                }

            }
        }
        return true;
    }

    public int[] coord(int row, int col){
        return new int[] {row, col};
    };

    public boolean isInGrid(int row, int col){
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length){
            return false;
        }
        return true;
    }

    public boolean isSea(int row, int col){
        if(grid[row][col] == 0) return true;
        return false;
    }

    public boolean isReachable(int row, int col){
        if(isInGrid(row, col) && !isSea(row, col)) {return true;}
        return false;

    }

    public int isSurrounded(int row, int col){
        int count = 0;
        for(int i=0; i<4; i++){

            int next_row = row + dir_row[i];
            int next_col = col + dir_col[i];

            if(isSea(next_row, next_col)) count ++;

        }
        return count;
    }
}
