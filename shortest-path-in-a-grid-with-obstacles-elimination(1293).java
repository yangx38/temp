// T = O(mnk)
// S = O(mnk)
    // for each cell, wst case put that cell into queue k times, store all paths in the visited set
class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int shortestPath(int[][] grid, int k) {
        if(grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});

        int[][] visited = new int[m][n];
        for(int i = 0; i < m; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        visited[0][0] = 0;
        
        int level = -1;
        while(!queue.isEmpty()) {
            level++;
            int size = queue.size(); 
            for(int ii = 0; ii < size; ii++) {
                int[] cur = queue.poll();
                int i = cur[0], j = cur[1], curK = cur[2];
                if(i == m-1 && j == n-1) return level;

                for(int[] d : dirs) {
                    int newI = i+d[0], newJ = j+d[1];
                    if(newI < 0 || newI >= m || newJ < 0 || newJ >= n) continue;
                    int obstacles = grid[newI][newJ] == 1 ? curK+1 : curK;
                    if(obstacles > k || obstacles >= visited[newI][newJ]) continue;

                    visited[newI][newJ] = obstacles;
                    queue.offer(new int[]{newI, newJ, obstacles});
                }
            }
        }
        return -1;
    }
}