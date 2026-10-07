class Solution {
    public List<String> removeInvalidParentheses(String s) {
         int left = 0, right = 0;

        // count minimum removals needed
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) left--;
                else right++;
            }
        }

        List<String> result = new ArrayList<>();
        dfs(s, 0, left, right, result);
        return result;
    }

    private void dfs(String s, int start, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            if (isValid(s)) result.add(s);
            return;
        }

        for (int i = start; i < s.length(); i++) {
            // skip duplicates: only remove the first of a run of identical chars
            if (i > start && s.charAt(i) == s.charAt(i - 1)) continue;

            char c = s.charAt(i);

            // not enough characters left to remove
            if (s.length() - i < left + right) return;

            String next = s.substring(0, i) + s.substring(i + 1);

            if (c == '(' && left > 0) {
                dfs(next, i, left - 1, right, result);
            } else if (c == ')' && right > 0) {
                dfs(next, i, left, right - 1, result);
            }
        }
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            else if (c == ')') {
                if (--count < 0) return false;
            }
        }
        return count == 0;
    }
}