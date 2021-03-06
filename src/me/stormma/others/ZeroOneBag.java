package me.stormma.others;

/**
 * @author stormma
 */
public class ZeroOneBag {

    static class Solution {

        private int search(int idx, int[] w, int[] v, int n, int s, int W, int[][] memo) {
            if (idx >= n) {
                return 0;
            }

            if (memo[idx][s] != -1) {
                return memo[idx][s];
            }
            if (s + w[idx] > W) {
                return memo[idx][s] = search(idx + 1, w, v, n, s, W, memo);
            }
            return memo[idx][s] = Math.max(search(idx + 1, w, v, n, s + w[idx], W, memo) + v[idx], search(idx + 1, w, v, n, s, W, memo));
        }

        /**
         * @param w 物品重量
         * @param v 物品价值
         * @param W 背包的最大容量
         * @return 最大价值
         */
        public int solve(int[] w, int[] v, int W) {
            int n = w.length;
            final int[][] memo = new int[n][W + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= W; j++) {
                    memo[i][j] = -1;
                }
            }
            return search(0, w, v, n, 0, W, memo);
        }
    }

    static class Solution2 {
        /**
         * @param w 物品重量
         * @param v 物品价值
         * @param W 背包的最大容量
         * @return 最大价值
         */
        public int solve(int[] w, int[] v, int W) {
            int n = w.length;
            int[][] dp = new int[n + 1][W + 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= W; j++) {
                    if (j < w[i]) {
                        dp[i + 1][j] = dp[i][j];
                    } else {
                        dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
                    }
                }
            }
            return dp[n][W];
        }
    }

    static class Solution3 {
        public int solve(int[] w, int[] v, int W) {
            int n = v.length;
            int[] dp = new int[W + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = W; j >= w[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
                }
            }
            return dp[W];
        }
    }

    public static void main(String[] args) {
        int[] w = {4, 3, 2, 5, 1};
        int[] v = {3, 4, 1, 4, 2};
        int W = 10;
        System.out.println(new Solution2().solve(w, v, W));
    }
}
