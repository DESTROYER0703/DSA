class Solution {
    private List<Integer> generateRow(int row){
        long ans = 1;
        List<Integer> ansRow=new ArrayList <>();
        ansRow.add(1);
        for(int col = 1; col<row;col++){
            ans = ans*(row-col)/col;
            
            ansRow.add((int) ans);
        }
        return ansRow;
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pasc = new ArrayList<>();
        for (int i = 1;i<=numRows;i++){
            pasc.add(generateRow(i));
        }
        return pasc;
    }      
}