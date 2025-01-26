package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    // https://leetcode.com/problems/course-schedule-ii/
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

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[numCourses];

        for(int i=0; i<adjList1.size(); i++){
            ArrayList<Integer> list = adjList1.get(i);
            if(list.isEmpty()){
                System.out.println(i);
                q.add(i);
                visited[i]=true;
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
        ArrayList<Integer> ans = new ArrayList<>();


        while(!q.isEmpty()){
            int curr = q.poll();
            boolean flag = false;
            for(int pr : adjList1.get(curr)){
                if(q.contains(pr)){
                    q.add(curr);
                    flag=true;
                    break;
                }
            }
            if(flag)
                continue;

            ans.add(curr);

            ArrayList<Integer> courses = adjList2.get(curr);
            for(int course : courses){
                if(!visited[course]){
                    q.add(course);
                    visited[course]=true;
                }
            }
        }
        int[] ansArray = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            ansArray[i]=ans.get(i);
        }
        return ansArray;


    }

    private boolean detectCycle(ArrayList<ArrayList<Integer>> adjList, int n){
        boolean[] visited = new boolean[n];
        visited[0]=true;
        return isCycle(adjList, visited, 0);
    }

    private boolean isCycle(ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int node){
        ArrayList<Integer> list = adjList.get(node);
        boolean ans = false;
        for(int i : list){
            if(visited[i])
                return true;
            visited[i]=true;
            ans = ans || isCycle(adjList, visited, i);
        }
        return ans;
    }


}
