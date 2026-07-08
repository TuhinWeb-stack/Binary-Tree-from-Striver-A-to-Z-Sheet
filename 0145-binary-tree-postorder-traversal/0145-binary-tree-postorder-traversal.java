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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    // WHAT: Recursive processing engine that tracks depth state.
    private static void traverse(TreeNode node, List<Integer> result) {
        // Base Case: If we step onto an empty node link, bounce safely back up the call stack.
        if (node == null) {
            return;
        }

        // 1. LEFT STEP: Recursively clear the entire left subtree branch first.
        // WHY: Postorder traversal requires evaluating children before processing parents.
        traverse(node.left, result);

        // 2. RIGHT STEP: Recursively clear the entire right subtree branch.
        traverse(node.right, result);

        // 3. ROOT STEP: Log the current node value last.
        // WHY: The parent node can only be recorded after both subtrees are completely finished.
        result.add(node.val);
    }
}