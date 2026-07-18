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
    private static class QueueEntry {
        TreeNode node;
        long index; // WHY long: prevents overflow in deep, skewed trees

        QueueEntry(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        // 1. EDGE CASE: Single/empty guard - not strictly needed since
        // constraints guarantee at least 1 node, but kept for safety.
        if (root == null) {
            return 0;
        }

        int maxWidth = 0;

        Queue<QueueEntry> queue = new LinkedList<>();
        queue.offer(new QueueEntry(root, 0)); // root starts at index 0

        // 2. MAIN BFS LOOP: Process the tree level by level.
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // WHY: We need the index of the very first node processed in
            // this level to (a) compute width, and (b) re-base later indices
            // in this level to prevent overflow.
            long firstIndexInLevel = queue.peek().index;

            long firstIndex = 0;
            long lastIndex = 0;

            // 3. PROCESS EXACTLY ONE LEVEL: Loop only levelSize times.
            for (int i = 0; i < levelSize; i++) {
                QueueEntry current = queue.poll();
                TreeNode node = current.node;

                // WHY: Re-base (normalize) the index relative to the first
                // node's index in THIS level. This keeps numbers small even
                // in deep skewed trees, preventing overflow, while the
                // RELATIVE distance (width) stays mathematically identical.
                long normalizedIndex = current.index - firstIndexInLevel;

                if (i == 0) {
                    firstIndex = normalizedIndex;
                }
                if (i == levelSize - 1) {
                    lastIndex = normalizedIndex;
                }

                // WHY: Standard complete-binary-tree indexing — left child
                // is always 2*i, right child is always 2*i + 1, computed
                // from the ORIGINAL (non-normalized) index to stay consistent
                // with children pushed by sibling nodes in the same level.
                if (node.left != null) {
                    queue.offer(new QueueEntry(node.left, current.index * 2));
                }
                if (node.right != null) {
                    queue.offer(new QueueEntry(node.right, current.index * 2 + 1));
                }
            }

            // 4. UPDATE MAX WIDTH: width = last - first + 1 for this level.
            // WHY: +1 because width counts INCLUSIVE of both endpoints
            // (e.g., indices 0 and 3 span positions 0,1,2,3 -> width 4).
            int currentWidth = (int) (lastIndex - firstIndex + 1);
            maxWidth = Math.max(maxWidth, currentWidth);
        }

        return maxWidth;
    }
}