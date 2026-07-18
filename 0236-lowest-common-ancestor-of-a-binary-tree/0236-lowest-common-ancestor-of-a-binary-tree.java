/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findLCA(root, p, q);
    }

    // WHAT: Post-order recursive search that returns what it "found" in
    // the subtree rooted at 'node' — either p, q, their LCA, or null.
    // WHY: By fully exploring left AND right subtrees first (post-order),
    // we can compare their results and decide, at THIS level, whether
    // this node is the meeting point (LCA) of p and q.
    private static TreeNode findLCA(TreeNode node, TreeNode p, TreeNode q) {
        // 1. BASE CASE - NULL NODE: An empty subtree contains neither p nor q.
        // WHY: This naturally terminates recursion at leaf children.
        if (node == null) {
            return null;
        }

        // 2. BASE CASE - FOUND A TARGET: If this node IS p or IS q, return
        // it immediately without searching deeper in this subtree.
        // WHY: We don't need to look further down — even if the OTHER
        // target lives below this node, this node itself is still a valid
        // ancestor candidate. Let the caller (parent) figure out the rest.
        if (node == p || node == q) {
            return node;
        }

        // 3. SEARCH LEFT SUBTREE: Recursively look for p/q on the left side.
        TreeNode leftResult = findLCA(node.left, p, q);

        // 4. SEARCH RIGHT SUBTREE: Recursively look for p/q on the right side.
        TreeNode rightResult = findLCA(node.right, p, q);

        // 5. DECISION - BOTH SIDES FOUND SOMETHING: This means p was found
        // on one side and q on the other (or one of them IS a child branch
        // result equal to p/q directly) — so THIS node is the meeting
        // point, i.e., the LCA.
        // WHY: p and q diverge into different subtrees exactly at their LCA.
        if (leftResult != null && rightResult != null) {
            return node;
        }

        // 6. DECISION - ONLY LEFT FOUND SOMETHING: Whatever was found
        // (could be p, q, or an already-resolved LCA) must propagate
        // upward unchanged, since the right side had nothing relevant.
        if (leftResult != null) {
            return leftResult;
        }

        // 7. DECISION - ONLY RIGHT FOUND SOMETHING (or neither): Same
        // logic as above, mirrored. If both are null, this naturally
        // returns null too (rightResult would be null).
        return rightResult;
    }
}