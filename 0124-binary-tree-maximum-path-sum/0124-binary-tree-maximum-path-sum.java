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
        int[] globalMax = new int[1];
        globalMax[0] = Integer.MIN_VALUE; // Initialize to the lowest possible value
        
        calculateMaxGain(root, globalMax);
        return globalMax[0];
    }

    // WHAT: Bottom-up recursive calculator that extracts branch gains.
    // WHY: Returns the maximum single-branch path sum running out of this node 
    // while checking if a combined path through this node breaks the global record.
    private static int calculateMaxGain(TreeNode node, int[] globalMax) {
        // Base Case: An empty node adds absolutely nothing to the path sum.
        if (node == null) {
            return 0;
        }

        // 1. LEFT STEP: Find the max path gain running down the left branch.
        // WHY: We use Math.max(..., 0) because if a branch returns a negative sum, 
        // it is always better to discard it completely (turn it into 0).
        int leftGain = Math.max(calculateMaxGain(node.left, globalMax), 0);

        // 2. RIGHT STEP: Find the max path gain running down the right branch.
        int rightGain = Math.max(calculateMaxGain(node.right, globalMax), 0);

        // 3. UPDATE GLOBAL MAX RECORD: Calculate the complete path sum centered at this node.
        // WHY: A path can connect through this node from left to right, making a full arch.
        int currentPathSum = node.val + leftGain + rightGain;
        globalMax[0] = Math.max(globalMax[0], currentPathSum);

        // 4. RETURN VALUE: Return the maximum single-branch gain back up to the parent.
        // WHY: The parent can only use one branch (left or right) to maintain a continuous path line.
        return node.val + Math.max(leftGain, rightGain);
    }
}