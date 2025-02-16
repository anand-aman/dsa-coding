package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalSort {
    public static void main(String[] args) {
        // INPUT
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int e = scanner.nextInt();

        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++)
            adjList.add(new ArrayList<>());

        for(int i=0; i<e; i++){
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();

            adjList.get(v1).add(v2);
        }

        List<Integer> topoSortedList = topoSort(n, adjList);
        for (int i : topoSortedList){
            System.out.print(i + " ");
        }

    }

    private static List<Integer> topoSort(int n, List<List<Integer>> adjList){
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfs(adjList, visited, stack, i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while(!stack.isEmpty()){
            ans.add(stack.pop());
        }
        return ans;
    }

    private static void dfs(List<List<Integer>> adjList, boolean[] visited, Stack<Integer> stack, int node) {
        visited[node]=true;
        for(int curr : adjList.get(node)){
            if(!visited[curr]){
                dfs(adjList, visited, stack, curr);
            }
        }
        stack.add(node);
    }

}

//[[], [3], [3], [], [0,1], [0,2]]