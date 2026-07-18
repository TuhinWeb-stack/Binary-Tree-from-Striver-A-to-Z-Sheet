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
    public boolean isSymmetric(TreeNode root) {
        // 1. EDGE CASE: An empty tree has no asymmetry to find.
        if (root == null) {
            return true;
        }

        // WHY: We compare root.left against root.right — NOT root against
        // itself — because symmetry is about the two halves mirroring
        // each other, not the root matching anything.
        return isMirror(root.left, root.right);
    }

    // WHAT: Recursive helper that checks if two subtrees are MIRROR images
    // of each other (crossed comparison), not identical copies.
    // WHY: This is the core logic — reusing the "Same Tree" recursion
    // pattern but swapping which children get compared against which.
    private static boolean isMirror(TreeNode left, TreeNode right) {
        // 1. BASE CASE - BOTH NULL: Nothing here on either side, mirrors fine.
        if (left == null && right == null) {
            return true;
        }

        // 2. BASE CASE - ONE NULL: One side has a node, other doesn't —
        // shapes can't mirror each other.
        if (left == null || right == null) {
            return false;
        }

        // 3. VALUE CHECK: Both nodes exist, so now check if their values match.
        // WHY: For a mirror, the values at symmetric positions must be equal.
        if (left.val != right.val) {
            return false;
        }

        // 4. CROSSED RECURSIVE STEP: This is the key difference from "Same Tree".
        // WHY: In a mirror, the LEFT side's outer edge (left.left) must match
        // the RIGHT side's outer edge (right.right) — think of folding the
        // tree in half like a reflection. Similarly, left.right (inner) must
        // match right.left (inner).
        boolean outerMatch = isMirror(left.left, right.right);
        boolean innerMatch = isMirror(left.right, right.left);

        return outerMatch && innerMatch;
    }
}