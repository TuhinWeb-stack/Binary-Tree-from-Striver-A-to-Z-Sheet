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
    public int diameterOfBinaryTree(TreeNode root) {
        int[] maxDiameter= new int[1];
        calculateHeight(root , maxDiameter);
        return maxDiameter[0];
    }
    public static int calculateHeight(TreeNode node , int[] maxDiameter ){
        if(node==null){
            return 0;
        }
        int leftHeight= calculateHeight( node.left , maxDiameter);
        int rightHeight= calculateHeight( node.right , maxDiameter );

        maxDiameter[0]= Math.max(maxDiameter[0], leftHeight+rightHeight);

        return Math.max(leftHeight, rightHeight)+1;
    }
}