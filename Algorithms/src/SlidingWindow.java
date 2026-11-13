import java.util.*;
import java.util.HashMap;

public class SlidingWindow {

    // First Negative Number in Every Window of Size K
    public List<Integer> firstNegInt(int arr[], int k) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        int i = 0;
        int j = k - 1;

        for (int index = 0; index < k; index++) {
            if (arr[index] < 0) {
                queue.add(arr[index]);
            }
        }

        while (j < arr.length) {
            res.add(queue.isEmpty() ? 0 : queue.peek());
            if (!queue.isEmpty() && arr[i] == queue.peek()) {
                queue.poll();
            }
            i++;
            j++;
            if (j < arr.length && arr[j] < 0) {
                queue.add(arr[j]);
            }
        }
        return res;
    }

    // Count Occurrences of Anagrams
    public int countOccurencesOfAnagrams(String pat, String txt) {
        int res = 0;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < pat.length(); i++) {
            if (!map2.containsKey(pat.charAt(i))) {
                map2.put(pat.charAt(i), 1);
            } else {
                map2.put(pat.charAt(i), map2.get(pat.charAt(i)) + 1);
            }
        }

        for (int i = 0; i < pat.length(); i++) {
            if (!map1.containsKey(txt.charAt(i))) {
                map1.put(txt.charAt(i), 1);
            } else {
                map1.put(txt.charAt(i), map1.get(txt.charAt(i)) + 1);
            }
        }

        int i = 0;
        int j = pat.length() - 1;

        while (j < txt.length()) {
            if (map1.equals(map2)) {
                res++;
            }

            map1.put(txt.charAt(i), map1.get(txt.charAt(i)) - 1);
            if (map1.get(txt.charAt(i)) == 0) {
                map1.remove(txt.charAt(i));
            }

            i++;
            j++;
            if (j < txt.length()) {
                if (!map1.containsKey(txt.charAt(j))) {
                    map1.put(txt.charAt(j), 1);
                } else {
                    map1.put(txt.charAt(j), map1.get(txt.charAt(j)) + 1);
                }
            }
        }
        return res;
    }

    // Count Occurrences of Anagrams
    public int countOccurencesOfAnagramsSimplified(String pat, String txt) {
        int res = 0;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < pat.length(); i++) {
            map2.put(pat.charAt(i), map2.getOrDefault(pat.charAt(i), 0) + 1);
        }
        for (int i = 0; i < pat.length(); i++) {
            map1.put(txt.charAt(i), map1.getOrDefault(txt.charAt(i), 0) + 1);
        }

        int i = 0;
        int j = pat.length() - 1;
        while (j < txt.length()) {
            if (map1.equals(map2)) {
                res++;
            }

            map1.put(txt.charAt(i), map1.get(txt.charAt(i)) - 1);
            if (map1.get(txt.charAt(i)) == 0) {
                map1.remove(txt.charAt(i));
            }

            i++;
            j++;
            if (j < txt.length()) {
                map1.put(txt.charAt(j), map1.getOrDefault(txt.charAt(j), 0) + 1);
            }
        }
        return res;
    }

    // Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        int min = nums.length + 1;

        int i = 0;
        int j = 0;
        int curr = 0;

        while (j < nums.length) {
            while (curr < target && j < nums.length) {
                curr += nums[j];
                j++; // the early increment
            }

            while (curr >= target) {
                min = Math.min(min, j - i); // We got the increment earlier so no need for -1;
                curr -= nums[i];
                i++;
            }
        }
        return min == nums.length + 1 ? 0 : min;
    }

    // Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int i = 0;
        int j = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (j < s.length()) {
            if (map.containsKey(s.charAt(j))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                if (map.get(s.charAt(i)) == 0) {
                    map.remove(s.charAt(i));
                }
                i++;
            } else {
                map.put(s.charAt(j), 1);
                j++;
                max = Math.max(max, j - i);
            }
        }
        return max;
    }

    // Longest Substring with K Unique Characters
    public int longestKSubstr(String s, int k) {
        int res = -1;
        int i = 0;
        int j = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (j < s.length()) {
            while (j < s.length() && map.size() <= k) {
                map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
                j++;
                if (map.size() == k) {
                    res = Math.max(res, j - i);
                }
            }
            while (map.size() > k) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                if (map.get(s.charAt(i)) == 0) {
                    map.remove(s.charAt(i));
                }
                i++;
            }
        }
        return res;
    }

}
