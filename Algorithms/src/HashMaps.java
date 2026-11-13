import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HashMaps {

    // Two Sum
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[]{-1, -1};

        Map<Integer, Integer> map = new java.util.HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }

        return res;
    }

    // Valid Anagram
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map1 = new java.util.HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map1.containsKey(s.charAt(i))) {
                map1.put(s.charAt(i), map1.get(s.charAt(i)) + 1);
            } else {
                map1.put(s.charAt(i), 1);
            }
        }

        Map<Character, Integer> map2 = new java.util.HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (map2.containsKey(t.charAt(i))) {
                map2.put(t.charAt(i), map2.get(t.charAt(i)) + 1);
            } else {
                map2.put(t.charAt(i), 1);
            }
        }

        if (!map1.equals(map2)) {
            return false;
        }

        return true;
    }

    // Isomorphic Strings
    public boolean isIsomorphic(String s, String t) {
        return isIsomorphicCheck(s, t) && isIsomorphicCheck(t, s);
    }

    private boolean isIsomorphicCheck(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> map = new java.util.HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), t.charAt(i));
            } else {
                if (!(map.get(s.charAt(i)) == t.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    // Group Anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<Map<Character, Integer>, List<Integer>> map = new java.util.HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            Map<Character, Integer> charMap = getStringToMap(strs[i]);
            if (!map.containsKey(charMap)) {
                map.put(charMap, new ArrayList<>());
            }
            map.get(charMap).add(i);
        }

        for (Map.Entry<Map<Character, Integer>, List<Integer>> entry : map.entrySet()) {
            List<Integer> value = entry.getValue();
            List<String> resAdder = new ArrayList<>();
            for (int i = 0; i < value.size(); i++) {
                resAdder.add(strs[value.get(i)]);
            }
            res.add(resAdder);
        }

        return res;
    }

    private Map<Character, Integer> getStringToMap(String str) {
        Map<Character, Integer> map = new java.util.HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            if (map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            } else {
                map.put(str.charAt(i), 1);
            }
        }

        return map;
    }

    // Longest Consecutive Sequence
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new java.util.HashMap<>();
        boolean visited[] = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        int longestSequence = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i] && !map.containsKey(nums[i] - 1)) {
                int currentIndex = i;
                int localMaxima = 1;
                while (!visited[currentIndex] && map.containsKey(nums[currentIndex] + 1)) {
                    localMaxima++;
                    visited[currentIndex] = true;
                    currentIndex = map.get(nums[currentIndex] + 1);
                }
                longestSequence = Math.max(longestSequence, localMaxima);
            }
        }
        return longestSequence;
    }

    // 4Sum II
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new java.util.HashMap<>();
        int res = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (!map.containsKey(nums1[i] + nums2[j])) {
                    map.put(nums1[i] + nums2[j], 0);
                }
                map.put(nums1[i] + nums2[j], map.get(nums1[i] + nums2[j]) + 1);
            }
        }

        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                if (map.containsKey((nums3[i] + nums4[j]) * (-1))) {
                    res += map.get((nums3[i] + nums4[j]) * (-1));
                }
            }
        }
        return res;
    }

    // Max Points on a Line
    public int maxPoints(int[][] points) {
        int res = 0;

        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> map = new java.util.HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                double yAxis = points[j][1] - points[i][1];
                double xAxis = points[j][0] - points[i][0];
                if (xAxis == 0) {
                    if (!map.containsKey((double) Integer.MAX_VALUE)) {
                        map.put((double) Integer.MAX_VALUE, 0);
                    }
                    map.put((double) Integer.MAX_VALUE, map.get((double) Integer.MAX_VALUE) + 1);
                    res = Math.max(res, map.get((double) Integer.MAX_VALUE));
                } else {
                    double slop = yAxis / xAxis;
                    if (slop == 0.0 || slop == -0.0) {
                        slop = 0.0; // IEEE 754 floating-point standard
                    }
                    if (!map.containsKey(slop)) {
                        map.put(slop, 0);
                    }
                    map.put(slop, map.get(slop) + 1);
                    res = Math.max(res, map.get(slop));
                }
            }
        }
        return res + 1;
    }

}
