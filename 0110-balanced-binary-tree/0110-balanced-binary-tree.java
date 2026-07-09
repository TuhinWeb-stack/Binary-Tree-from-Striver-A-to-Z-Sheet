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
    public boolean isBalanced(TreeNode root) {
        // If the bottom-up check returns -1, it means the tree is unbalanced.
        return checkHeight(root) != -1;
    }

    // WHAT: Bottom-up height calculator that performs a twin task.
    // WHY: It returns the actual height if the subtree is perfectly balanced, 
    // but returns -1 instantly if any structural imbalance is detected.
    private static int checkHeight(TreeNode node) {
        // Base Case: An empty node contributes 0 to the total height path.
        if (node == null) {
            return 0;
        }

        // 1. LEFT STEP: Traverse down into the left subtree branch.
        int leftHeight = checkHeight(node.left);
        // WHY: If a failure sentinel (-1) bubbles up from beneath, pass it up instantly.
        if (leftHeight == -1) {
            return -1; 
        }

        // 2. RIGHT STEP: Traverse down into the right subtree branch.
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        // 3. BALANCE CHECK STEP: Compare the absolute difference between subtrees.
        // WHY: If the delta height is greater than 1, this specific node is unbalanced.
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Trigger and bubble up the failure state sentinel
        }

        // 4. BALANCED PATH HEIGHT: If it passes validation, return its true height.
        return Math.max(leftHeight, rightHeight) + 1;
    }
}