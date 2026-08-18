class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }
        if (k == 1) {
            int[] freq = new int[51];
            for (int num : nums) {
                freq[num]++;
            }
            int ans = -1;
            for (int num : nums) {
                if (freq[num] == 1) {
                    ans = Math.max(ans, num);
                }
            }
            return ans;
        }     
        int countFirst = 0;
        int countLast = 0;
        for (int num : nums) {
            if (num == nums[0]) countFirst++;
            if (num == nums[n - 1]) countLast++;
        }
        int ans = -1;
        if (countFirst == 1) {
            ans = Math.max(ans, nums[0]);
        }
        if (countLast == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }
        return ans;
    }
}