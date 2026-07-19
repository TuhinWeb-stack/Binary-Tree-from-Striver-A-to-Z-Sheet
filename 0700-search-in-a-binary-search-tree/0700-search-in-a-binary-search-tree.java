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
    public TreeNode searchBST(TreeNode root, int val) {
        // 1. BASE CASE - NULL NODE: We've run out of tree to search,
        // meaning val doesn't exist anywhere on this path.
        // WHY: This also naturally handles "target not found" - if we
        // never hit base case 2 (match), we eventually hit this and
        // return null.
        if (root == null) {
            return null;
        }

        // 2. BASE CASE - FOUND MATCH: Current node IS the target.
        // WHY: Returning 'root' here automatically returns the WHOLE
        // subtree too, since root.left and root.right pointers are
        // still intact - exactly what the problem asks for.
        if (root.val == val) {
            return root;
        }

        // 3. DECISION - GO LEFT: If val is smaller than current node's
        // value, the BST guarantees it can ONLY exist (if at all) in
        // the left subtree - no need to ever check the right side.
        if (val < root.val) {
            return searchBST(root.left, val);
        }

        // 4. DECISION - GO RIGHT: Otherwise (val > root.val), the BST
        // guarantees it can only exist in the right subtree.
        // WHY: We don't need an explicit "val > root.val" check since
        // we've already ruled out "==" and "<" above - this is the
        // only remaining case.
        return searchBST(root.right, val);
    }
}