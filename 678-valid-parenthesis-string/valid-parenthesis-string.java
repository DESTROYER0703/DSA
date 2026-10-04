class Solution {
    public boolean checkValidString(String s) {
        int lo = 0; // minimum possible open count
        int hi = 0; // maximum possible open count

        for (char c : s.toCharArray()) {
            if (c == '(') {
                lo++;
                hi++;
            } else if (c == ')') {
                lo--;
                hi--;
            } else { // '*'
                lo--;
                hi++;
            }

            if (hi < 0) return false; // too many ')' even in the best case
            if (lo < 0) lo = 0;       // can't have negative open count
        }

        return lo == 0;
    }
}