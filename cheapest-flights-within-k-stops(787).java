// T = O(NlogN + E) - N: #of vertices
// S = O(N+E)
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<int[]>[] graph = new List[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(int[] flight : flights) {
            int u = flight[0], v = flight[1], w = flight[2];
            graph[u].add(new int[]{v, w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1]-b[1]); // node, dist, restNodes
        pq.offer(new int[]{src, 0, K+1});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], curDist = cur[1], remain = cur[2];
            if(u == dst) return curDist;
            if(remain > 0) {
                List<int[]> neighbors = graph[u];
                for(int[] ne : neighbors) {
                    int v = ne[0], w = ne[1];
                    pq.offer(new int[]{v, curDist + w, remain-1});
                }
            }
        }
        return -1;
    }
}