class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        int[][] memo = new int[n][n + 1];
        return solve(0, 1, piles, suffixSum, memo);
    }
    private int solve(int i, int M, int[] piles, int[] suffixSum, int[][] memo) {
        int n = piles.length;
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }
        if (memo[i][M] != 0) {
            return memo[i][M];
        }

        int maxStones = 0;
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            int opponentStones = solve(i + X, nextM, piles, suffixSum, memo);
            int currentStones = suffixSum[i] - opponentStones;
            maxStones = Math.max(maxStones, currentStones);
        }
        memo[i][M] = maxStones;
        return memo[i][M];
    }
}