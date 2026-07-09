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
    public int maxDepth(TreeNode root) {
        // Base Case: If the current node is null, its depth contribution is 0.
        if (root == null) {
            return 0;
        }

        // WHAT: Recursively calculate the max depth of the left and right subtrees.
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // WHY: The maximum depth at the current node is the larger of the two 
        // subtree depths, plus 1 to account for the current node itself.
        return Math.max(leftDepth, rightDepth) + 1;
    }
}