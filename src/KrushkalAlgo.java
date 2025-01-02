import java.util.*;

public class KrushkalAlgo {

    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int weight;

        public Edge(int v1, int v2, int weight){
            this.v1=v1;
            this.v2=v2;
            this.weight=weight;
        }
        public int compareTo(Edge e){
            return this.weight-e.weight;
        }
        void printEdge(){
            System.out.println(Math.min(v1, v2) + " " + Math.max(v1, v2) + " " + weight);
        }
    }

    private static int findParent(int[] parentArray, int v){
        int parent = v;
        while(parentArray[parent]!=parent){
            parent=parentArray[parent];
        }
        return parent;

        /*
        METHOD-2
        if(parentArray[v]==v){
            return v;
        }
        return findParent(parentArray, parentArray[v]);
         */
    }

    private static Edge[] unionFindAlgorithm(Edge[] edges, int n){
        //Sort in ascending order of weight
        Arrays.sort(edges);

        int[] parent = new int[edges.length];
        for(int i=0; i<parent.length; i++){
            parent[i]=i;
        }
        Edge[] mst = new Edge[n-1];
        int count=0;
        int edgeNo=0; //different variable for iterating over all edges

        while(count != n-1){
            Edge currentEdge = edges[edgeNo];
            int parentOfV1 = findParent(parent, currentEdge.v1);
            int parentOfV2 = findParent(parent, currentEdge.v2);
            if(parentOfV1 != parentOfV2){
                mst[count++]=currentEdge;
                // root parent should be updated. I made a mistake of updating parent[currentEdge.v2] to parentOfV1 once
                parent[parentOfV2]=parentOfV1;
            }
            edgeNo++;
        }
        return mst;
    }

    public static void main(String[] args) {
        //Step 1 - Input
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // No of Vertices
        int e = scanner.nextInt(); // No of Edges
        Edge[] graph = new Edge[e];

        for(int i=0; i<e; i++){
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int weight = scanner.nextInt();
            graph[i] = new Edge(v1, v2, weight);
        }

        //Step 2 - Kruskal's Algo
        Edge[] mst = unionFindAlgorithm(graph, n);

        //Step 3 - Print Minimum Spanning Tree (MST)
        for(Edge edge : mst){
            System.out.println(edge.v1+" "+ edge.v2+" "+ edge.weight);
        }

    }
}

/*
input where line 46 was causing issue.

4 5
0 1 10
0 2 6
0 3 5
1 3 15
2 3 4

 */