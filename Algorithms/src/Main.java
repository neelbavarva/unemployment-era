import java.io.*;
import java.util.*;

public class Main {

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
