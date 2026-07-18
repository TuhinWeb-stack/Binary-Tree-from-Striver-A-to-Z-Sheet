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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // 1. EDGE CASE: Empty tree has no levels, hence nothing visible.
        // WHY: Prevents pushing a null root into the queue, which would
        // crash the very first poll().
        if (root == null) {
            return result;
        }

        // 2. QUEUE SETUP: Standard BFS uses a queue to process nodes level-by-level.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 3. MAIN BFS LOOP: Keep going while there are nodes left to process.
        while (!queue.isEmpty()) {
            // 3a. LEVEL SIZE SNAPSHOT: Freeze how many nodes belong to THIS
            // level before the inner loop starts adding children to the queue.
            // WHY: Without this snapshot, queue.size() would keep growing
            // mid-loop and we'd lose track of level boundaries.
            int levelSize = queue.size();

            // 3b. PROCESS EXACTLY ONE LEVEL: Loop only levelSize times.
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // WHY: If this is the LAST node in the current level's
                // left-to-right processing order, it's the rightmost node —
                // exactly the one visible from the right side.
                if (i == levelSize - 1) {
                    result.add(node.val);
                }

                // WHY: Always push left before right — this guarantees the
                // queue processes nodes in true left-to-right order, which
                // is what makes "last node in the loop" == "rightmost node".
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return result;
    }
}