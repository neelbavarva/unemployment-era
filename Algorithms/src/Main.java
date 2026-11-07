import java.util.*;

public class Main {

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

    public static void main(String args[]){
        System.out.println("Hi");
    }
}
