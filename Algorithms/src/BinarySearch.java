public class BinarySearch {

    // Binary Search
    public int binarySearch(int arr[], int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // First and Last Position of Element in Sorted Array
    public int[] getFirstAndLastOccurence(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int firstOcc = Integer.MAX_VALUE;
        int lastOcc = -1;

        // First occurence
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
                firstOcc = Math.min(firstOcc, mid);
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        left = 0;
        right = nums.length - 1;
        // Last occurence
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
                lastOcc = Math.max(lastOcc, mid);
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (firstOcc == Integer.MAX_VALUE) {
            firstOcc = -1;
        }
        return new int[]{firstOcc, lastOcc};
    }

    // Find K Rotation
    public int findKRotation(int arr[]) {
        if (arr.length == 1) {
            return 0;
        }
        int left = 0;
        int right = arr.length - 1;
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Lesser than both it's left & right
            if ((mid - 1 >= 0 && mid + 1 <= arr.length - 1 && arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) || (mid == arr.length - 1 && arr[mid] < arr[mid - 1] && arr[mid] < arr[0])) {
                ans = mid;
                break;
            } else if (arr[0] > arr[arr.length - 1] && arr[mid] < arr[0]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return ans;
    }

    // Find K Rotation
    public int findKRotationSimplified(int arr[]) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid; // because of the EXACT pivot
            }
        }

        return left;
    }

    // Find Peak Element
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        int ans = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Greater than both it's left & right
            if ((mid - 1 >= 0 && mid + 1 <= nums.length - 1 && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) || (mid == nums.length - 1 && nums[mid] > nums[mid - 1]) || (mid == 0 && nums[mid] > nums[mid + 1])) {
                ans = mid;
                break;
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return ans;
    }

    // Search a 2D Matrix
    public boolean searchMatrix(int[][] matrix, int target) {
        int width = matrix[0].length;
        int height = matrix.length;

        int left = 0;
        int right = width * height - 1;
        int length = width * height;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int i = mid / width;
            int j = mid % width;

            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }

    // Book Allocation
    public int findPages(int[] arr, int k) {
        if (k > arr.length) {
            return -1;
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

}
