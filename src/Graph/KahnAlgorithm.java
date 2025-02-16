package Graph;

import java.util.*;

public class KahnAlgorithm {

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

        List<Integer> topoSortedList = kahnAlgo(n, adjList);
        for (int i : topoSortedList){
            System.out.print(i + " ");
        }

    }

    private static List<Integer> kahnAlgo(int n, List<List<Integer>> adjList){
        // In-degree
        int[] inDegree = new int[n];
        for(int i=0; i<n; i++){
            for(int j : adjList.get(i)){
                inDegree[j]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<n; i++){
            if(inDegree[i]==0)
                queue.add(i);
        }
        List<Integer> topoSort = new ArrayList<>();

        while(!queue.isEmpty()){
            int node = queue.poll();
            topoSort.add(node);
            List<Integer> listOfAdjacentNodes = adjList.get(node);

            for(int i : listOfAdjacentNodes){
                inDegree[i]--;
                if(inDegree[i]==0)
                    queue.add(i);
            }
        }
        return topoSort;

    }
}
