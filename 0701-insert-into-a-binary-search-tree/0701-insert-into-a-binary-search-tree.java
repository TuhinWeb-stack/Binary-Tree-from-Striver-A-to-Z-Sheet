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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return insert(root, val);
    }

    // WHAT: Recursive helper that finds the correct null spot for val and
    // returns the (possibly newly-created) node that should sit at this position.
    // WHY: By having every call RETURN what belongs at its position, the
    // parent automatically re-links itself (node.left = ... / node.right = ...)
    // as the recursion unwinds - no manual parent-pointer tracking needed.
    private static TreeNode insert(TreeNode node, int val) {
        // 1. BASE CASE - FOUND THE INSERTION SPOT: We've hit null, meaning
        // this exact position is empty - this is where val belongs.
        // WHY: Creating and returning a new node here is what actually
        // performs the insertion; the caller will attach it via
        // node.left = ... or node.right = ... one level up.
        if (node == null) {
            return new TreeNode(val);
        }

        // 2. DECISION - GO LEFT: If val is smaller, it belongs somewhere
        // in the left subtree (BST property).
        // WHY: We reassign node.left to the RESULT of the recursive call -
        // if node.left was already non-null, this just re-attaches the
        // same subtree (now with val inserted deeper inside it). If
        // node.left WAS null, this attaches the brand-new node directly.
        if (val < node.val) {
            node.left = insert(node.left, val);
        }
        // 3. DECISION - GO RIGHT: Otherwise, val belongs in the right subtree.
        // WHY: val is guaranteed not to equal node.val (no duplicates),
        // so "not less than" safely means "greater than" here.
        else {
            node.right = insert(node.right, val);
        }

        // 4. RETURN UNCHANGED NODE: This node itself isn't the insertion
        // point, so we just return it as-is, letting the CALLER re-attach
        // it to its own parent unchanged.
        // WHY: This is what makes the "self-linking" trick work - every
        // node on the path from root down to the new leaf gets its
        // left/right pointer refreshed (even if it points to the same
        // subtree as before), keeping the whole tree connected correctly.
        return node;
    }
}