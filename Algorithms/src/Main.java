import java.io.*;
import java.util.*;

public class Main {

    // ===== Common Methods & Classes ===== //

    public class Pair<K, V> {
        K first;
        V second;

        Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    // ===== Binary Search ===== //

    public int binarySearch(int arr[], int target){
        int left = 0;
        int right = arr.length-1;

        while(left<=right){
            int mid = left + (right-left)/2;
            if(arr[mid]==target){
                return mid;
            } else if(arr[mid]<target){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        return -1;
    }

    public int[] getFirstAndLastOccurence(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int firstOcc = Integer.MAX_VALUE;
        int lastOcc = -1;

        // First occurence
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]==target){
                right = mid-1;
                firstOcc = Math.min(firstOcc, mid);
            } else if(nums[mid]<target){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        left = 0;
        right = nums.length-1;
        // Last occurence
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]==target){
                left = mid+1;
                lastOcc = Math.max(lastOcc, mid);
            } else if(nums[mid]<target){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        if(firstOcc==Integer.MAX_VALUE){
            firstOcc = -1;
        }
        return new int[]{firstOcc, lastOcc};
    }

    public int findKRotation(int arr[]) {
        if(arr.length==1){
            return 0;
        }
        int left = 0;
        int right = arr.length - 1;
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left)/2;
            // Lesser than both it's left & right
            if (
                    (mid - 1 >= 0 && mid + 1 <= arr.length - 1 && arr[mid]<arr[mid - 1] && arr[mid]<arr[mid + 1]) ||
                    (mid == arr.length - 1 && arr[mid]<arr[mid - 1] && arr[mid]<arr[0])
            ) {
                ans = mid;
                break;
            } else if (arr[0]>arr[arr.length - 1] && arr[mid]< arr[0]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return ans;
    }
    
    public int findKRotationSimplified(int arr[]) {
        int left = 0;
        int right = arr.length - 1;

        while(left<right){
            int mid = left + (right - left)/2;
            if(arr[mid]>arr[right]){
                left = mid+1;
            } else {
                right = mid; // because of the EXACT pivot
            }
        }

        return left;
    }

