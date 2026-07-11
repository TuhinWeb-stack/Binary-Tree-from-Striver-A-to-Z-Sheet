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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return compare(p, q);
    }

    // WHAT: Recursive helper that compares two nodes and their subtrees.
    // WHY: Breaks the "are these trees the same" question into smaller,
    // identical sub-questions on left and right children.
    private static boolean compare(TreeNode p, TreeNode q) {
        // 1. BASE CASE - BOTH NULL: Nothing to compare, so this branch matches.
        // WHY: If both trees "end" here at the same time, structure agrees.
        if (p == null && q == null) {
            return true;
        }

        // 2. BASE CASE - ONE NULL: One tree has a node, the other doesn't.
        // WHY: Structural mismatch — shapes differ, so trees can't be the same.
        if (p == null || q == null) {
            return false;
        }

        // 3. VALUE CHECK: Both nodes exist here, so it's now safe to read .val.
        // WHY: Even if structure matches, differing values break the match.
        if (p.val != q.val) {
            return false;
        }

        // 4. RECURSIVE STEP: Check left subtrees AND right subtrees.
        // WHY: The whole tree only matches if BOTH sides independently match.
        boolean leftSame = compare(p.left, q.left);
        boolean rightSame = compare(p.right, q.right);

        return leftSame && rightSame;
    }
}