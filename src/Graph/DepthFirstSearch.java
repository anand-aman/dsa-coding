package Graph;

import java.util.ArrayList;
import java.util.Scanner;

public class DepthFirstSearch {

    private static void dfsTraversal(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> dfs){
        visited[node]=true;
        dfs.add(node);

        for(int i=0; i<adjList.get(node).size(); i++){
            int nextNode = adjList.get(node).get(i);
            if(!visited[nextNode])
                dfsTraversal(nextNode, visited, adjList, dfs);
        }
    }


    public static void main(String[] args) {
        // Input
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // No of Vertices
        int e = scanner.nextInt(); // No of Edges

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            adjList.add(new ArrayList<Integer>());
        }

        for(int i=0; i<e; i++){
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            adjList.get(v1).add(v2);
            adjList.get(v2).add(v1);
        }
        boolean[] visited = new boolean[n];

        ArrayList<Integer> dfs = new ArrayList<>();
        dfsTraversal(0, visited, adjList, dfs);

        for(int i : dfs)
            System.out.print(i+" ");
    }

}