    public int findPeakElement(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left)/2;
            // Greater than both it's left & right
            if ((mid - 1 >= 0 && mid + 1 <= nums.length - 1 && nums[mid]>nums[mid - 1] && nums[mid]>nums[mid + 1]) || (mid==nums.length-1 && nums[mid]>nums[mid-1]) || (mid==0 && nums[mid]>nums[mid+1])){
                ans = mid;
                break;
            } else if (nums[mid]<nums[mid+1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return ans;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int width = matrix[0].length;
        int height = matrix.length;

        int left = 0;
        int right = width*height-1;
        int length = width*height;

        while(left<=right){
            int mid = left + (right-left)/2;
            int i = mid / width;
            int j = mid % width;

            if(matrix[i][j]==target){
                return true;
            } else if(matrix[i][j]>target){
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return false;
    }

    public int findPages(int[] arr, int k) {
        if (k > arr.length) {
            return - 1;
        }

        long left = 0;
        long right = 0;

        for (int i = 0; i < arr.length; i++) {
            left = Math.max(left, arr[i]);
            right += arr[i];
        }

        long ans = -1;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (isAllocatedPagesValid(arr, mid, k)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return (int) ans;
    }

    private boolean isAllocatedPagesValid(int[] arr, long maxPages, int k) {
        int students = 1;
        long counter = 0;

        for (int i = 0; i < arr.length; i++) {
            counter += arr[i];
            if (counter > maxPages) {
                counter = arr[i];
                students++;
            }
        }

        return students <= k;
    }

    // ===== HashMap ===== //

    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[]{-1,-1};

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                res[0] = map.get(target-nums[i]);
                res[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }

        return res;
    }

    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(map1.containsKey(s.charAt(i))){
                map1.put(s.charAt(i), map1.get(s.charAt(i))+1);
            } else {
                map1.put(s.charAt(i), 1);
            }
        }

        Map<Character, Integer> map2 = new HashMap<>();
        for(int i=0;i<t.length();i++){
            if(map2.containsKey(t.charAt(i))){
                map2.put(t.charAt(i), map2.get(t.charAt(i))+1);
            } else {
                map2.put(t.charAt(i), 1);
            }
        }

        if(!map1.equals(map2)){
            return false;
        }

        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        return isIsomorphicCheck(s,t) && isIsomorphicCheck(t,s);
    }

    private boolean isIsomorphicCheck(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), t.charAt(i));
            } else {
                if(!(map.get(s.charAt(i))==t.charAt(i))){
                    return false;
                }
            }
        }

        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<Map<Character, Integer>, List<Integer>> map = new HashMap<>();

        for(int i=0;i<strs.length;i++){
            Map<Character, Integer> charMap = getStringToMap(strs[i]);
            if(!map.containsKey(charMap)){
                map.put(charMap, new ArrayList<>());
            }
            map.get(charMap).add(i);
        }

        for (Map.Entry<Map<Character, Integer>, List<Integer>> entry : map.entrySet()) {
            List<Integer> value = entry.getValue();
            List<String> resAdder = new ArrayList<>();
            for(int i=0;i<value.size();i++){
                resAdder.add(strs[value.get(i)]);
            }
            res.add(resAdder);
        }

        return res;
    }

    private Map<Character, Integer> getStringToMap(String str){
        Map<Character, Integer> map = new HashMap<>();

        for(int i=0;i<str.length();i++){
            if(map.containsKey(str.charAt(i))){
                map.put(str.charAt(i),map.get(str.charAt(i))+1);
            } else {
                map.put(str.charAt(i),1);
            }
        }

        return map;
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        boolean visited[] = new boolean[nums.length];
        for(int i=0;i<nums.length;i++){
            map.put(nums[i], i);
        }

        int longestSequence = 0;
        for(int i=0;i<nums.length;i++){
            if(!visited[i] && !map.containsKey(nums[i]-1)){
                int currentIndex = i;
                int localMaxima = 1;
                while(!visited[currentIndex] && map.containsKey(nums[currentIndex]+1)){
                    localMaxima++;
                    visited[currentIndex] = true;
                    currentIndex = map.get(nums[currentIndex]+1);
                }
                longestSequence = Math.max(longestSequence, localMaxima);
            }
        }
        return longestSequence;
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int i=0;i<nums1.length;i++){
            for(int j=0;j<nums2.length;j++){
                if(!map.containsKey(nums1[i]+nums2[j])){
                    map.put(nums1[i]+nums2[j], 0);
                }
                map.put(nums1[i]+nums2[j], map.get(nums1[i]+nums2[j])+1);
            }
        }

        for(int i=0;i<nums3.length;i++){
            for(int j=0;j<nums4.length;j++){
                if(map.containsKey((nums3[i]+nums4[j])*(-1))){
                    res += map.get((nums3[i]+nums4[j])*(-1));
                }
            }
        }
        return res;
    }

    public int maxPoints(int[][] points) {
        int res = 0;

        for(int i=0;i<points.length;i++){
            Map<Double, Integer> map = new HashMap<>();
            for(int j=i+1;j<points.length;j++){
                double yAxis = points[j][1] - points[i][1];
                double xAxis = points[j][0] - points[i][0];
                if(xAxis==0){
                    if(!map.containsKey((double)Integer.MAX_VALUE)){
                        map.put((double) Integer.MAX_VALUE, 0);
                    }
                    map.put((double) Integer.MAX_VALUE, map.get((double)Integer.MAX_VALUE)+1);
                    res = Math.max(res, map.get((double)Integer.MAX_VALUE));
                } else {
                    double slop = yAxis/xAxis;
                    if(slop==0.0 || slop==-0.0){
                        slop=0.0; // IEEE 754 floating-point standard
                    }
                    if(!map.containsKey(slop)){
                        map.put(slop, 0);
                    }
                    map.put(slop, map.get(slop)+1);
                    res = Math.max(res, map.get(slop));
                }
            }
        }
        return res+1;
    }

    // ===== Stack ===== //

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int res[] = new int[nums1.length];
        int nextGreater[] = new int[nums2.length];

        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
