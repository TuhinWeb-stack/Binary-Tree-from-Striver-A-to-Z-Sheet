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
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE ,Long.MAX_VALUE);
    }
    public static boolean validate(TreeNode node, long min, long max){
        if(node== null){
            return true;
        }
        if(node.val<=min || node.val>=max){
            return false;
        }
        boolean leftRes= validate(node.left,min, node.val);
        boolean rightRes= validate(node.right, node.val, max);

        return leftRes && rightRes;
    }
}