class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0, count = 0;

        for (int x : nums) {
            if (x < target) less++;
            else if (x == target) count++;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(less + i);
        }

        return result;
    }
}