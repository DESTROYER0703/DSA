class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, String str,
                           int open, int close, int n) {

        // We have used all n pairs
        if (str.length() == 2 * n) {
            ans.add(str);
            return;
        }

        // We can add '(' if we still have some left
        if (open < n) {
            backtrack(ans, str + "(", open + 1, close, n);
        }

        // We can add ')' only if there is an unmatched '('
        if (close < open) {
            backtrack(ans, str + ")", open, close + 1, n);
        }
        
    }
}