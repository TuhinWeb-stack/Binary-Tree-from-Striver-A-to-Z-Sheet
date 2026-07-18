/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public static class NodeInfo{
            int row;
            int val;

            NodeInfo(int row , int val){
                this.row= row;
                this.val= val;
            }
        }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result= new ArrayList<>();
        
        if(root== null){
            return result;
        }

        TreeMap<Integer, List<NodeInfo>> columsTree= new TreeMap<>();
        Queue<Object[]> q= new LinkedList<>();
        q.offer(new Object[]{root, 0 , 0});

        while(!q.isEmpty()){
            Object[] current = q.poll();
            TreeNode node= (TreeNode)current[0];
            int row= (int)current[1];
            int col= (int)current[2];

            columsTree.computeIfAbsent(col , k-> new ArrayList<>()).add(new NodeInfo(row , node.val));

            if(node.left!=null){
                q.offer(new Object[]{node.left, row+1, col-1});
            }
            if(node.right!=null){
                q.offer(new Object[]{node.right, row+1 , col+1});
            }
        }
        for(List<NodeInfo>curr : columsTree.values()){
            curr.sort((a,b)->{
                if(a.row!=b.row){
                    return Integer.compare(a.row, b.row);
                }
                return Integer.compare(a.val, b.val);
            });
            List<Integer> columnValues= new ArrayList<>();
            for(NodeInfo info : curr){
                columnValues.add(info.val);
            }
            result.add(columnValues);
        }
        return result;
    }
}