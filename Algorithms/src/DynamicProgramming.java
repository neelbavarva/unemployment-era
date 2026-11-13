import java.util.Arrays;

public class DynamicProgramming {

    public int globalDP[];

    // Climbing Stairs
    public int climbStairs(int n) {
        globalDP = new int[n + 1];
        Arrays.fill(globalDP, -1);
        return climbStairsMemoized(n);
    }

    public int climbStairsMemoized(int n) {
        if (n == 0) {
            return globalDP[n] = 1;
        }
        if (n < 0) {
            return 0;
        }

        if (globalDP[n] != -1) {
            return globalDP[n];
        }

        return globalDP[n] = climbStairsMemoized(n - 1) + climbStairsMemoized(n - 2);
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

}
