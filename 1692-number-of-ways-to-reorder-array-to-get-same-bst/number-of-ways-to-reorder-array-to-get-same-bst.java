class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);

        return (int) ((ways(list) - 1 + MOD) % MOD);
    }

    private long ways(List<Integer> arr) {
        int m = arr.size();
        if (m <= 2) return 1;

        int root = arr.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < m; i++) {
            int x = arr.get(i);
            if (x < root) left.add(x);
            else right.add(x);
        }

        long res = comb[m - 1][left.size()];
        res = res * ways(left) % MOD;
        res = res * ways(right) % MOD;
        return res;
    }
}