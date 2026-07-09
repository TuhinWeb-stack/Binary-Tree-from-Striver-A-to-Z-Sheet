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
        // We use an array of size 1 instead of a primitive int because arrays are 
        // passed by reference in Java, allowing recursive calls to modify the shared maximum value.
        int[] maxDiameter = new int[1];
        calculateHeight(root, maxDiameter);
        return maxDiameter[0];
    }

    // WHAT: Bottom-up height calculator that updates our global path tracker on the fly.
    // WHY: Returns the height of the current node's subtree while simultaneously 
    // checking if the left-to-right path spanning across this node is the new longest trail.
    private static int calculateHeight(TreeNode node, int[] maxDiameter) {
        // Base Case: An empty node has a height contribution of 0.
        if (node == null) {
            return 0;
        }

        // 1. LEFT STEP: Find the deep height path on the left branch.
        int leftHeight = calculateHeight(node.left, maxDiameter);

        // 2. RIGHT STEP: Find the deep height path on the right branch.
        int rightHeight = calculateHeight(node.right, maxDiameter);

        // 3. DIAMETER RECORD CHECK: Calculate edges passing through this node.
        // WHY: The number of edges combining both sides is exactly leftHeight + rightHeight.
        maxDiameter[0] = Math.max(maxDiameter[0], leftHeight + rightHeight);

        // 4. HEIGHT RETURN: Return the height of this current subtree back up to the parent.
        return Math.max(leftHeight, rightHeight) + 1;
    }
}