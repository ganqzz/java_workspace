package algorithms;

/**
 * A Dynamic Programming based solution for 0-1 Knapsack problem
 */
class KnapsackDp {

    // Returns the maximum value that can be put in a knapsack of capacity W
    public static int knapsack(int W, int[] wt, int[] val, int n) {
        // Populate base cases with 0
        int[][] K = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]],
                                       K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][W];
    }

    public static void main(String[] args) {
        int[] val = new int[]{60, 100, 120};
        int[] wt = new int[]{10, 20, 30};
        int W = 50;
        int n = val.length;
        System.out.println(knapsack(W, wt, val, n));
    }
}
