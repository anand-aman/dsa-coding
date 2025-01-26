package LeetCode;

import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjList1 = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            adjList1.add(new ArrayList<>());
        }

        for(int i=0; i<prerequisites.length; i++){
            int j = prerequisites[i][0];
            int k = prerequisites[i][1];
            adjList1.get(j).add(k);
        }

        if(detectCycle(adjList1, numCourses))
            return new int[0];

        boolean[] visited = new boolean[numCourses];
        ArrayList<Integer> itr = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=adjList1.size()-1; i>=0; i--){
            ArrayList<Integer> list = adjList1.get(i);
            System.out.println(i+" -> "+ list);
            if(list.isEmpty()){
                itr.add(i);
            }
        }

        ArrayList<ArrayList<Integer>> adjList2 = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            adjList2.add(new ArrayList<>());
        }
        for(int i=0; i<prerequisites.length; i++){
            int j = prerequisites[i][0];
            int k = prerequisites[i][1];
            adjList2.get(k).add(j);
        }

        for(int item : itr){
            if(visited[item])
                continue;
            Queue<Integer> q = new LinkedList<>();
            q.add(item);
            visited[item]=true;
            while(!q.isEmpty()){
                int curr = q.poll();
                ans.add(curr);

                ArrayList<Integer> courses = adjList2.get(curr);
                for(int course : courses){
                    if(!visited[course]){
                        q.add(course);
                        visited[course]=true;
                    }
                }
            }
        }

        int[] ansArray = new int[(ans.size())];
        int j=0;
        while(j<ans.size()){
            ansArray[j]=ans.get(j);
            j++;
        }
        return ansArray;


    }

    private boolean detectCycle(ArrayList<ArrayList<Integer>> adjList, int n){
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!visited[i]){
                // System.out.print("\nComponent: "+i+" --> ");
                Set<Integer> path = new HashSet<>();
                path.add(i);
                visited[i]=true;
                if(isCycle(adjList, visited, i, path))
                    return true;
            }
        }
        return false;
    }

    private boolean isCycle(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int node, Set<Integer> path){
        ArrayList<Integer> list = adjList.get(node);
        path.add(node);
        boolean ans = false;
        for(int i : list){
            // System.out.print(node+", ");
            if(visited[i] && path.contains(i))
                return true;
            visited[i]=true;
            Set<Integer> newPath = new HashSet<>(path);
            newPath.add(i);
            ans = ans || isCycle(adjList, visited, i, newPath);
        }

        return ans;
    }


}