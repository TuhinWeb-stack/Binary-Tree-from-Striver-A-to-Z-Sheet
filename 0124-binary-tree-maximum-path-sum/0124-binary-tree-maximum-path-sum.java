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
    public int maxPathSum(TreeNode root) {
        int[] globalMax= new int[1];
        globalMax[0]= Integer.MIN_VALUE;
        
        currentMaxPath(root , globalMax);
        return globalMax[0];
    }
    public static int currentMaxPath(TreeNode node, int[] globalMax){
        if(node==null){
            return 0;
        }
        int leftGain= Math.max(currentMaxPath(node.left, globalMax), 0);
        int rightGain= Math.max(currentMaxPath(node.right, globalMax), 0);
        int currentMax= node.val +leftGain +rightGain;

        globalMax[0]= Math.max(globalMax[0], currentMax);

        return node.val+ Math.max(leftGain, rightGain);
    }
}