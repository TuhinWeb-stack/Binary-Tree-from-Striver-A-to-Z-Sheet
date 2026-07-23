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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();

        // 1. EDGE CASE: Empty tree has no nodes at any distance.
        if (root == null) {
            return result;
        }

        // 2. PHASE 1 - BUILD PARENT MAP: Traverse the whole tree once,
        // recording each node's parent.
        // WHY: A tree's pointers only go downward (parent -> child). To
        // move UPWARD during BFS later, we need a reverse lookup.
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);

        // 3. PHASE 2 - BFS SETUP: Standard BFS from the target node.
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(target);
        visited.add(target);

        int currentDistance = 0;

        // 4. MAIN BFS LOOP: Expand outward one distance-level at a time.
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // WHY: If we've reached the target distance k, every node
            // CURRENTLY in the queue is exactly k edges away from target -
            // collect them all and stop immediately (no need to go further).
            if (currentDistance == k) {
                for (int i = 0; i < levelSize; i++) {
                    result.add(queue.poll().val);
                }
                return result;
            }

            // 5. PROCESS EXACTLY ONE LEVEL: Explore all 3 possible
            // directions (left, right, parent) for each node in this level.
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // WHY: Check all THREE neighbors - this is what makes the
                // tree behave like an undirected graph. Each neighbor is
                // only added if it exists AND hasn't been visited yet
                // (prevents infinite loops, e.g., child -> parent -> same child).
                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.offer(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode parent = parentMap.get(node);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                }
            }

            // 6. ADVANCE DISTANCE: We've fully expanded one more edge outward.
            currentDistance++;
        }

        // WHY: If k is larger than the "radius" of the tree from target
        // (e.g., Example 2: single node, k=3), the queue empties out
        // before ever reaching currentDistance == k, so we return empty.
        return result;
    }
    private static void buildParentMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (node == null) {
            return;
        }
        parentMap.put(node, parent);
        buildParentMap(node.left, node, parentMap);
        buildParentMap(node.right, node, parentMap);
    }
}