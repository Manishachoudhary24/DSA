class Solution {
    class Node {
        int maxLen;
        int prefixLen;
        int suffixLen;
        char leftChar;
        char rightChar;
        Node(char c) {
            this.maxLen = 1;
            this.prefixLen = 1;
            this.suffixLen = 1;
            this.leftChar = c;
            this.rightChar = c;
        }
        Node() {}
    }
    private Node[] tree;
    private char[] chars;
    private int n;
    private Node merge(Node left, Node right, int leftLen, int rightLen) {
        Node res = new Node();
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;   
        res.maxLen = Math.max(left.maxLen, right.maxLen);
        res.prefixLen = left.prefixLen;
        res.suffixLen = right.suffixLen;
        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);

            if (left.prefixLen == leftLen) {
                res.prefixLen = leftLen + right.prefixLen;
            }
            if (right.suffixLen == rightLen) {
                res.suffixLen = rightLen + left.suffixLen;
            }
        }

        return res;
    }
    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }
    private void update(int node, int start, int end, int idx, char val) {
        if (start == end) {
            chars[idx] = val;
            tree[node] = new Node(val);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        chars = s.toCharArray();
        tree = new Node[4 * n];
        build(1, 0, n - 1);
        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            update(1, 0, n - 1, idx, ch);
            ans[i] = tree[1].maxLen;
        }
        return ans;
    }
}