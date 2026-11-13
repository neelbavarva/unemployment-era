import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Stacks {

    // Next Greater Element
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int res[] = new int[nums1.length];
        int nextGreater[] = new int[nums2.length];

        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
//      Stack<Integer> stack = new Stack<>();
//      Stack is a legacy class, synchronised and slower
//      ArrayDeque doesn’t allow null. That’s never an issue in DSA.

        for (int i = nums2.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                nextGreater[i] = -1;
            } else {
                while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                    stack.pop();
                }
                nextGreater[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            map.put(nums2[i], nextGreater[i]);
            stack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    // Valid Parentheses
    public boolean isValidParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(curr);
            } else {
                if (curr == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (curr == ']') {
                    if (stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (curr == '}') {
                    if (stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    stack.push(curr);
                }
            }
        }

        return stack.isEmpty();
    }

    // Online Stock Span
    class StockSpanner {

        static class Pair<K, V> {
            K first;
            V second;

            Pair(K first, V second) {
                this.first = first;
                this.second = second;
            }
        }

        Deque<Pair<Integer, Integer>> stack;
        int count;

        public StockSpanner() {
            stack = new ArrayDeque<>();
            count = 0;
        }

        public int next(int price) {
            count++;
            while (!stack.isEmpty() && stack.peek().first <= price) {
                stack.pop();
            }

            int curr = 0;
            if (!stack.isEmpty()) {
                curr = stack.peek().second;
            }

            stack.push(new Pair(price, count));
            return count - curr;
        }
    }

    // Next Greater Element II
    public int[] nextGreaterElements(int[] nums) {
        int res[] = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            stack.push(nums[i]);
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    // Asteroid Collision
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] < 0) {
                boolean destroyed = false;
                while (!stack.isEmpty() && stack.peek() > 0) {
                    if (Math.abs(asteroids[i]) > stack.peek()) {
                        stack.pop();
                    } else if (Math.abs(asteroids[i]) == stack.peek()) {
                        stack.pop();
                        destroyed = true;
                        break;
                    } else {
                        destroyed = true;
                        break;
                    }
                }
                if (!destroyed) {
                    stack.push(asteroids[i]);
                }
            } else {
                stack.push(asteroids[i]);
            }
        }

        int res[] = new int[stack.size()];
        int index = stack.size() - 1;
        while (!stack.isEmpty()) {
            res[index--] = stack.pop();
        }
        return res;
    }

    // Largest Rectangle in Histogram
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        int[] leftmax = new int[heights.length];
        int[] rightmax = new int[heights.length];

        Deque<Integer> stack = new ArrayDeque<>();

        // left max
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            leftmax[i] = stack.isEmpty() ? i : i - stack.peek() - 1;
            stack.push(i);
        }
        stack.clear();

        // right max
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            rightmax[i] = stack.isEmpty() ? heights.length - 1 - i : stack.peek() - i - 1;
            stack.push(i);
        }

        for (int i = 0; i < leftmax.length; i++) {
            res = Math.max(res, (rightmax[i] + leftmax[i] + 1) * heights[i]);
        }
        return res;
    }

    // Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        int[] heights = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    // Trapping Rain Water
    public int trappingRainWater(int[] heights) {
        int total = 0;
        int[] leftmax = new int[heights.length];
        int[] rightmax = new int[heights.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            leftmax[i] = stack.isEmpty() ? -1 : stack.peek();
            if (stack.isEmpty()) {
                stack.push(i);
            }
        }
        stack.clear();

        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            rightmax[i] = stack.isEmpty() ? -1 : stack.peek();
            if (stack.isEmpty()) {
                stack.push(i);
            }
        }

        for (int i = 0; i < rightmax.length; i++) {
            if (leftmax[i] != -1 && rightmax[i] != -1) {
                total += (Math.min(heights[leftmax[i]], heights[rightmax[i]]) - heights[i]);
            }
        }
        return total;
    }

    // Trapping Rain Water
    public int trappingRainWaterWithoutStack(int[] heights) {
        int total = 0;
        int[] leftmax = new int[heights.length];
        int[] rightmax = new int[heights.length];

        leftmax[0] = heights[0];
        for (int i = 1; i < heights.length; i++) {
            leftmax[i] = Math.max(leftmax[i - 1], heights[i]);
        }

        rightmax[heights.length - 1] = heights[heights.length - 1];
        for (int i = heights.length - 2; i >= 0; i--) {
            rightmax[i] = Math.max(rightmax[i + 1], heights[i]);
        }

        for (int i = 0; i < rightmax.length; i++) {
            if (leftmax[i] != -1 && rightmax[i] != -1) {
                total += Math.min(leftmax[i], rightmax[i]) - heights[i];
            }
        }
        return total;
    }

    // Remove K Digits
    public String removeKdigits(String num, int k) {
        String res = "";
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < num.length(); i++) {
            while (!stack.isEmpty() && Integer.parseInt(num.charAt(i) + "") < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(Integer.parseInt(num.charAt(i) + ""));
        }

        while (k-- > 0) {
            stack.pop();
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        res = new StringBuilder(res).reverse().toString();
        res = res.replaceFirst("^0+", "");
        return res.isEmpty() ? "0" : res;
    }

    // Decode String

    // Sum of Subarray Minimums

}
