// T = O(nlog(n)) - n as path length
// S = O(n) 
class Solution {
    int m, n;
    int[][] visited;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int maximumMinimumPath(int[][] A) {
        if(A == null || A.length == 0) return 0;
        m = A.length; n = A[0].length;
        visited = new int[m][n]; 
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)->b[2]-a[2]);
        queue.offer(new int[]{0, 0, A[0][0]}); // (i, j, w) - w: min of the path
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0], j = cur[1], w = cur[2];
            if(i == m-1 && j == n-1) return w;
            
            for(int[] d : dirs) {
                int subres = w;
                int newI = i+d[0], newJ = j+d[1];
                if(newI < 0 || newI >= m || newJ < 0 || newJ >= n) continue;
                if(visited[newI][newJ] == 1) continue;
                subres = Math.min(subres, A[newI][newJ]);
                visited[newI][newJ] = 1;
                queue.offer(new int[]{newI, newJ, subres});
            }
        }
        return -1;
    }
}