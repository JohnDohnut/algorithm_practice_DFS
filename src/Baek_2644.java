import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class Baek_2644 {
    public static int[] weights;
    public static ArrayList<ArrayList<Integer>> adjs = new ArrayList<>();
    public static boolean[] visited;
    public void solution() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(br.readLine());

        weights = new int[nodes+1];
        visited = new boolean[nodes+1];
        Arrays.fill(visited, false);
        Arrays.fill(weights, 0);
        for(int i=0; i<=nodes; i++){
            adjs.add(new ArrayList<Integer>());

        }

        int[] targets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int edges = Integer.parseInt(br.readLine());

        for(int i=1; i<=edges; i++){
            int[] pair = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            adjs.get(pair[0]).add(pair[1]);
            adjs.get(pair[1]).add(pair[0]);
        }

        DFS(targets[0]);
        int answer = weights[targets[1]];
        if(answer == 0){
            System.out.println(-1);
        }
        else{
            System.out.println(answer);
        }

    }

    public void DFS(int target){
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(target);
        visited[target] = true;
        while(!stack.isEmpty()){
            int curr = stack.pop();
            ArrayList<Integer> adj = adjs.get(curr);
            for(int node : adj){
                if(!visited[node]){
                    stack.push(node);
                    visited[node]=true;
                    weights[node]=weights[curr] + 1;
                }
            }
        }
    }


}
