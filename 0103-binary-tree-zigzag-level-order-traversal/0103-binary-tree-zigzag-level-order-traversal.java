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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result= new LinkedList<>();
        if(root==null){
            return result;
        }
        boolean reverseOrder= false;
        Queue<TreeNode> q= new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int levelSize= q.size();
            List<Integer> currentLevel= new LinkedList<>();

            for(int i= 0; i<levelSize; i++){
                TreeNode currNode= q.poll();
                currentLevel.add(currNode.val);

                if(currNode.left!= null){
                q.offer(currNode.left);
                }
                if(currNode.right!= null){
                q.offer(currNode.right);
                }
            }

            if(reverseOrder){
                Collections.reverse(currentLevel);
            }
            result.add(currentLevel);
            reverseOrder= !reverseOrder;
            
        }
        return result;
    }
}