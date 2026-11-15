import java.util.Arrays;

public class DynamicProgramming {

    // ============ Notes ============ //

    //  Step 1: dimensions = changing parameters
    //  Step 2: write the definition line, always
    //  dp[i][j] = <exactly what f(i, j) returns, in words>
    //  Step 3: base cases become pre-filled cells
    //  Step 4: the loop direction for each dimension

    //  The call moves…	So loop…
    //  f(i + 1, …) (index goes up)	i from high to low
    //  f(i - 1, …) (index goes down)	i from low to high
    //  f(…, j - something) (shrinks)	j from low to high

    //  Step 5: the loop body is the memo’s return line

    // ===== Shared memo tables ===== //

    // int / long: fill with -1 to mean "not computed yet".
    private int[] dp1;
    private int[][] dp2;
    private int[][][] dp3;

    private long[] dpLong1;
    private long[][] dpLong2;
    private long[][][] dpLong3;

    // Boolean (wrapper, not primitive): null = "not computed yet"
    private Boolean[] dpBool1;
    private Boolean[][] dpBool2;
    private Boolean[][][] dpBool3;

    private static void fill(int[][] arr, int val) {
        for (int[] row : arr) Arrays.fill(row, val);
    }

    private static void fill(int[][][] arr, int val) {
        for (int[][] grid : arr) fill(grid, val);
    }

    private static void fill(long[][] arr, long val) {
        for (long[] row : arr) Arrays.fill(row, val);
    }

    private static void fill(long[][][] arr, long val) {
        for (long[][] grid : arr) fill(grid, val);
    }

    // Climbing Stairs
    public int climbStairs(int n) {
        dp1 = new int[n + 1];
        Arrays.fill(dp1, -1);
        return climbStairsMemoized(n);
    }

    public int climbStairsMemoized(int n) {
        if (n == 0) {
            return dp1[n] = 1;
        }
        if (n < 0) {
            return 0;
        }

        if (dp1[n] != -1) {
            return dp1[n];
        }

        return dp1[n] = climbStairsMemoized(n - 1) + climbStairsMemoized(n - 2);
    }

    public int climbStairsDP(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // House Robber
    public int rob(int[] nums) {
        dp1 = new int[nums.length + 1];
        Arrays.fill(dp1, -1);
        return robMemoized(nums, 0);
    }

    public int robMemoized(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }

        if (dp1[i] != -1) {
            return dp1[i];
        }

        return dp1[i] = Math.max(robMemoized(nums, i + 1), nums[i] + robMemoized(nums, i + 2));
    }

    public int robDP(int[] nums) {
        int[] dp = new int[nums.length + 2];
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }

        return dp[0];
    }

    // House Robber II
    public int robII(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        dp1 = new int[nums.length + 2];
        Arrays.fill(dp1, -1);
        int ans1 = robIIMemoized1(nums, 0);
        Arrays.fill(dp1, -1);
        int ans2 = robIIMemoized2(nums, 1);
        return Math.max(ans1, ans2);
    }

    public int robIIMemoized1(int[] nums, int i) {
        if (i >= nums.length - 1) {
            return 0;
        }

        if (dp1[i] != -1) {
            return dp1[i];
        }

        return dp1[i] = Math.max(robIIMemoized1(nums, i + 1), nums[i] + robIIMemoized1(nums, i + 2));
    }

    public int robIIMemoized2(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }

        if (dp1[i] != -1) {
            return dp1[i];
        }

        return dp1[i] = Math.max(robIIMemoized2(nums, i + 1), nums[i] + robIIMemoized2(nums, i + 2));
    }

    public int robIIDP(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length + 2];
        for (int i = nums.length - 1; i >= 1; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        int ans1 = dp[1];
        Arrays.fill(dp, 0);
        for (int i = nums.length - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        int ans2 = dp[0];
        return Math.max(ans1, ans2);
    }

    // 0 - 1 Knapsack Problem
    public int knapsack(int W, int val[], int wt[]) {
        dp2 = new int[val.length + 1][W + 1];
        fill(dp2, -1);
        return knapsackMemoized(W, val, wt, 0);
    }

    public int knapsackMemoized(int W, int val[], int wt[], int i) {
        if (i >= val.length) {
            return 0;
        }
        if (W == 0) {
            return 0;
        }

        if (dp2[i][W] != -1) {
            return dp2[i][W];
        }

        if (W >= wt[i]) {
            return dp2[i][W] = Math.max(val[i] + knapsackMemoized(W - wt[i], val, wt, i + 1), knapsackMemoized(W, val, wt, i + 1));
        } else {
            return dp2[i][W] = knapsackMemoized(W, val, wt, i + 1);
        }
    }

    public int knapsackDP(int W, int val[], int wt[]) {
        dp2 = new int[val.length + 1][W + 1];

        for (int i = 0; i < dp2.length; i++) {
            dp2[i][0] = 0;
        }

        for (int i = val.length - 1; i >= 0; i--) {
            for (int j = 1; j <= W; j++) {
                if (j >= wt[i]) {
                    dp2[i][j] = Math.max(dp2[i + 1][j], val[i] + dp2[i + 1][j - wt[i]]);
                } else {
                    dp2[i][j] = dp2[i + 1][j];
                }
            }
        }

        return dp2[0][W];
    }
}