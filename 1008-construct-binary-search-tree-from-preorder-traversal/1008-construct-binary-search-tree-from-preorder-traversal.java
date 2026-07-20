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
    // WHAT: Shared mutable index tracking our current position in the
    // preorder array, visible across all recursive calls.
    // WHY: An int[] (array-wrapped) acts as a "pass by reference" trick in
    // Java - a plain int parameter would reset/copy on every recursive
    // call, but we need EVERY call (across the whole recursion tree) to
    // see and advance the SAME shared position, since preorder values are
    // consumed strictly once, left to right, across the entire build.
    private static int[] index;

    // WHAT: Static entry point matching LeetCode's method layout signature.
    // WHY: Initializes the shared index pointer, then kicks off recursion
    // with an unbounded range (-infinity, +infinity) since the root has
    // no constraints from any ancestor yet.
    public TreeNode bstFromPreorder(int[] preorder) {
         index = new int[1]; // starts at 0 automatically
        return build(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // WHAT: Recursive helper that consumes preorder values (via the shared
    // index) as long as they fit within the valid (min, max) range for
    // this position in the tree.
    // WHY: This mirrors the exact same range-narrowing pattern from
    // Validate BST - except here we're CONSTRUCTING nodes instead of just
    // checking them, using the range to decide when to STOP building the
    // current subtree and hand control back to the caller.
    private static TreeNode build(int[] preorder, int min, int max) {
        // 1. BASE CASE - ARRAY EXHAUSTED: No more values left to place
        // anywhere in the tree.
        if (index[0] == preorder.length) {
            return null;
        }

        int currentVal = preorder[index[0]];

        // 2. BASE CASE - VALUE OUT OF RANGE: The next unconsumed value
        // doesn't belong in THIS subtree - it belongs further up the call
        // stack, in a different (less constrained) part of the tree.
        // WHY: This is the signal that tells us "this subtree is complete,
        // stop building here" - crucially, we do NOT advance index[0] in
        // this case, since we haven't actually consumed/used this value yet.
        if (currentVal < min || currentVal > max) {
            return null;
        }

        // 3. CONSUME THE VALUE: This value belongs here - create the node
        // and advance the shared pointer so the NEXT recursive call (for
        // the left subtree) starts from the following array position.
        index[0]++;
        TreeNode node = new TreeNode(currentVal);

        // 4. BUILD LEFT SUBTREE: Everything after this in the array that's
        // still LESS than currentVal belongs on the left - so we tighten
        // the upper bound to currentVal (min stays inherited from ancestors).
        // WHY: BST property - left subtree must be strictly less than node.
        node.left = build(preorder, min, currentVal);

        // 5. BUILD RIGHT SUBTREE: Whatever's left in the array at this
        // point (after left subtree fully consumed its portion) must be
        // greater than currentVal - so we tighten the lower bound.
        // WHY: BST property - right subtree must be strictly greater.
        node.right = build(preorder, currentVal, max);

        return node;
    }
}