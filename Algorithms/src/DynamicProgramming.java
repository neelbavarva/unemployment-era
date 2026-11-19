import java.util.*;

public class DynamicProgramming {

    // ============ Notes ============ //

    //  Step 1: dimensions = changing parameters
    //  Step 2: write the definition line, always
    //  dp[i][j] = <exactly what f(i, j) returns, in words>
    //  Step 3: base cases become pre-filled cells
    //  Step 4: the loop direction for each dimension

    //  The call moves…	So loop…
    //  f(i + 1, …) (index goes up)	    i from high to low
    //  f(i - 1, …) (index goes down)	i from low to high
    //  f(…, j - something) (shrinks)	j from low to high

    //  Step 5: the loop body is the memo’s return line

    // ===== Shared memo tables ===== //

    private int[] dpInt1;
    private int[][] dpInt2;
    private int[][][] dpInt3;

    private long[] dpLong1;
    private long[][] dpLong2;
    private long[][][] dpLong3;

    private Boolean[] dpBoolean1;
    private Boolean[][] dpBoolean2;
    private Boolean[][][] dpBoolean3;

    private boolean[] dpboolean1;
    private boolean[][] dpboolean2;
    private boolean[][][] dpboolean3;

    private static void fill(Boolean[][] arr, Boolean val) {
        for (Boolean[] row : arr) Arrays.fill(row, val);
    }

    private static void fill(Boolean[][][] arr, Boolean val) {
        for (Boolean[][] grid : arr) fill(grid, val);
    }

    private static void fill(boolean[][] arr, boolean val) {
        for (boolean[] row : arr) Arrays.fill(row, val);
    }

