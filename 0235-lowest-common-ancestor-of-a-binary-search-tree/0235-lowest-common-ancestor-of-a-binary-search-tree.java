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

    // WHAT: Recursive helper that uses BST ordering to navigate directly
    // toward the split point where p and q's paths diverge.
    // WHY: Comparing p.val and q.val against node.val tells us with
    // certainty which subtree (if any) could contain BOTH targets -
    // no need to search blindly like in a non-BST tree.
    private static TreeNode findLCA(TreeNode node, TreeNode p, TreeNode q) {
        // 1. DECISION - BOTH TARGETS SMALLER: If both p and q are less
        // than the current node's value, BST guarantees both must live
        // in the left subtree - so the LCA must be there too.
        // WHY: No need to check the right subtree at all - it's
        // guaranteed to contain neither p nor q.
        if (p.val < node.val && q.val < node.val) {
            return findLCA(node.left, p, q);
        }

        // 2. DECISION - BOTH TARGETS LARGER: Symmetric case - both must
        // be in the right subtree.
        if (p.val > node.val && q.val > node.val) {
            return findLCA(node.right, p, q);
        }

        // 3. SPLIT POINT FOUND: If we reach here, it means NEITHER of
        // the above conditions held - meaning either:
        //   (a) p and q are on OPPOSITE sides of node.val (one smaller,
        //       one larger) - node is exactly where their paths diverge, OR
        //   (b) node.val itself equals p.val or q.val - node IS one of
        //       our targets, and per LCA definition a node can be its
        //       own ancestor.
        // WHY: Either way, THIS node is the lowest common ancestor -
        // going further in any direction would "overshoot" and lose
        // one of the two targets.
        return node;
    }
}