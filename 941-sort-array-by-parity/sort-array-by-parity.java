class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // Move left pointer rightward until an odd number is found
            while (left < right && nums[left] % 2 == 0) {
                left++;
            }
            // Move right pointer leftward until an even number is found
            while (left < right && nums[right] % 2 != 0) {
                right--;
            }
            
            // Swap the odd number at left with the even number at right
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        
        return nums;
    }
}