//      Stack<Integer> stack = new Stack<>();
//      Stack is a legacy class, synchronised and slower
//      ArrayDeque doesn’t allow null. That’s never an issue in DSA.

        for(int i=nums2.length-1;i>=0;i--){
            if(stack.isEmpty()){
                nextGreater[i] = -1;
            } else {
                while(!stack.isEmpty() && stack.peek()<nums2[i]){
                    stack.pop();
                }
                nextGreater[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            map.put(nums2[i], nextGreater[i]);
            stack.push(nums2[i]);
        }

        for(int i=0;i<nums1.length;i++){
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public boolean isValidParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for(int i=0;i<s.length();i++){
            char curr = s.charAt(i);
            if(stack.isEmpty()){
                stack.push(curr);
            } else {
                if(curr==')'){
                    if(stack.peek()=='('){
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if(curr==']'){
                    if(stack.peek()=='['){
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (curr=='}') {
                    if(stack.peek()=='{'){
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

    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        int[] heights = new int[matrix[0].length];

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]=='1'){
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

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

    // ===== Sliding Window ===== //

    public List<Integer> firstNegInt(int arr[], int k) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        int i = 0;
        int j = k - 1;

        for (int index = 0; index<k; index++) {
            if (arr[index]<0) {
                queue.add(arr[index]);
            }
        }

        while (j<arr.length) {
            res.add(queue.isEmpty() ? 0 : queue.peek());
            if (!queue.isEmpty() && arr[i] == queue.peek()) {
                queue.poll();
            }
            i++; j++;
            if (j<arr.length && arr[j]<0) {
                queue.add(arr[j]);
            }
        }
        return res;
    }

    public int countOccurencesOfAnagrams(String pat, String txt) {
        int res = 0;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i<pat.length(); i++) {
            if (!map2.containsKey(pat.charAt(i))) {
                map2.put(pat.charAt(i), 1);
            } else {
                map2.put(pat.charAt(i), map2.get(pat.charAt(i)) + 1);
            }
        }

        for (int i = 0; i<pat.length(); i++) {
            if (!map1.containsKey(txt.charAt(i))) {
                map1.put(txt.charAt(i), 1);
            } else {
                map1.put(txt.charAt(i), map1.get(txt.charAt(i)) + 1);
            }
        }

        int i = 0;
        int j = pat.length() - 1;

        while (j<txt.length()) {
            if (map1.equals(map2)) {
                res++;
            }

            map1.put(txt.charAt(i), map1.get(txt.charAt(i)) - 1);
            if (map1.get(txt.charAt(i)) == 0) {
                map1.remove(txt.charAt(i));
            }

            i++; j++;
            if (j<txt.length()) {
                if (!map1.containsKey(txt.charAt(j))) {
                    map1.put(txt.charAt(j), 1);
                } else {
                    map1.put(txt.charAt(j), map1.get(txt.charAt(j)) + 1);
                }
            }
        }
        return res;
    }

    public int countOccurencesOfAnagramsSimplified(String pat, String txt) {
        int res = 0;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i<pat.length(); i++) {
            map2.put(pat.charAt(i), map2.getOrDefault(pat.charAt(i), 0) + 1);
        }
        for (int i = 0; i<pat.length(); i++) {
            map1.put(txt.charAt(i), map1.getOrDefault(txt.charAt(i), 0) + 1);
        }

        int i = 0;
        int j = pat.length() - 1;
        while (j<txt.length()) {
            if (map1.equals(map2)) {
                res++;
            }

            map1.put(txt.charAt(i), map1.get(txt.charAt(i)) - 1);
            if (map1.get(txt.charAt(i)) == 0) {
                map1.remove(txt.charAt(i));
            }

            i++; j++;
            if (j<txt.length()) {
                map1.put(txt.charAt(j), map1.getOrDefault(txt.charAt(j), 0) + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        out.flush();
        out.close();
    }

    static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }

        int[] nextIntArray(int n) throws IOException {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = nextInt();
            return arr;
        }

        String nextLine() throws IOException {
            st = null;
            return br.readLine();
        }
    }
}
