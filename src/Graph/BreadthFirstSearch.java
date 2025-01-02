package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BreadthFirstSearch {

    private static ArrayList<Integer> bfsTraversal(int n, ArrayList<ArrayList<Integer>> adjList){
        // n is number of vertices
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] isVisited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0); // starting vertex
         while(!queue.isEmpty()){
             int v = queue.poll();
             bfs.add(v);


             //iterating over all the adjacent vertices of the vertex V.
             for(int i : adjList.get(v)){
                 if(!isVisited[i]) {
                     isVisited[i]=true; // we are marking visited to avoid repetition of those nodes which are connected to multiple nodes from previous level
                     queue.add(i);
                 }
             }
         }

        return bfs;
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

        ArrayList<Integer> bfs = bfsTraversal(n, adjList);

        for(int i : bfs)
            System.out.print(i+" ");
    }
}


/*

8 8
0 1
0 5
1 2
1 3
5 6
5 7
3 4
6 4



 */
