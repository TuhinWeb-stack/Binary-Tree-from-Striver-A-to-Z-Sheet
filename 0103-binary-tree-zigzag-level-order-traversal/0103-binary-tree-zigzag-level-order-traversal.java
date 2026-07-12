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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // 1. EDGE CASE: Empty tree has no levels to traverse.
        // WHY: Avoids adding a null node into the queue, which would crash later.
        if (root == null) {
            return result;
        }

        // 2. SETUP QUEUE: Standard BFS uses a queue to process nodes level-by-level.
        // WHY: Queue (FIFO) guarantees we process nodes in the order they were
        // discovered, which naturally groups nodes level by level.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 3. LEVEL TRACKER: Toggles direction for each level processed.
        // WHY: false = left-to-right (natural order), true = right-to-left (reversed).
        boolean reverseThisLevel = false;

        // 4. MAIN BFS LOOP: Keep going while there are nodes left to process.
        while (!queue.isEmpty()) {
            // 4a. LEVEL SIZE SNAPSHOT: Capture how many nodes belong to THIS level
            // WHY: queue.size() changes as we add children, so we must "freeze"
            // the count before the inner loop starts modifying the queue.
            int levelSize = queue.size();
            LinkedList<Integer> currentLevel = new LinkedList<>();

            // 4b. PROCESS EXACTLY ONE LEVEL: Loop only levelSize times.
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // WHY: Add to the level list in natural left-to-right order first;
                // we'll reverse the whole list afterward if needed (simpler than
                // conditionally inserting at front/back during collection).
                currentLevel.add(node.val);

                // WHY: Push children in left-then-right order regardless of
                // zigzag direction — the QUEUE processing order must stay
                // consistent; only the OUTPUT list order flips.
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // 4c. APPLY ZIGZAG: Reverse this level's list if flag says so.
            // WHY: This is the only step that actually creates the "zigzag" effect.
            if (reverseThisLevel) {
                Collections.reverse(currentLevel);
            }

            result.add(currentLevel);

            // 4d. TOGGLE DIRECTION: Flip for the next level.
            // WHY: Zigzag alternates EVERY level, so we flip after each level completes.
            reverseThisLevel = !reverseThisLevel;
        }

        return result;
    }
}