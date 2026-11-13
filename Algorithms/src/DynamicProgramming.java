import java.util.Arrays;

public class DynamicProgramming {

    // ===== Shared memo tables =====

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
}