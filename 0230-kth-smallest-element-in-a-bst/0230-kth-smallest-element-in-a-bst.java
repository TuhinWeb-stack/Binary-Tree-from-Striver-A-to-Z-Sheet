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
    public int kthSmallest(TreeNode root, int k) {
       int[] count = new int[1];  // tracks how many nodes visited so far
        int[] result = new int[1]; // stores the answer once found

        inorder(root, k, count, result);

        return result[0];
    }

    // WHAT: In-order traversal (Left -> Node -> Right) that visits BST
    // nodes in ascending sorted order, stopping early once the kth node
    // is reached.
    // WHY: In-order traversal on a BST is GUARANTEED to produce values
    // in sorted order - this is the core property that makes this
    // problem trivial once recognized.
    private static void inorder(TreeNode node, int k, int[] count, int[] result) {
        // 1. BASE CASE - NULL NODE: Nothing to visit here.
        // WHY: Also acts as an early-exit guard - if count[0] already
        // reached k in a sibling branch, we don't want to keep recursing
        // pointlessly (see step 2 for the actual short-circuit check).
        if (node == null) {
            return;
        }

        // 2. EARLY EXIT CHECK: If we've already found our answer in an
        // earlier part of the traversal, stop doing any further work.
        // WHY: Without this, we'd keep traversing the rest of the tree
        // even after finding the kth smallest - wasteful once k is small
        // and the tree is large.
        if (count[0] >= k) {
            return;
        }

        // 3. GO LEFT FIRST: Explore all smaller values before this node.
        // WHY: This is what guarantees ascending order - everything in
        // the left subtree is smaller and must be counted before we count
        // the current node itself.
        inorder(node.left, k, count, result);

        // 4. VISIT CURRENT NODE: Increment the counter - we've now
        // "seen" one more value in sorted order.
        count[0]++;

        // WHY: The moment our running count matches k, THIS node's value
        // is exactly the kth smallest - record it as the answer.
        if (count[0] == k) {
            result[0] = node.val;
            return; // WHY: no need to explore the right subtree at all now
        }

        // 5. GO RIGHT: Only reached if we haven't found the kth element
        // yet - explore larger values next, in sorted order.
        inorder(node.right, k, count, result); 
    }
}