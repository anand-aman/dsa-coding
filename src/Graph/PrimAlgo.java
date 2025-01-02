package Graph;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PrimAlgo {

    public static void main(String[] args) {
        // Input
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // No of Vertices
        int e = scanner.nextInt(); // No of Edges

        int[][] adjMatrix = new int[n][n];

        for(int i=0; i<e; i++){
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int weight = scanner.nextInt();

            adjMatrix[v1][v2]=weight;
            adjMatrix[v2][v1]=weight;
        }

        // Prim's Algorithm Code

        int[] parent = new int[n];
        int[] weight = new int[n];
        Set<Integer> unvisited = new HashSet<>();

        for(int i=0; i<n; i++){
            weight[i]=Integer.MAX_VALUE;
            unvisited.add(i);
        }

        weight[0] = 0;
        parent[0]=-1;

        while(!unvisited.isEmpty()){
            int minVertex = findMinVertex(unvisited, weight);
            // iterating over all neighbours of minVertex
            for(int i=0; i<n; i++){
                if(adjMatrix[minVertex][i]>0){ //this condition means edge exists
                    int w = adjMatrix[minVertex][i];
                    if(w < weight[i] && unvisited.contains(i)){ // Important Condition
                        weight[i]=w;
                        parent[i]=minVertex;
                    }
                }
            }
        }
        // Printing MST

        for(int i=1; i<n; i++){
            int v1 = i;
            int v2 = parent[i];
            int w = weight[i];

            System.out.println(v1 + " " + v2 + " " + w);
        }


    }

    private static int findMinVertex(Set<Integer> unvisited, int[] weight){
        int minVertex=-1;
        int minWeight=Integer.MAX_VALUE;
        for(int vertex : unvisited){
            if(weight[vertex]<minWeight){ // for an unvisited vertex, if weight of the parent and vertex is less than previous weight
                minVertex=vertex;
                minWeight=weight[vertex];
            }
        }
        unvisited.remove(minVertex); //remove min vertex
        return minVertex;
    }
}


/*
Sample Input:
5 7
0 1 4
0 2 8
1 3 6
1 2 2
2 4 9
2 3 3
3 4 5


Sample Output:
1 0 4
2 1 2
3 2 3
4 3 5
 */