class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[] sum, sq;
    private int[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new int[4 * n];

        int[] last = new int[100_001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;
        for (int j = 0; j < n; j++) {
            int prev = last[nums[j]];
            update(1, 0, n - 1, prev + 1, j);
            last[nums[j]] = j;
            ans = (ans + sq[1]) % MOD;
        }
        return (int) ans;
    }

    private void apply(int node, int len, int v) {
        sq[node] = (sq[node] + 2L * v * sum[node] + (long) v * v % MOD * len) % MOD;
        sum[node] += (long) v * len; // max ~1e10, fits in long
        lazy[node] += v;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, mid - l + 1, lazy[node]);
            apply(2 * node + 1, r - mid, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return;
        if (ql <= l && r <= qr) {
            apply(node, r - l + 1, 1);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr);
        update(2 * node + 1, mid + 1, r, ql, qr);
        sum[node] = sum[2 * node] + sum[2 * node + 1];
        sq[node] = (sq[2 * node] + sq[2 * node + 1]) % MOD;
    }
}