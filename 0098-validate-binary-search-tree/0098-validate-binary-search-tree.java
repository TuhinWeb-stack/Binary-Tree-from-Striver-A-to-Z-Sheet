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
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // WHAT: Recursive helper that checks if 'node' AND its entire subtree
    // fit strictly within the range (min, max) - not just the node itself.
    // WHY: The valid range narrows as we descend, correctly propagating
    // constraints from ALL ancestors (not just the immediate parent) down
    // to every descendant - this is what catches violations like a
    // deeply-nested node breaking a rule set by a distant ancestor.
    private static boolean validate(TreeNode node, long min, long max) {
        // 1. BASE CASE - NULL NODE: An empty subtree trivially satisfies
        // any range constraint - nothing here to violate anything.
        if (node == null) {
            return true;
        }

        // 2. RANGE CHECK: Current node's value must be STRICTLY within
        // (min, max) - using strict inequalities enforces "strictly less/
        // greater than" as required by the problem (no duplicates allowed).
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // 3. RECURSE LEFT: Everything in the left subtree must be less
        // than node.val, so we TIGHTEN the upper bound to node.val while
        // keeping the same lower bound (min) inherited from ancestors.
        // WHY: This is how a distant ancestor's constraint (e.g., "must be
        // < 5") correctly carries all the way down through every left
        // step, not just the immediate child.
        boolean leftValid = validate(node.left, min, node.val);

        // 4. RECURSE RIGHT: Everything in the right subtree must be
        // greater than node.val, so we TIGHTEN the lower bound to
        // node.val while keeping the same upper bound (max).
        boolean rightValid = validate(node.right, node.val, max);

        // 5. COMBINE: The whole subtree is valid only if BOTH sides are
        // valid - a single violation anywhere invalidates everything.
        return leftValid && rightValid;
    }
}