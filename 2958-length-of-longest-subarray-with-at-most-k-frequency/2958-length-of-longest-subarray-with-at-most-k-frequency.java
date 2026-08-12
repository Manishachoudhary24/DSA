import java.util.HashMap;
import java.util.Map;
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);
            while (count.get(nums[right]) > k) {
                count.put(nums[left], count.get(nums[left]) - 1);
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}