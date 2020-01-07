// Bellman Ford - can neg weight, no neg cycle
// T = O(VE) 
// S = O(V)
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N+1]; Arrays.fill(dist, Integer.MAX_VALUE); dist[K] = 0;

        for(int i = 0; i < N-1; i++) {
            // for all edges
            for(int[] time : times) {
                int u = time[0], v = time[1], w = time[2];  // w即(u, v)
                if(dist[u] != Integer.MAX_VALUE) {
                    dist[v] = Math.min(dist[v], dist[u]+w);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i < dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE) return -1;
            else max = Math.max(max, dist[i]);
        }
        return max;
    }
}

// Dijkstra - no neg weight
// T = O(NlogN + E) - N: #of vertices
// S = O(N+E)
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N+1]; Arrays.fill(dist, Integer.MAX_VALUE); dist[K] = 0;

        // build graph 
        List<int[]>[] graph = new List[N+1];
        for(int i = 1; i < graph.length; i++) graph[i] = new ArrayList<>();
        for(int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            graph[u].add(new int[]{v, w});
        }
        // pq
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> a[1]-b[1]); // node, dist
        pq.offer(new int[]{K, 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            List<int[]> neighbors = graph[u];
            for(int[] ne : neighbors) {
                int v = ne[0], w = ne[1];
                if(dist[v] > dist[u] + w) {
                    dist[v] = dist[u]+w;
                    pq.offer(new int[]{v, dist[v]});
                } 
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i < dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
    }
}