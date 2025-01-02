import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DijkstraAlgo {

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

        //Dijsktra Algorithm implementation

        int[] weightFromSrc = new int[n];
        Set<Integer> unvisited = new HashSet<>();

        //initialize parent & weightFromSrc
        for(int i=1; i<n; i++){
            weightFromSrc[i]=Integer.MAX_VALUE;
            unvisited.add(i);
        }
        weightFromSrc[0]=0;
        unvisited.add(0);

        while(!unvisited.isEmpty()){
            int minVertex = findMinVertex(unvisited, weightFromSrc);
            unvisited.remove(minVertex);
            int weightTillMinVertex = weightFromSrc[minVertex];

            //Exploring neighbours of Min Vertex (current vertex)
            for(int i=0; i<n; i++){
                if(adjMatrix[minVertex][i] > 0 && unvisited.contains(i)){
                    if(weightTillMinVertex + adjMatrix[minVertex][i] < weightFromSrc[i]){
                        weightFromSrc[i] = weightTillMinVertex + adjMatrix[minVertex][i];
                    }
                }
            }
        }

        //Print Vertex and it's shortest distance from Source i.e. 0
        for(int i=0; i<n; i++){
            System.out.println(i + " " + weightFromSrc[i]);
        }



    }

    private static int findMinVertex(Set<Integer> unvisited, int[] weightFromSrc){
        int minVertex=-1;
        int minVertexWeight=Integer.MAX_VALUE;
        for(int i : unvisited){
            if(weightFromSrc[i] < minVertexWeight) {
                minVertex = i;
                minVertexWeight = weightFromSrc[minVertex];
            }
        }
        return minVertex;
    }

}
