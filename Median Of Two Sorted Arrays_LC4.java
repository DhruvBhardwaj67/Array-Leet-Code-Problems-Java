class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        int[][][][] jump = new int[m][n][2][];

        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (grid[r][c] == 1) {
                    if (r + 1 < m) {
                        jump[r][c][0] = (grid[r + 1][c] == 1) ? jump[r + 1][c][1] : new int[]{r + 1, c};
                    }

                    if (c + 1 < n) {
                        jump[r][c][1] = (grid[r][c + 1] == 1) ? jump[r][c + 1][0] : new int[]{r, c + 1};
                    }
                }
            }
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (dp[r][c] == 0) continue;

                if (c + 1 < n) {
                    int[] target = (grid[r][c + 1] == 1) ? jump[r][c + 1][0] : new int[]{r, c + 1};
                    if (target != null) {
                        dp[target[0]][target[1]] = (dp[target[0]][target[1]] + dp[r][c]) % MOD;
                    }
                }

                if (r + 1 < m) {
                    int[] target = (grid[r + 1][c] == 1) ? jump[r + 1][c][1] : new int[]{r + 1, c};
                    if (target != null) {
                        dp[target[0]][target[1]] = (dp[target[0]][target[1]] + dp[r][c]) % MOD;
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
