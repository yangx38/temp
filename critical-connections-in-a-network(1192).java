// T = O(V+E)
// S = O(V+E)
class Solution {
    List<Integer>[] graph;
    int[] dfsNum, low;
    int dfscounter = 0;
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new List[n];
        for(int i = 0; i <graph.length; i++) graph[i] = new ArrayList<>();
        for(List<Integer> connection : connections) {
            int u = connection.get(0), v = connection.get(1);
            graph[u].add(v); 
            graph[v].add(u);
        }

        dfsNum = new int[n];
        low = new int[n];
        Arrays.fill(dfsNum, -1);

        for(int u = 0; u < n; u++) {
            if(dfsNum[u] == -1) {
                dfs(u, u);
            }
        }
        return res;
    }

    private void dfs(int u, int pre) {
        dfsNum[u] = dfscounter++; // discover u
        low[u] = dfsNum[u];
        // (u, v)
        for(int v : graph[u]) {
            if(v == pre) continue;
            if(dfsNum[v] == -1) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);
                if(low[v] > dfsNum[u]) res.add(Arrays.asList(u, v));
            }
            else low[u] = Math.min(low[u], dfsNum[v]);
        }
    }
}