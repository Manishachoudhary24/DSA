import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();     
        for (int[] seat : reservedSeats) {
            int row = seat[0], col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << (col - 2)));
            }
        }    
        int maxGroups = (n - rowMasks.size()) * 2;
        int leftMask = 0b00001111;   
        int middleMask = 0b00111100; 
        int rightMask = 0b11110000;        
        for (int mask : rowMasks.values()) {
            boolean left = (mask & leftMask) == 0;
            boolean right = (mask & rightMask) == 0;
            boolean middle = (mask & middleMask) == 0; 
            if (left && right) maxGroups += 2;
            else if (left || right || middle) maxGroups += 1;
        }
        return maxGroups;
    }
}