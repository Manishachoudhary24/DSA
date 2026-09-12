class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        boolean negative = (dividend < 0) ^ (divisor < 0);
        int a = dividend < 0 ? dividend : -dividend;
        int b = divisor < 0 ? divisor : -divisor;
        int quotient = 0;
        while (a <= b) {
            int temp = b, multiple = 1;
            while (temp >= (Integer.MIN_VALUE >> 1) && a <= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            quotient += multiple;
        }
        return negative ? -quotient : quotient;
    }
}