    private static void fill(boolean[][][] arr, boolean val) {
        for (boolean[][] grid : arr) fill(grid, val);
    }

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
        dpInt1 = new int[n + 1];
        Arrays.fill(dpInt1, -1);
        return climbStairsMemoized(n);
    }

    public int climbStairsMemoized(int n) {
        if (n == 0) {
            return dpInt1[n] = 1;
        }
        if (n < 0) {
            return 0;
        }

        if (dpInt1[n] != -1) {
            return dpInt1[n];
        }

        return dpInt1[n] = climbStairsMemoized(n - 1) + climbStairsMemoized(n - 2);
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
        dpInt1 = new int[nums.length + 1];
        Arrays.fill(dpInt1, -1);
        return robMemoized(nums, 0);
    }

    public int robMemoized(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }

        if (dpInt1[i] != -1) {
            return dpInt1[i];
        }

        return dpInt1[i] = Math.max(robMemoized(nums, i + 1), nums[i] + robMemoized(nums, i + 2));
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
        dpInt1 = new int[nums.length + 2];
        Arrays.fill(dpInt1, -1);
        int ans1 = robIIMemoized1(nums, 0);
        Arrays.fill(dpInt1, -1);
        int ans2 = robIIMemoized2(nums, 1);
        return Math.max(ans1, ans2);
    }

    public int robIIMemoized1(int[] nums, int i) {
        if (i >= nums.length - 1) {
            return 0;
        }

        if (dpInt1[i] != -1) {
            return dpInt1[i];
        }

        return dpInt1[i] = Math.max(robIIMemoized1(nums, i + 1), nums[i] + robIIMemoized1(nums, i + 2));
    }

    public int robIIMemoized2(int[] nums, int i) {
        if (i >= nums.length) {
            return 0;
        }

        if (dpInt1[i] != -1) {
            return dpInt1[i];
        }

        return dpInt1[i] = Math.max(robIIMemoized2(nums, i + 1), nums[i] + robIIMemoized2(nums, i + 2));
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
        dpInt2 = new int[val.length + 1][W + 1];
        fill(dpInt2, -1);
        return knapsackMemoized(W, val, wt, 0);
    }

    public int knapsackMemoized(int W, int val[], int wt[], int i) {
        if (i >= val.length) {
            return 0;
        }
        if (W == 0) {
            return 0;
        }

        if (dpInt2[i][W] != -1) {
            return dpInt2[i][W];
        }

        if (W >= wt[i]) {
            return dpInt2[i][W] = Math.max(val[i] + knapsackMemoized(W - wt[i], val, wt, i + 1), knapsackMemoized(W, val, wt, i + 1));
        } else {
            return dpInt2[i][W] = knapsackMemoized(W, val, wt, i + 1);
        }
    }

    public int knapsackDP(int W, int val[], int wt[]) {
        dpInt2 = new int[val.length + 1][W + 1];

        for (int i = 0; i < dpInt2.length; i++) {
            dpInt2[i][0] = 0;
        }

        for (int i = val.length - 1; i >= 0; i--) {
            for (int j = 1; j <= W; j++) {
                if (j >= wt[i]) {
                    dpInt2[i][j] = Math.max(dpInt2[i + 1][j], val[i] + dpInt2[i + 1][j - wt[i]]);
                } else {
                    dpInt2[i][j] = dpInt2[i + 1][j];
                }
            }
        }

        return dpInt2[0][W];
    }

    // Subset Sum
    public boolean isSubsetSum(int arr[], int sum) {
        dpBoolean2 = new Boolean[arr.length + 1][sum + 1];
        return isSubsetSumMemoized(arr, sum, 0);
    }

    public boolean isSubsetSumMemoized(int arr[], int sum, int i) {
        if (sum == 0) {
            return true;
        }

        if (i == arr.length || sum < 0) {
            return false;
        }

        if (dpBoolean2[i][sum] != null) {
            return dpBoolean2[i][sum];
        }

        if (arr[i] <= sum) {
            return dpBoolean2[i][sum] = isSubsetSumMemoized(arr, sum - arr[i], i + 1) || isSubsetSumMemoized(arr, sum, i + 1);
        } else {
            return dpBoolean2[i][sum] = isSubsetSumMemoized(arr, sum, i + 1);
        }
    }

    public boolean isSubsetSumDP(int arr[], int sum) {
        dpboolean2 = new boolean[arr.length + 1][sum + 1];
        for (int i = 0; i < dpboolean2.length; i++) {
            for (int j = 0; j < dpboolean2[0].length; j++) {
                dpboolean2[0][j] = false;
                dpboolean2[i][0] = true;
            }
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 1; j <= sum; j++) {
                if (j >= arr[i]) {
                    dpboolean2[i][j] = dpboolean2[i + 1][j] || dpboolean2[i + 1][j - arr[i]];
                } else {
                    dpboolean2[i][j] = dpboolean2[i + 1][j];
                }
            }
        }

        return dpboolean2[0][sum];
    }

    // Partition Equal Subset Sum
    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }

        if (total % 2 == 1) {
            return false;
        }

        return isSubsetSum(nums, total / 2);
    }

    // Count of Subsets with Given Sum
    public int perfectSum(int[] arr, int target) {
        dpInt2 = new int[arr.length + 1][target + 1];
        fill(dpInt2, -1);
        return perfectSumMemoized(arr, target, 0);
    }

    public int perfectSumMemoized(int[] arr, int target, int i) {
        if (target == 0 && i == arr.length) {
            return 1;
        }

        if (i >= arr.length) {
            return 0;
        }

        if (dpInt2[i][target] != -1) {
            return dpInt2[i][target];
        }

        if (target >= arr[i]) {
            return dpInt2[i][target] = perfectSumMemoized(arr, target - arr[i], i + 1) + perfectSumMemoized(arr, target, i + 1);
        } else {
            return dpInt2[i][target] = perfectSumMemoized(arr, target, i + 1);
        }
    }

    public int perfectSumDP(int[] arr, int target) {
        dpInt2 = new int[arr.length + 1][target + 1];
        dpInt2[arr.length][0] = 1;

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < dpInt2[0].length; j++) {
                if (j >= arr[i]) {
                    dpInt2[i][j] = dpInt2[i + 1][j] + dpInt2[i + 1][j - arr[i]];
                } else {
                    dpInt2[i][j] = dpInt2[i + 1][j];
                }
            }
        }

        return dpInt2[0][target];
    }

    // Minimum Subset Sum Difference
    public int minDifference(int arr[]) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        dpInt2 = new int[arr.length + 1][total + 1];
        fill(dpInt2, -1);
        return minDifferenceMemoized(arr, 0, total, 0);
    }

    public int minDifferenceMemoized(int arr[], int taken, int total, int i) {
        if (i == arr.length) {
            return Math.abs(2 * taken - total);
        }

        if (dpInt2[i][taken] != -1) {
            return dpInt2[i][taken];
        }

        return dpInt2[i][taken] = Math.min(minDifferenceMemoized(arr, taken, total, i + 1), minDifferenceMemoized(arr, taken + arr[i], total, i + 1));
    }

    public int minDifferenceDP(int arr[]) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }

        dpboolean2 = new boolean[arr.length + 1][total + 1];
        for (int i = 0; i < dpboolean2.length; i++) {
            for (int j = 0; j < dpboolean2[0].length; j++) {
                dpboolean2[0][j] = false;
                dpboolean2[i][0] = true;
            }
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 1; j <= total; j++) {
                if (j >= arr[i]) {
                    dpboolean2[i][j] = dpboolean2[i + 1][j] || dpboolean2[i + 1][j - arr[i]];
                } else {
                    dpboolean2[i][j] = dpboolean2[i + 1][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= total; i++) {
            if (dpboolean2[0][i]) {
                min = Math.min(min, Math.abs(total - 2 * i));
            }
        }
        return min;
    }

    // Target Sum
    public int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        return ((target + total) % 2 == 0 && Math.abs(target) <= total) ? perfectSumDP(nums, (total + target) / 2) : 0;
    }

    // Unbounded Knapsack
    public int knapSack(int val[], int wt[], int capacity) {
        dpInt2 = new int[val.length + 1][capacity + 1];
        fill(dpInt2, -1);
        return knapSackMemoized(val, wt, capacity, 0);
    }

    public int knapSackMemoized(int val[], int wt[], int capacity, int i) {
        if (i == val.length) {
            return 0;
        }

        if (dpInt2[i][capacity] != -1) {
            return dpInt2[i][capacity];
        }

        if (capacity >= wt[i]) {
            return dpInt2[i][capacity] = Math.max(knapSackMemoized(val, wt, capacity, i + 1), val[i] + knapSackMemoized(val, wt, capacity - wt[i], i));
        } else {
            return dpInt2[i][capacity] = knapSackMemoized(val, wt, capacity, i + 1);
        }
    }

    public int knapSackDP(int val[], int wt[], int capacity) {
        dpInt2 = new int[val.length + 1][capacity + 1];

        for (int i = val.length - 1; i >= 0; i--) {
            for (int j = 0; j <= capacity; j++) {
                if (j >= wt[i]) {
                    dpInt2[i][j] = Math.max(val[i] + dpInt2[i][j - wt[i]], dpInt2[i + 1][j]);
                } else {
                    dpInt2[i][j] = dpInt2[i + 1][j];
                }
            }
        }

        return dpInt2[0][capacity];
    }

    // Coin Change
    public int coinChange(int[] coins, int amount) {
        dpInt2 = new int[coins.length + 1][amount + 1];
        fill(dpInt2, -1);
        int ans = coinChangeMemoized(coins, amount, 0);
        return ans >= Integer.MAX_VALUE / 2 ? -1 : ans;
    }

    public int coinChangeMemoized(int[] coins, int amount, int i) {
        if (i == coins.length) {
            if (amount == 0) {
                return 0;
            } else {
                return Integer.MAX_VALUE / 2;
            }
        }

        if (dpInt2[i][amount] != -1) {
            return dpInt2[i][amount];
        }

        if (amount >= coins[i]) {
            return dpInt2[i][amount] = Math.min(coinChangeMemoized(coins, amount, i + 1), 1 + coinChangeMemoized(coins, amount - coins[i], i));
        } else {
            return dpInt2[i][amount] = coinChangeMemoized(coins, amount, i + 1);
        }
    }

    public int coinChangeDP(int[] coins, int amount) {
        dpInt2 = new int[coins.length + 1][amount + 1];

        for (int i = 0; i < dpInt2.length; i++) {
            for (int j = 0; j < dpInt2[0].length; j++) {
                dpInt2[coins.length][j] = Integer.MAX_VALUE / 2;
            }
        }

        dpInt2[coins.length][0] = 0;

        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    dpInt2[i][j] = Math.min(1 + dpInt2[i][j - coins[i]], dpInt2[i + 1][j]);
                } else {
                    dpInt2[i][j] = dpInt2[i + 1][j];
                }
            }
        }

        return dpInt2[0][amount] >= Integer.MAX_VALUE / 2 ? -1 : dpInt2[0][amount];
    }

    // Coin Change II
    public int coinChangeII(int amount, int[] coins) {
        dpInt2 = new int[coins.length + 1][amount + 1];
        fill(dpInt2, -1);
        return coinChangeIIMemoized(amount, coins, 0);
    }

    public int coinChangeIIMemoized(int amount, int[] coins, int i) {
        if (i == coins.length) {
            if (amount == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dpInt2[i][amount] != -1) {
            return dpInt2[i][amount];
        }

        if (amount >= coins[i]) {
            return dpInt2[i][amount] = coinChangeIIMemoized(amount, coins, i + 1) + coinChangeIIMemoized(amount - coins[i], coins, i);
        } else {
            return dpInt2[i][amount] = coinChangeIIMemoized(amount, coins, i + 1);
        }
    }

    public int coinChangeIIDP(int amount, int[] coins) {
        dpInt2 = new int[coins.length + 1][amount + 1];
        dpInt2[coins.length][0] = 1;

        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i]) {
                    dpInt2[i][j] = dpInt2[i][j - coins[i]] + dpInt2[i + 1][j];
                } else {
                    dpInt2[i][j] = dpInt2[i + 1][j];
                }
            }
        }

        return dpInt2[0][amount];
    }

    // Longest Common Subsequence
    public int longestCommonSubsequence(String text1, String text2) {
        dpInt2 = new int[text1.length() + 1][text2.length() + 1];
        fill(dpInt2, -1);
        return longestCommonSubsequenceMemoized(text1, text2, 0, 0);
    }

    public int longestCommonSubsequenceMemoized(String text1, String text2, int i, int j) {
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }

        if (dpInt2[i][j] != -1) {
            return dpInt2[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            return dpInt2[i][j] = 1 + longestCommonSubsequenceMemoized(text1, text2, i + 1, j + 1);
        } else {
            return dpInt2[i][j] = Math.max(longestCommonSubsequenceMemoized(text1, text2, i + 1, j), longestCommonSubsequenceMemoized(text1, text2, i, j + 1));
        }
    }

    public int longestCommonSubsequenceDP(String text1, String text2) {
        dpInt2 = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                if (i == text1.length() || j == text2.length()) {
                    dpInt2[i][j] = 0;
                }
            }
        }

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dpInt2[i][j] = 1 + dpInt2[i + 1][j + 1];
                } else {
                    dpInt2[i][j] = Math.max(dpInt2[i + 1][j], dpInt2[i][j + 1]);
                }
            }
        }

        return dpInt2[0][0];
    }

    // Longest Common Substring
    public int longCommSubstr(String s1, String s2) {
        int max = 0;
        dpInt2 = new int[s1.length() + 1][s2.length() + 1];
        fill(dpInt2, -1);
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                max = Math.max(max, longCommSubstrMemoized(s1, s2, i, j));
            }
        }
        return max;
    }

    public int longCommSubstrMemoized(String s1, String s2, int i, int j) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }

        if (dpInt2[i][j] != -1) {
            return dpInt2[i][j];
        }

        if (s1.charAt(i) != s2.charAt(j)) {
            return dpInt2[i][j] = 0;
        }
        return dpInt2[i][j] = 1 + longCommSubstrMemoized(s1, s2, i + 1, j + 1);
    }

    public int longCommSubstrDP(String s1, String s2) {
        int max = 0;
        dpInt2 = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dpInt2[i][j] = 1 + dpInt2[i + 1][j + 1];
                } else {
                    dpInt2[i][j] = 0;
                }
            }
        }

        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                max = Math.max(max, dpInt2[i][j]);
            }
        }

        return max;
    }

    // Print Longest Common Substring
    public String printLongestCommonSubstring(String text1, String text2) {
        dpInt2 = new int[text1.length() + 1][text2.length() + 1];

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dpInt2[i][j] = 1 + dpInt2[i + 1][j + 1];
                } else {
                    dpInt2[i][j] = 0;
                }
            }
        }

        int max = 0;
        int index = 0;
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (dpInt2[i][j] > max) {
                    max = dpInt2[i][j];
                    index = i;
                }
            }
        }

        return text1.substring(index, index + max);
    }

    // Print All Longest Common Substrings
    public ArrayList<String> printAllLongestCommonSubstring(String text1, String text2) {
        ArrayList<String> arrayList = new ArrayList<>();
        Set<String> stringSet = new HashSet<>();

        dpInt2 = new int[text1.length() + 1][text2.length() + 1];

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dpInt2[i][j] = 1 + dpInt2[i + 1][j + 1];
                } else {
                    dpInt2[i][j] = 0;
                }
            }
        }

        int max = 0;
        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                max = Math.max(max, dpInt2[i][j]);
            }
        }

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (dpInt2[i][j] == max && max != 0) {
                    if (!stringSet.contains(text1.substring(i, i + max))) {
                        arrayList.add(text1.substring(i, i + max));
                        stringSet.add(text1.substring(i, i + max));
                    }
                }
            }
        }

        return arrayList;
    }

    // Print Longest Common Subsequence
    public String printLongestCommonSubsequence(String text1, String text2) {
        dpInt2 = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                if (i == text1.length() || j == text2.length()) {
                    dpInt2[i][j] = 0;
                }
            }
        }

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dpInt2[i][j] = 1 + dpInt2[i + 1][j + 1];
                } else {
                    dpInt2[i][j] = Math.max(dpInt2[i + 1][j], dpInt2[i][j + 1]);
                }
            }
        }

        String ans = "";
        int i = 0;
        int j = 0;
        while (i < text1.length() && j < text2.length()) {
            if (text1.charAt(i) == text2.charAt(j)) { // move diagonally
                ans += text1.charAt(i);
                i++;
                j++;
            } else if (dpInt2[i + 1][j] >= dpInt2[i][j + 1]) { // down holds the bigger answer
                i++;
            } else { // right holds the bigger answer
                j++;
            }
        }

        return ans;
    }

    // Print All Longest Common Subsequences
    public ArrayList<String> printAllLongestCommonSubsequence(String text1, String text2) {
        ArrayList<String> arrayList = new ArrayList<>();
        // DP + Backtracking
        return arrayList;
    }

    // Print Shortest Common Supersequence
    public String shortestCommonSupersequence(String text1, String text2) {
        dpInt2 = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                if (i == text1.length() || j == text2.length()) {
                    dpInt2[i][j] = 0;
                }
            }
        }

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dpInt2[i][j] = 1 + dpInt2[i + 1][j + 1];
                } else {
                    dpInt2[i][j] = Math.max(dpInt2[i + 1][j], dpInt2[i][j + 1]);
                }
            }
        }

        int i = 0;
        int j = 0;
        String ans = "";
        while (i < text1.length() && j < text2.length()) {
            if (text1.charAt(i) == text2.charAt(j)) { // move diagonally
                ans += text1.charAt(i);
                i++;
                j++;
            } else if (dpInt2[i + 1][j] >= dpInt2[i][j + 1]) { // down holds the bigger answer
                ans += text1.charAt(i);
                i++;
            } else { // right holds the bigger answer
                ans += text2.charAt(j);
                j++;
            }
        }

        while (i < text1.length()) {
            ans += text1.charAt(i++);
        }

        while (j < text2.length()) {
            ans += text2.charAt(j++);
        }

        return ans;
    }

    // Interleaving String
    public boolean isInterleave(String s1, String s2, String s3) {
        dpBoolean2 = new Boolean[s1.length() + 1][s2.length() + 1];
        return (s1.length() + s2.length() == s3.length()) && isInterleaveMemoized(s1, s2, s3, 0, 0);
    }

    public boolean isInterleaveMemoized(String s1, String s2, String s3, int i, int j) {
        if (i >= s1.length() && j >= s2.length()) {
            return true;
        }

        if (dpBoolean2[i][j] != null) {
            return dpBoolean2[i][j];
        }

        boolean A = i < s1.length() && s1.charAt(i) == s3.charAt(i + j) && isInterleaveMemoized(s1, s2, s3, i + 1, j);
        boolean B = j < s2.length() && s2.charAt(j) == s3.charAt(i + j) && isInterleaveMemoized(s1, s2, s3, i, j + 1);

        return dpBoolean2[i][j] = A || B;
    }

    // TODO
    public boolean isInterleaveDP(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();
        if (n + m != s3.length()) return false;

        // dp[i][j] = can s1[i..] and s2[j..] interleave to form s3[i+j..]
        dpboolean2 = new boolean[n + 1][m + 1];
        dpboolean2[n][m] = true;                       // both used up = success

        for (int i = n; i >= 0; i--) {                 // start AT n, not n-1
            for (int j = m; j >= 0; j--) {             // start AT m, not m-1
                if (i == n && j == m) continue;        // base cell, already set

                boolean A = i < n && s1.charAt(i) == s3.charAt(i + j) && dpboolean2[i + 1][j];
                boolean B = j < m && s2.charAt(j) == s3.charAt(i + j) && dpboolean2[i][j + 1];
                dpboolean2[i][j] = A || B;
            }
        }
        return dpboolean2[0][0];
    }

    // Minimum Insertions/Deletions to Convert String a to b
    public int minDistance(String word1, String word2) {
        int lcs = longestCommonSubsequenceDP(word1, word2);
        return Math.abs(word1.length() + word2.length() - (2 * lcs));
    }

    // Longest Palindromic Subsequence
    public int longestPalindromeSubseq(String s) {
        return longestCommonSubsequenceDP(s, new StringBuilder(s).reverse().toString());
    }

    // Minimum Deletions to Make a String Palindrome
    public int minInsertions(String s) {
        return s.length() - longestCommonSubsequenceDP(s, new StringBuilder(s).reverse().toString());
    }

    // Longest Repeating Subsequence
    public int longestRepSubseq(String s) {
        String text1 = s;
        String text2 = s;

        dpInt2 = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                if (i == text1.length() || j == text2.length()) {
                    dpInt2[i][j] = 0;
                }
            }
        }

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j) && i != j) {
                    dpInt2[i][j] = 1 + dpInt2[i + 1][j + 1];
                } else {
                    dpInt2[i][j] = Math.max(dpInt2[i + 1][j], dpInt2[i][j + 1]);
                }
            }
        }

        return dpInt2[0][0];
    }

    // Shortest Common Supersequence
    public int minSuperSeq(String s1, String s2) {
        return (s1.length() + s2.length() - longestCommonSubsequenceDP(s1, s2));
    }

    // Edit Distance
    public int editDistance(String word1, String word2) {
        dpInt2 = new int[word1.length() + 1][word2.length() + 1];
        fill(dpInt2, -1);
        return editDistanceMemoized(word1, word2, 0, 0);
    }

    public int editDistanceMemoized(String word1, String word2, int i, int j) {
        if (i == word1.length()) {
            return Math.abs(word2.length() - j);
        }

        if (j == word2.length()) {
            return Math.abs(word1.length() - i);
        }

        if (dpInt2[i][j] != -1) {
            return dpInt2[i][j];
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            return dpInt2[i][j] = editDistanceMemoized(word1, word2, i + 1, j + 1);
        } else {
            return dpInt2[i][j] = 1 + Math.min(editDistanceMemoized(word1, word2, i + 1, j), Math.min(editDistanceMemoized(word1, word2, i, j + 1), editDistanceMemoized(word1, word2, i + 1, j + 1)));

        }
    }

    public int editDistanceDP(String word1, String word2) {
        dpInt2 = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == word1.length()) {
                    dpInt2[i][j] = Math.abs(word2.length() - j);
                }

                if (j == word2.length()) {
                    dpInt2[i][j] = Math.abs(word1.length() - i);
                }
            }
        }

        for (int i = word1.length() - 1; i >= 0; i--) {
            for (int j = word2.length() - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dpInt2[i][j] = dpInt2[i + 1][j + 1];
                } else {
                    dpInt2[i][j] = 1 + Math.min(dpInt2[i + 1][j], Math.min(dpInt2[i][j + 1], dpInt2[i + 1][j + 1]));

                }
            }
        }

        return dpInt2[0][0];
    }

}